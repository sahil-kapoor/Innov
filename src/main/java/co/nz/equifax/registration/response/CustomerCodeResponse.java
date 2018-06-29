package co.nz.equifax.registration.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class CustomerCodeResponse {

	private String customerCode;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
}
