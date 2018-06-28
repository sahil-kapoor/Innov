package co.nz.equifax.commons.web;

import java.time.Instant;

public class ApiResponse {
	
    private Boolean success;
    private String message;
    private Object data;
    private long timeStampSeconds;
    
    
	public long getTimeStampSeconds() {
		return timeStampSeconds;
	}

	public Object getData() {
		return data;
	}

	public ApiResponse(Boolean success, String message, Object data) {
		super();
		this.success = success;
		this.message = message;
		this.timeStampSeconds=Instant.now().getEpochSecond();
		this.data = data;
		
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
