package org.ricki.catalog.system.openid.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SignatureHolder {

  private Map<String, KeyPair> keyPairs = new HashMap<>(10);

  public KeyPair loadKeyFromKeystore(String keysoreFileName, String alias, String storePassword, String keyPassword)
          throws FileNotFoundException, KeyStoreException
          , NoSuchAlgorithmException, CertificateException, IOException
          , UnrecoverableKeyException {

    KeyPair keyPair = keyPairs.get(alias);

    if (keyPairs.get(alias) == null) {
      synchronized (this) {
        keyPair = keyPairs.get(alias);
        if (keyPair == null) {

          File file = new File(keysoreFileName);
          FileInputStream is = new FileInputStream(file);
          try {
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());

            keystore.load(is, storePassword.toCharArray());

            Key key = keystore.getKey(alias, keyPassword.toCharArray());
            keyPair = new KeyPair(keystore.getCertificate(alias).getPublicKey(), (PrivateKey) key);

            keyPairs.put(alias, keyPair);
          } finally {
            is.close();
          }
        }
      }
    }

    return keyPair;
  }

}
