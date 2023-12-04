package com.thebuildingblocks.keypr.common;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface EncryptionFormat {
    InputStream decrypt(InputStream inputStream, PrivateKey privateEncryptionKey);

    InputStream decryptAndVerify(InputStream inputStream, PrivateKey privateEncryptionKey, PublicKey publicSignatureKey);

    OutputStream signAndEncrypt(OutputStream outputStream, PrivateKey privateSignatureKey, PublicKey publicEncryptionKey);

    public class None implements EncryptionFormat {

        @Override
        public InputStream decrypt(InputStream inputStream, PrivateKey privateEncryptionKey) {
            return inputStream;
        }

        @Override
        public InputStream decryptAndVerify(InputStream inputStream, PrivateKey privateEncryptionKey, PublicKey publicSignatureKey) {
            return inputStream;
        }

        @Override
        public OutputStream signAndEncrypt(OutputStream outputStream, PrivateKey privateSignatureKey, PublicKey publicEncryptionKey) {
            return outputStream;
        }
    }
}