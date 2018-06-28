package co.nz.equifax.registration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public boolean generateOTP(String code) {
		UuidCode uuidCode= new UuidCode(code,Long.valueOf(Util.getRandomKeyNumereic(6)).longValue());
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

}
