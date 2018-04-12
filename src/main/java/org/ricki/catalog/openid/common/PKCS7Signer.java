package org.ricki.catalog.openid.common;

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
import java.util.Base64;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

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

  public String sign(String data, PrivateKey privateKey) {
    try {
      Signature privateSignature = Signature.getInstance("SHA256withRSA");
      privateSignature.initSign(privateKey);
      privateSignature.update(data.getBytes(UTF_8));

      byte[] signature = privateSignature.sign();

      return Base64.getEncoder().encodeToString(signature);
    } catch (Throwable th) {
      // TODO exception handling
      return null;
    }
  }
  //--------------------------------------------------------------------------------------------------

  public boolean verify(String data, String signature, PublicKey publicKey) {
    try {
      Signature publicSignature = Signature.getInstance("SHA256withRSA");
      publicSignature.initVerify(publicKey);
      publicSignature.update(data.getBytes(UTF_8));

      byte[] signatureBytes = Base64.getDecoder().decode(signature);

      return publicSignature.verify(signatureBytes);
    } catch (Throwable th) {
      // TODO exception handling
      throw new RuntimeException(th);
    }
  }
  //--------------------------------------------------------------------------------------------------

}
