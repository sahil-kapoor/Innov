import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class test {
  public static void main(String[] args) throws IOException {
    System.out.println( RandomStringUtils.randomAlphanumeric(10));
    String text="{\"id\":1,\"name\":\"Leanne Graham\",\"username\":\"Bret\",\"email\":\"Sincere@april.biz\",\"address\":{\"street\":\"Kulas Light\",\"suite\":\"Apt. 556\",\"city\":\"Gwenborough\",\"zipcode\":\"92998-3874\",\"geo\":{\"lat\":\"-37.3159\",\"lng\":\"81.1496\"}},\"phone\":\"1-770-736-8031 x56442\",\"website\":\"hildegard.org\",\"company\":{\"name\":\"Romaguera-Crona\",\"catchPhrase\":\"Multi-layered client-server neural-net\",\"bs\":\"harness real-time e-markets\"}}";
    ObjectMapper mapper = new ObjectMapper();
    JsonNode actualObj = mapper.readTree(text);
    System.out.println(actualObj);
    
/*    final NameValuePair[] data = {
    	    new BasicNameValuePair("phone", "+642041491175"),
    	    new BasicNameValuePair("message", "Hello world"),
    	    new BasicNameValuePair("key", "textbelt")
    	};
    	HttpClient httpClient = HttpClients.createMinimal();
    	HttpPost httpPost = new HttpPost("https://textbelt.com/text");
    	httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(data)));
    	HttpResponse httpResponse = httpClient.execute(httpPost);

    	String responseString = EntityUtils.toString(httpResponse.getEntity());
    	
    	System.out.println(responseString);*/
    	
    	
    	 String ACCOUNT_SID = "AC68345e7dc3204355613b58d188e0f3a5";
    	 String AUTH_TOKEN = "1de3a36aeddc97b5d3e4baa83822aa0a";

    	
    	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    	    Message message = Message.creator(new PhoneNumber("+642041491175"),
    	        new PhoneNumber("+61451266954"), 
    	        "123456").create();

    	    System.out.println(message.getSid());
  }

}
