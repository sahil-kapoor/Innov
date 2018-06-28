package co.nz.equifax.registration.controllers;


import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.nz.equifax.registration.entity.ClientOrg;
import co.nz.equifax.registration.entity.UuidCode;
import co.nz.equifax.registration.repository.ClientOrgRepository;
import co.nz.equifax.registration.repository.UuidCodeRepository;
import co.nz.equifax.utils.Util;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

  @Autowired
  private ClientOrgRepository clientOrgRepository;

/*  public RegistrationController(ClientOrgRepository clientOrgRepository) {
    this.clientOrgRepository = clientOrgRepository;
  }*/

  @Autowired
  private UuidCodeRepository uuidCodeRepository;
/*
  public RegistrationController(UuidCodeRepository uuidCodeRepository) {
    this.uuidCodeRepository = uuidCodeRepository;
  }
*/
  @GetMapping("/verify/{id}")
  public ResponseEntity<Object> verifyId(@PathVariable @NotNull String id) {
    if(clientOrgRepository.findByUuid(id).isPresent())
      return new ResponseEntity<>(HttpStatus.OK);
    else
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/add")
  public ResponseEntity<Object> setTasks() {
    ClientOrg org=new ClientOrg();
    org.setClientId(1001);
    org.setUuid(Util.getRandomKeyAlphanumeric(10));
    org.setOrgId(16929);
    clientOrgRepository.save(org);
    UuidCode uuidCode= new UuidCode(org.getUuid(),Long.valueOf(Util.getRandomKeyNumereic(6)).longValue());
    uuidCodeRepository.save(uuidCode);
    return new ResponseEntity<>(HttpStatus.OK);

  }

  @GetMapping("/confirm/{id}")
  public ResponseEntity<Object> confirmId(@PathVariable @NotNull String id,@RequestParam("id") @NotNull long code) {
	  UuidCode uuidCode= new UuidCode(id,code);
    if(uuidCodeRepository.findUuidCodeByUuidAndCode(id,code).isPresent())
      return new ResponseEntity<>(HttpStatus.OK);
    else
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

 /* @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable long id) {
    taskRepository.deleteById(id);
  }*/
}
