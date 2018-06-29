package co.nz.equifax.configurations;

import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

import co.nz.equifax.utils.Constants;

@Configuration
public class RestTemplateConfig {

  private final int TIMEOUT = (int) TimeUnit.SECONDS.toMillis(Constants.TIMEOUT_IN_SECONDS);

  /*@Value("${ibmi-username}")
  private String ibmiUsername;

  @Value("${ibmi-password}")
  private String ibmiPassword;

  @Value("${ibmi-host}")
  private String ibmiHost;*/

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {

    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
            .loadTrustMaterial(null, acceptingTrustStrategy)
            .build();

    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

    //Handle Proxy
        HttpHost proxy = new HttpHost("eisproxynz",8080);
        CloseableHttpClient httpClient = HttpClients.custom()
            .setProxy(proxy).setSSLSocketFactory(csf)
            .build();

    /*CloseableHttpClient httpClient = HttpClients.custom()
            .setSSLSocketFactory(csf)
            .build();*/

    HttpComponentsClientHttpRequestFactory requestFactory =
            new HttpComponentsClientHttpRequestFactory();
    requestFactory.setConnectTimeout(TIMEOUT);
    requestFactory.setReadTimeout(TIMEOUT);
    requestFactory.setConnectionRequestTimeout(TIMEOUT);
    requestFactory.setHttpClient(httpClient);

    RestTemplate restTemplate = new RestTemplate(requestFactory);
    /*restTemplate.getInterceptors().add(
            new BasicAuthorizationInterceptor("itg2ibmi", "practiv101"));*/
    return restTemplate;
  }
}
