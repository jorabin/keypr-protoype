package com.thebuildingblocks.keypr.common;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Objects;

public class EncryptedStreamProcessing {

    private final Map<Integer, EncryptionContext> encryptionContexts;
    private final EncryptionFormat encryptionFormat;
    private EncryptionContext encryptionContext;
    private ByteArrayOutputStream baos;

    /**
     * @param contexts a map of myPublicKeyId to an encryptionContext
     */
    public EncryptedStreamProcessing(Map<Integer, EncryptionContext> contexts, EncryptionFormat format) {
        this.encryptionContexts = contexts;
        this.encryptionFormat = format;
    }

    public InputStream decrypt(InputStream encryptedInputStream) throws IOException {
        // read the first 32 bits
        byte[] keyBytes = encryptedInputStream.readNBytes(4);
        // obtain keyId
        int publicKeyId = ByteBuffer.wrap(keyBytes).getInt();
        // obtain encryption context
        this.encryptionContext = encryptionContexts.get(publicKeyId);
        // check it exists
        if (Objects.isNull(encryptionContext)) {
            throw new IllegalStateException("Unknown keyId received");
        }
        // if we don't have their public signature key yet, then we need to await the processing
        // of the incoming (Pair Request or Response) to obtain it, so at this point we can only decrypt
        // and keep a buffer for later verification
        if (Objects.isNull(encryptionContext.theirSignaturePublicKey)) {
            baos = new ByteArrayOutputStream();
            encryptedInputStream.transferTo(baos);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            return encryptionFormat.decrypt(bais, encryptionContext.myEncryptionKeyPair.getPrivate());
        }
        // we have their public keys, so we can return the stream decryptor
        return encryptionFormat.decryptAndVerify(encryptedInputStream,
                encryptionContext.myEncryptionKeyPair.getPrivate(),
                encryptionContext.theirSignaturePublicKey);
    }

    public InputStream verify() {
        return encryptionFormat.decryptAndVerify(new ByteArrayInputStream(baos.toByteArray()),
                encryptionContext.myEncryptionKeyPair.getPrivate(),
                encryptionContext.theirSignaturePublicKey);
    }

    public OutputStream encrypt(OutputStream encryptedOutputStream) throws IOException {
        // send their public key id
        encryptedOutputStream.write(ByteBuffer.allocate(4).putInt(encryptionContext.theirPublicKeyId).array());
        // return the stream encryptor
        return encryptionFormat.signAndEncrypt(encryptedOutputStream,
                encryptionContext.mySignatureKeyPair.getPrivate(),
                encryptionContext.theirEncryptionPublicKey);
    }
}