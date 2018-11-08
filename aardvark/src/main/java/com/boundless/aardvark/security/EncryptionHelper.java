package com.boundless.aardvark.security;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import androidx.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.security.auth.x500.X500Principal;

/**
 * Although it technically works
 *
 * There are improvements to be done :
 * - Handling below/above marshmellow
 * - Type of padding
 * - Type of encryption (AES?)
 */
public class EncryptionHelper {

    // Alias keys
    public static final String ALIAS_GOOGLE_PAYMENTS_ENCRYPTION = "ALIAS_GOOGLE_PAYMENTS_ENCRYPTION";

    private static final String COMMON_NAME = "soundwaves";
    private static final String ORGANISATION = "boundless";
    private static final int STANDARD_YEARS_OF_ENCRYPTION = 1000;
    private static final String KEYSTORE = "AndroidKeyStore";
    private static final String ENCRYPTION_TYPE = "RSA";
    private static final String CIPHER_TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    private static final String CIPHER_PROVIDER = "AndroidOpenSSL";

    private Context context;
    private KeyStore keyStore;
    private List<String> keyAliases = new ArrayList<>();

    public void init(Context context) {
        this.context = context;

        try {
            keyStore = KeyStore.getInstance(KEYSTORE);
            keyStore.load(null);
        } catch (Exception e) {
            Log.e("Encryption test", e.getMessage(), e);
        }

        fetchKeys();
    }

    private void fetchKeys() {
        try {
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                keyAliases.add(aliases.nextElement());
            }
        }
        catch(Exception e) {
            Log.e("Encryption test", e.getMessage(), e);
        }
    }

    public void createNewKey(String keystoreAlias) {
        try {
            if (!keyStore.containsAlias(keystoreAlias)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.add(Calendar.YEAR, STANDARD_YEARS_OF_ENCRYPTION);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    // Below Marshmellow
                    KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(context)
                            .setAlias(keystoreAlias)
                            .setSubject(new X500Principal("CN=" + COMMON_NAME + ", O=" + ORGANISATION))
                            .setSerialNumber(BigInteger.ONE)
                            .setStartDate(calendar.getTime())
                            .setEndDate(calendar.getTime())
                            .build();
                    KeyPairGenerator generator = KeyPairGenerator.getInstance(ENCRYPTION_TYPE, KEYSTORE);
                    generator.initialize(spec);
                    generator.generateKeyPair();
                } else {
                    // Above Marshmellow
                    int purposes = KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT;
                    KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(keystoreAlias, purposes)
                            .setCertificateSubject(new X500Principal("CN=" + COMMON_NAME + ", O=" + ORGANISATION))
                            .setCertificateSerialNumber(BigInteger.ONE)
                            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                            .setKeyValidityStart(calendar.getTime())
                            .setKeyValidityEnd(calendar.getTime())
                            .build();
                    KeyPairGenerator generator = KeyPairGenerator.getInstance(ENCRYPTION_TYPE, KEYSTORE);
                    generator.initialize(spec);
                    generator.generateKeyPair();
                }
            }
        } catch (Exception e) {
            Log.e("Encryption test", e.getMessage(), e);
        }

        fetchKeys();
    }

    public void deleteKey(final String keystoreAlias) {
        try {
            keyStore.deleteEntry(keystoreAlias);
        } catch (KeyStoreException e) {
            Log.e("Encryption test", e.getMessage(), e);
        }

        fetchKeys();
    }

    @Nullable
    public String encryptString(String keystoreAlias, String stringToEncrypt) {
        try {
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(keystoreAlias, null);

            Cipher input;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                input = Cipher.getInstance(CIPHER_TRANSFORMATION, CIPHER_PROVIDER);
            } else {
                input = Cipher.getInstance(CIPHER_TRANSFORMATION);
            }
            input.init(Cipher.ENCRYPT_MODE, privateKeyEntry.getCertificate().getPublicKey());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, input);
            cipherOutputStream.write(stringToEncrypt.getBytes("UTF-8"));
            cipherOutputStream.close();

            byte [] vals = outputStream.toByteArray();
            return Base64.encodeToString(vals, Base64.DEFAULT);
        } catch (Exception e) {
            Log.e("Encryption test", e.getMessage(), e);
        }

        return null;
    }

    @Nullable
    public String decryptString(String keystoreAlias, String encryptedText) {
        try {
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(keystoreAlias, null);

            Cipher output;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                output = Cipher.getInstance(CIPHER_TRANSFORMATION, CIPHER_PROVIDER);
            } else {
                output = Cipher.getInstance(CIPHER_TRANSFORMATION);
            }
            output.init(Cipher.DECRYPT_MODE, privateKeyEntry.getPrivateKey());

            CipherInputStream cipherInputStream = new CipherInputStream(new ByteArrayInputStream(Base64.decode(encryptedText, Base64.DEFAULT)), output);
            ArrayList<Byte> values = new ArrayList<>();
            int nextByte;
            while ((nextByte = cipherInputStream.read()) != -1) {
                values.add((byte)nextByte);
            }

            byte[] bytes = new byte[values.size()];
            for(int i = 0; i < bytes.length; i++) {
                bytes[i] = values.get(i).byteValue();
            }

            String decryptedText = new String(bytes, 0, bytes.length, "UTF-8");
            return decryptedText;

        } catch (Exception e) {
            Log.e("Encryption test", e.getMessage(), e);
        }

        return null;
    }

    public List<String> getAliases() {
        return keyAliases;
    }

}
