package co.nz.equifax.user.response;

import java.time.Instant;

public class AuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private long timestamp=Instant.now().getEpochSecond();

    
    
    public AuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public long getTimestamp() {
		return timestamp;
	}

	public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
