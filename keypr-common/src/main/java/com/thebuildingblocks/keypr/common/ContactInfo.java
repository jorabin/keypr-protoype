package com.thebuildingblocks.keypr.common;

import org.derecalliance.derec.api.DeRecIdentity;

import java.net.URI;

import static com.thebuildingblocks.keypr.common.Cryptography.secureRandom;

/**
 * This is the info a Helper Gives a Sharer to bootstrap the key exchange
 */
public class ContactInfo {
    final DeRecIdentity sharerId;
    final DeRecIdentity helperId;
    String publicEncryptionKey;
    long nonce;
    int publicKeyId;
    URI transportUri;
    public ContactInfo(DeRecIdentity sharerId, DeRecIdentity helperId) {
        this.sharerId = sharerId;
        this.helperId = helperId;
        this.publicKeyId = secureRandom.nextInt();
        this.nonce = secureRandom.nextLong();
        this.publicEncryptionKey = helperId.getPublicKey();
        this.transportUri = helperId.getAddress();
    }
}