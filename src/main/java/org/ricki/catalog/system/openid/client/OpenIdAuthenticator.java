package org.ricki.catalog.system.openid.client;

import org.ricki.catalog.system.openid.common.SignatureHolder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OpenIdAuthenticator {

  @Inject
  SignatureHolder signatureHolder;

  private String keystoreFileName;

  private String keystorePassword;

  private String keyPassword;

  private String keyAlias;

  private String client_id;

  String redirect_uri;

  public byte[] sign(String alias, byte[] data) {
    try {
      KeyPair keyPair = signatureHolder.loadKeyFromKeystore(keystoreFileName, alias, keystorePassword, keyPassword);

      // keyPair.
    } catch (Throwable th) {

      return null;
    }
    return null;
  }

  public String createAuthRequest() {
    String access_type = "online";
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss +0000");
    String timestamp = fmt.format(new Date());

    String state = UUID.randomUUID().toString();

    String scope = "fullname";
    // подпишем
    String signingData = scope + timestamp + client_id + state;
    String client_secret = "";
    try {
      KeyPair keyPair = signatureHolder.loadKeyFromKeystore(keystoreFileName, keyAlias, keystorePassword, keyPassword);
//      keyPair
    } catch (Throwable th) {
      // TODO exception handler
      return null;
    }

    StringBuilder sb = new StringBuilder(2048);
    try {
      sb.append("response_type=code&")
              .append("client_id=").append(URLEncoder.encode(client_id, "UTF-8"))
              .append("redirect_uri=").append(URLEncoder.encode(redirect_uri, "UTF-8"))
              .append("scope=").append(URLEncoder.encode(scope, "UTF-8"))
              .append("client_secret=").append(URLEncoder.encode(client_secret, "UTF-8"))
              .append("state=").append(URLEncoder.encode(state, "UTF-8"))
              .append("timestamp=").append(URLEncoder.encode(timestamp, "UTF-8"))
              .append("access_type=").append(URLEncoder.encode(access_type, "UTF-8"));
      return sb.toString();
    } catch (UnsupportedEncodingException uee) {
      return null;
    }
  }


  public void setKeystoreFileName(String fileName) {
    keystoreFileName = fileName;
  }

  public void setKeystorePassword(String password) {
    keystorePassword = password;
  }

  public void setKeyPassword(String keyPassword) {
    this.keyPassword = keyPassword;
  }

  public void setKeyAlias(String keyAlias) {
    this.keyAlias = keyAlias;
  }

  public void setClient_id(String client_id) {
    this.client_id = client_id;
  }

  public void setRedirect_uri(String redirect_uri) {
    this.redirect_uri = redirect_uri;
  }
}
