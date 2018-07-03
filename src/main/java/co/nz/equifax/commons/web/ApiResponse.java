package co.nz.equifax.commons.web;

import java.time.Instant;

public class ApiResponse {
	
    private Boolean success;
    private String message;
    private Object data;
    private long timestamp;
    private String version;
    
    
	public long getTimestamp() {
		return timestamp;
	}
	

	public String getVersion() {
		return version;
	}


	public Object getData() {
		return data;
	}

	public ApiResponse(Boolean success, String message, Object data) {
		super();
		this.success = success;
		this.message = message;
		this.timestamp=Instant.now().getEpochSecond();
		this.data = data;
		this.version="1.0.0";
		
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
