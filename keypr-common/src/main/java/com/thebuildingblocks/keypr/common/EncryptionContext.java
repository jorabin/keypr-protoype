package com.thebuildingblocks.keypr.common;

import org.derecalliance.derec.api.DeRecIdentity;

import java.security.KeyPair;
import java.security.PublicKey;

import static com.thebuildingblocks.keypr.common.Cryptography.*;

/**
 * Things the parties need to know to communicate with counterparties.
 * <p>
 * It's mainly symmetrical. I know my and their publicKeyId, public keys, and their id. Only I know my
 * private keys, of course.
 * <p>
 * Helpers generate EncryptionContexts for all the sharers they know about (they must know the sharers in
 * advance and the sharers must have the equivalent of {@link ContactInfo} for each helper they wish to use.
 * <p>
 * Helpers generate ContactInfo from their encryption context since it is they that have chosen the nonce.
 * <p>
 * The values of the "their" fields get filled in as pairing progresses.
 */
public class EncryptionContext {
    // the value controls whether a {@link #getContactInfo()} call works or not
    boolean isHelper;
    // the nonce to be used in initial pairing request, this is set by the helper
    long nonce;

    DeRecIdentity myDeRecId;
    DeRecIdentity theirDeRecId;

    // the value sent in plaintext that allows identification of the encryption key used
    int myPublicKeyId;
    KeyPair myEncryptionKeyPair;
    KeyPair mySignatureKeyPair;
    // byte [] myEncryptionPublicKeyHash; // SHA-384, stored here for convenience

    // gathered during pairing
    int theirPublicKeyId;
    PublicKey theirEncryptionPublicKey;
    // gathered during pairing
    PublicKey theirSignaturePublicKey;
    // byte [] theirEncryptionPublicKeyHash;

    private EncryptionContext(DeRecIdentity myDeRecId, DeRecIdentity theirDeRecId) {
        this.myDeRecId = myDeRecId;
        this.theirDeRecId = theirDeRecId;
        myPublicKeyId = secureRandom.nextInt();
        nonce = secureRandom.nextLong();
        myEncryptionKeyPair = keyPairGenerator.generateKeyPair();
        mySignatureKeyPair = keyPairGenerator.generateKeyPair();
    }

    public static EncryptionContext forHelper(DeRecIdentity myDeRecId, DeRecIdentity theirDeRecId) {
        EncryptionContext ec = new EncryptionContext(myDeRecId, theirDeRecId);
        ec.isHelper = true;
        return ec;
    }

    public static EncryptionContext forSharer(ContactInfo contactInfo) {
        EncryptionContext ec = new EncryptionContext(contactInfo.sharerId, contactInfo.helperId);
        ec.nonce = contactInfo.nonce;
        ec.theirPublicKeyId = contactInfo.publicKeyId;
        ec.theirEncryptionPublicKey = fromPem(contactInfo.publicEncryptionKey);
        return ec;
    }

    public ContactInfo getContactInfo() {
        if (!isHelper) {
            throw new IllegalStateException("Cannot generate contact info for a sharer");
        }
        ContactInfo contactInfo = new ContactInfo(this.myDeRecId, this.theirDeRecId);
        contactInfo.nonce = nonce;
        contactInfo.publicEncryptionKey = pemFrom(myEncryptionKeyPair.getPublic());
        contactInfo.publicKeyId = this.myPublicKeyId;
        contactInfo.transportUri = this.myDeRecId.getAddress();
        return contactInfo;
    }
}
