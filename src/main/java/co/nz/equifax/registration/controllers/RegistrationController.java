package co.nz.equifax.registration.controllers;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.nz.equifax.commons.web.ApiResponse;
import co.nz.equifax.commons.web.EmptyJsonResponse;
import co.nz.equifax.registration.entity.ClientOrg;
import co.nz.equifax.registration.entity.UuidCode;
import co.nz.equifax.registration.repository.ClientOrgRepository;
import co.nz.equifax.registration.repository.UuidCodeRepository;
import co.nz.equifax.registration.response.CustomerCodeResponse;
import co.nz.equifax.registration.services.RegistrationService;
import co.nz.equifax.utils.Util;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private ClientOrgRepository clientOrgRepository;

	@Autowired
	@Qualifier("registrationService")
	private RegistrationService registrationService;

	@Autowired
	private UuidCodeRepository uuidCodeRepository;

	@GetMapping("/verify/{code}")
	public ResponseEntity<?> verifyId(@PathVariable @NotNull String code) throws NumberFormatException, Exception {
		if (registrationService.isCustomerCodeValid(code)) {
			registrationService.generateOTP(code);
			return ResponseEntity
					.ok(new ApiResponse(true, "Customer code is valid. OTP generated.", new EmptyJsonResponse()));
		}

		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(false, "Customer code is valid.", new EmptyJsonResponse()));
	}

	@GetMapping("/add")
	public ResponseEntity<Object> setTasks() {
		ClientOrg org = new ClientOrg();
		String code=Util.getRandomKeyAlphanumeric(10);
		org.setClientId(1001);
		org.setUuid(code);
		org.setOrgId(16929);
		clientOrgRepository.save(org);
		/*UuidCode uuidCode = new UuidCode(org.getUuid(), Long.valueOf(Util.getRandomKeyNumereic(6)).longValue());
		uuidCodeRepository.save(uuidCode);*/
		CustomerCodeResponse cCode=new CustomerCodeResponse();
		cCode.setCustomerCode(code);
		
		return new ResponseEntity<>(new ApiResponse(true, "Customer Code generated!!", cCode),HttpStatus.OK);
	}

	@GetMapping("/confirm/{code}")
	public ResponseEntity<Object> confirmId(@PathVariable @NotNull String code,
			@RequestParam("otp") @NotNull long otp) {
		if (registrationService.verifyOTP(code, otp))
			return ResponseEntity.ok(new ApiResponse(true, "OTP verified.", new EmptyJsonResponse()));
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(false, "Customer OTP is invalid.", new EmptyJsonResponse()));
	}

	/*
	 * @DeleteMapping("/{id}") public void deleteTask(@PathVariable long id) {
	 * taskRepository.deleteById(id); }
	 */
}
