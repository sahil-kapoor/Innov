package co.nz.equifax.registration.services;




import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.nz.equifax.registration.entity.UuidCode;
import co.nz.equifax.registration.repository.ClientOrgRepository;
import co.nz.equifax.registration.repository.UuidCodeRepository;
import co.nz.equifax.utils.Util;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private ClientOrgRepository clientOrgRepository;

	@Autowired
	private UuidCodeRepository uuidCodeRepository;
	
	 @Autowired
	 private RestTemplate restTemplate;

	@Override
	public boolean generateOTP(String code) throws NumberFormatException, Exception {
		UuidCode uuidCode= new UuidCode(code,Long.valueOf(sendOTP()).longValue());
	    if(null !=uuidCodeRepository.save(uuidCode))
		return true;
	    else
	    	return false;
	}

	@Override
	public boolean isCustomerCodeValid(String code) {
		if(clientOrgRepository.findByUuid(code).isPresent())
			return true;
		else
			return false;
	}

	@Override
	public boolean verifyOTP(String code, Long otp) {
		if(uuidCodeRepository.findUuidCodeByUuidAndCode(code,otp).isPresent())
			return true;
		else
		return false;
	}
	
	
	public String sendOTP() throws Exception {
		String otp=Util.getRandomKeyNumereic(6);
		HttpHeaders headers = new HttpHeaders();
	    String plainClientCredentials="AC68345e7dc3204355613b58d188e0f3a5:1de3a36aeddc97b5d3e4baa83822aa0a";
	    String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));


	    headers.add("Authorization", "Basic " + base64ClientCredentials);
	    MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    map.add("To", "+64272756362");
	    map.add("From", "+61451266954");
	    map.add("Body", "Your OTP for Equifax Credit Score is: "+otp+". If you have not requested, please call 0800 692 733");

	    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
	    ResponseEntity<?> entity = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
	    try {
	        entity = restTemplate.postForEntity("https://api.twilio.com/2010-04-01/Accounts/AC68345e7dc3204355613b58d188e0f3a5/Messages.json", request, String.class);

	        if (entity.getStatusCode().isError()) {
	            throw new Exception("Rest Request Failed - Status Code");
	        }
	        ObjectMapper mapper = new ObjectMapper();
	        resultMap = mapper.readValue((String) entity.getBody(), new TypeReference<Map<String, String>>() {
	        });
	       
	    } catch (HttpStatusCodeException exception) {
	        
	        throw new Exception(exception.getMessage(), exception.getCause());
	    } catch (RestClientException exception) {
	        
	    } catch (Exception exception) {
	        
	    }
	    return otp;
		
		
	}
	
}


