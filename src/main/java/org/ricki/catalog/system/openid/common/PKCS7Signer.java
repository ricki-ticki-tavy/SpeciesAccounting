package org.ricki.catalog.system.openid.common;

import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PKCS7Signer {

  @Inject
  KeyStoreHolder keyStoreHolder;

  private static final String SIGNATUREALGO = "SHA1withRSA";
  public static final String BOUNCY_CASTLE_PROVIDER_SIG = "BC";

  private CMSSignedDataGenerator getGenerator(String keystoreFileName, String keyStorePassword, String alias, String keyPassword)
          throws IOException, KeyStoreException, CertificateException
          , NoSuchAlgorithmException, UnrecoverableKeyException
          , OperatorCreationException, CMSException {
    KeyStore keyStore = keyStoreHolder.getKeyStore(keystoreFileName, keyStorePassword);
    final List<java.security.cert.Certificate> certlist = new ArrayList(Arrays.asList((java.security.cert.Certificate[])
            keyStore.getCertificateChain(alias)));

    Store certstore = new JcaCertStore(certlist);
    Certificate cert = keyStore.getCertificate(alias);

    ContentSigner signer = new JcaContentSignerBuilder(SIGNATUREALGO).setProvider(BOUNCY_CASTLE_PROVIDER_SIG). // TODO WTF BOUNCY_CASTLE_PROVIDER_SIG ?!!
            build((PrivateKey) (keyStore.getKey(alias, keyPassword.toCharArray())));

    CMSSignedDataGenerator signGenerator = new CMSSignedDataGenerator();

    signGenerator.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").
            build()).build(signer, (X509Certificate) cert));

    signGenerator.addCertificates(certstore);
    return signGenerator;
  }
  //--------------------------------------------------------------------------------------------------

  public byte[] sign(String keystoreFileName, String keyStorePassword, String alias, String keyPassword, String data) {
    try {
      byte[] dataToSign = data.getBytes("UTF-8");
      CMSSignedDataGenerator signGenerator = getGenerator(keystoreFileName, keyStorePassword, alias, keyPassword);
      CMSTypedData cmsdata = new CMSProcessableByteArray(dataToSign);
      CMSSignedData signeddata = signGenerator.generate(cmsdata, true);
      return signeddata.getEncoded();
    } catch (Throwable th) {
      // TODO exception handling
      return null;
    }
  }
  //--------------------------------------------------------------------------------------------------

}
