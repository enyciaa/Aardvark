package com.boundless.aardvark.security;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class EncryptionTestActivity extends Activity {

    private static final String ENCRYPTION_KEY_ALIAS = "GOOGLE_PAYMENT_ENCRYPTION_KEY";
    private static final String STRING_TO_ENCRYPT = "Encrypt this little number";

    private EncryptionHelper boundlessEncryption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up encryption
        boundlessEncryption = new EncryptionHelper();
        boundlessEncryption.init(this);

        // Clear alias from keystore
        boundlessEncryption.deleteKey(ENCRYPTION_KEY_ALIAS);

        // Create key
        List<String> aliases = boundlessEncryption.getAliases();
        boolean aliasExists = false;
        for (String alias : aliases) {
            if (alias.equals(EncryptionHelper.ALIAS_GOOGLE_PAYMENTS_ENCRYPTION)) {
                 aliasExists = true;
            }
        }
        if (!aliasExists) {
            boundlessEncryption.createNewKey(ENCRYPTION_KEY_ALIAS);
        }

        // Encrypt and decrypt
        String encryptedString = boundlessEncryption.encryptString(ENCRYPTION_KEY_ALIAS, STRING_TO_ENCRYPT);
        String decryptedString = boundlessEncryption.decryptString(ENCRYPTION_KEY_ALIAS, encryptedString);

        // Set up layout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Alias key name
        TextView alias = new TextView(this);
        alias.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        alias.setText(ENCRYPTION_KEY_ALIAS);
        linearLayout.addView(alias);

        // Original string
        TextView stringToEncrypt = new TextView(this);
        stringToEncrypt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        stringToEncrypt.setText(STRING_TO_ENCRYPT);
        linearLayout.addView(stringToEncrypt);

        // Encrypted string
        TextView encryptedStringTextView = new TextView(this);
        encryptedStringTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        encryptedStringTextView.setText(encryptedString);
        linearLayout.addView(encryptedStringTextView);

        // Decrypted string
        TextView decryptedStringTextView = new TextView(this);
        decryptedStringTextView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        decryptedStringTextView.setText(decryptedString);
        linearLayout.addView(decryptedStringTextView);

        setContentView(linearLayout);
    }
}
