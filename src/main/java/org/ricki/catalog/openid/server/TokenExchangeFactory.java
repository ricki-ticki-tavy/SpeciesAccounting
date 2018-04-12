package org.ricki.catalog.openid.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ricki.catalog.openid.common.ErrorAnswer;
import org.ricki.catalog.openid.common.IdentityMarkerAssertionSection;
import org.ricki.catalog.openid.common.TokenRequestAnswer;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.ricki.catalog.openid.common.ErrorAnswer.ERROR_INVALID_GRANT;
import static org.ricki.catalog.openid.server.AuthCodeRequestStruct.*;

@RestController
@RequestMapping("/factory")
public class TokenExchangeFactory {

  @Inject
  AuthenticateService userTokenHolder;

  public static final String TOKEN_TYPE_BEARER = "Bearer";

  private String packError(String message) {
    Gson json = new GsonBuilder().create();
    return json.toJson(new ErrorAnswer(message));
  }

  @PostMapping
  public String postRequest(
          @RequestParam(CLIENT_ID_PARAM_NAME) String clientId
          , @RequestParam(CODE_PARAM_NAME) String code
          , @RequestParam(GRANT_TYPE_PARAM_NAME) String grantType
          , @RequestParam(CLIENT_SECRET_PARAM_NAME) String clientSecret
          , @RequestParam(STATE_PARAM_NAME) String state
          , @RequestParam(REDIRECT_URI_PARAM_NAME) String redirectUri
          , @RequestParam(SCOPE_PARAM_NAME) String scope
          , @RequestParam(TIMESTAMP_PARAM_NAME) String timestamp
          , @RequestParam(TOKEN_TYPE_PARAM_NAME) String tokenType
  ) {
    return getRequest(clientId, code, grantType, clientSecret, state, redirectUri, scope, timestamp, tokenType);
  }

  @GetMapping
  public String getRequest(
          @RequestParam(CLIENT_ID_PARAM_NAME) String clientId
          , @RequestParam(CODE_PARAM_NAME) String code
          , @RequestParam(GRANT_TYPE_PARAM_NAME) String grantType
          , @RequestParam(CLIENT_SECRET_PARAM_NAME) String clientSecret
          , @RequestParam(STATE_PARAM_NAME) String state
          , @RequestParam(REDIRECT_URI_PARAM_NAME) String redirectUri
          , @RequestParam(SCOPE_PARAM_NAME) String scope
          , @RequestParam(TIMESTAMP_PARAM_NAME) String timestamp
          , @RequestParam(TOKEN_TYPE_PARAM_NAME) String tokenType
  ) {
    Gson json = new GsonBuilder().create();
    if (code == null) {
      return json.toJson(new ErrorAnswer(ERROR_INVALID_GRANT));
    }
    if (!TOKEN_TYPE_BEARER.equals(tokenType)) {
      return json.toJson(new ErrorAnswer(ERROR_INVALID_GRANT));
    }

    UserTokenInfo tokenInfo;
    try {
      // проведем поиск по коду авторизации
      tokenInfo = userTokenHolder.exchangeAccessCodeToToken(code);
    } catch (Throwable th) {
      return json.toJson(new ErrorAnswer(th.getMessage()));
    }

    // Сформируем идентификатор маркера
    StringBuilder sb = new StringBuilder(3000);
    String markerHeader = "{\"alg\":\"RS256\",\"kid\":\"1e9gdk7\"}";

    String markerAssertions = json.toJson(new IdentityMarkerAssertionSection("openIdSimpleServer"
            , tokenInfo.userAccount.getId().toString(), tokenInfo.validUntil, tokenInfo.loginDatetime
            , tokenInfo.tokenValidFrom));

    String merkerSign = "werwerwer";

    Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();

    sb.append(enc.encodeToString(markerHeader.getBytes(StandardCharsets.UTF_8))).append(".")
            .append(enc.encodeToString(markerAssertions.getBytes(StandardCharsets.UTF_8))).append(".")
            .append(enc.encodeToString(merkerSign.getBytes(StandardCharsets.UTF_8)));

    return json.toJson(new TokenRequestAnswer(sb.toString(), tokenInfo.token, tokenInfo.expires_in, state, TOKEN_TYPE_BEARER));
  }

}
