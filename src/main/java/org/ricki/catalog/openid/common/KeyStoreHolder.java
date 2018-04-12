package org.ricki.catalog.openid.common;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class KeyStoreHolder {

  private Map<String, KeyStore> keyStores = new HashMap<>(10);
  private ReentrantLock keyStoreLock = new ReentrantLock(true);

  private Map<String, Key> keyMap = new HashMap<>(10);
  private ReentrantReadWriteLock keyMapLock = new ReentrantReadWriteLock(true);

  //--------------------------------------------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------
  //--------------------------------------------------------------------------------------------------

  /**
   * Загрузка открытого сертификата
   *
   * @param alias
   * @param fileName
   * @return
   */
  public void loadCertificate(String alias, String fileName) {
    try {
      keyMapLock.writeLock().lock();
      if (keyMap.get(alias) == null) {
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        FileInputStream is = new FileInputStream(new File(fileName));
        X509Certificate cer = (X509Certificate) fact.generateCertificate(is);
        PublicKey key = cer.getPublicKey();
        keyMap.put(alias, key);
      }
    } catch (Throwable th) {
      throw new RuntimeException(th);

    } finally {
      keyMapLock.writeLock().unlock();
    }
  }

  public void loadKey(String alias, String keystoreFileName, String keyStorePassword, String keyAlias, String keyPassword) {
    KeyStore keyStore;

    if (keyMap.get(alias) == null) {
      try {
        keyStore = getKeyStore(keystoreFileName, keyStorePassword);
      } catch (Throwable th) {
        throw new RuntimeException(th);
      }

      try {
        keyMapLock.writeLock().lock();
        Key key = keyStore.getKey(keyAlias, keyPassword.toCharArray());
        keyMap.put(alias, key);
      } catch (Throwable th) {
        throw new RuntimeException(th);
      } finally {
        keyMapLock.writeLock().unlock();
      }
    }
  }

  public KeyStore getKeyStore(String keystoreFileName, String storePassword)
          throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
    KeyStore keyStore = keyStores.get(keystoreFileName);
    if (keyStore == null) {
      keyStoreLock.lock();
      try {
        keyStore = keyStores.get(keystoreFileName);
        if (keyStore == null) {
          File file = new File(keystoreFileName);
          FileInputStream is = new FileInputStream(file);
          try {
            keyStore = KeyStore.getInstance("JKS"); //KeyStore.getDefaultType()

            keyStore.load(is, storePassword.toCharArray());

            keyStores.put(keystoreFileName, keyStore);
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
