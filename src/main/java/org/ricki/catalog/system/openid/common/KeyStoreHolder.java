package org.ricki.catalog.system.openid.common;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class KeyStoreHolder {

  private Map<String, KeyStore> keyStores = new HashMap<>(10);
  private ReentrantLock keyStoreLock = new ReentrantLock(true);
  //--------------------------------------------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------

  public KeyStore getKeyStore(String keysoreFileName, String storePassword)
          throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
    KeyStore keyStore = keyStores.get(keysoreFileName);
    if (keyStore == null) {
      keyStoreLock.lock();
      try {
        keyStore = keyStores.get(keysoreFileName);
        if (keyStore == null) {
          File file = new File(keysoreFileName);
          FileInputStream is = new FileInputStream(file);
          try {
            keyStore = KeyStore.getInstance("JKS"); //KeyStore.getDefaultType()

            keyStore.load(is, storePassword.toCharArray());

            keyStores.put(keysoreFileName, keyStore);
          } finally {
            is.close();
          }

        }
      } finally {
        keyStoreLock.unlock();
      }
    }
    return keyStore;
  }
  //--------------------------------------------------------------------------------------------------

  @PostConstruct
  public void init() {
    Security.addProvider(new BouncyCastleProvider());
  }

  //--------------------------------------------------------------------------------------------------

}
