package com.thebuildingblocks.keypr.common;

import java.security.KeyPair;
import java.security.PublicKey;

/**
 * Things the parties need to know to communicate with counterparties
 */
public class EncryptionContext {
    /**
     * This is the info a Helper Gives a Sharer to bootstrap the key exchange
     */
    public static class ContactInfo {
        String publicEncryptionKey;
        long nonce;
        int publicKeyId;
        String transportUri;
    }
    // the value sent in plaintext that allows identification of the encryption key used
    int publicKeyId;
    // the nonce to be used in initial pairing request
    long nonce;
    KeyPair myEncryptionKeyPair;
    KeyPair mySignatureKeyPair;
    byte [] myEncryptionPublicKeyHash; // SHA-384, for convenience

    // gathered during pairing
    PublicKey theirEncryptionPublicKey;
    // gathered during pairing
    PublicKey theirSignaturePublicKey;
    byte [] theirEncryptionPublicKeyHash;
}
