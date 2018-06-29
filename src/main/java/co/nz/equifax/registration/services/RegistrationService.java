package co.nz.equifax.registration.services;

public interface RegistrationService {
	
	public boolean generateOTP(String code) throws NumberFormatException, Exception;
	
	public boolean isCustomerCodeValid(String code);
	
	public boolean verifyOTP(String code, Long otp);

}
