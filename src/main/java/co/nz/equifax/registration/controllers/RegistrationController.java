package co.nz.equifax.registration.controllers;


import co.nz.equifax.registration.entity.ClientOrg;
import co.nz.equifax.registration.repository.ClientOrgRepository;
import co.nz.equifax.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

  @Autowired
  private ClientOrgRepository clientOrgRepository;

  public RegistrationController(ClientOrgRepository clientOrgRepository) {
    this.clientOrgRepository = clientOrgRepository;
  }


  @GetMapping("/verify/{id}")
  public ResponseEntity<Object> getTasks(@PathVariable @NotNull String id) {
    if(clientOrgRepository.findByUuid(id).isPresent())
      return new ResponseEntity<>(HttpStatus.OK);
    else
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/add")
  public ResponseEntity<Object> setTasks() {
    ClientOrg org=new ClientOrg();
    org.setClientId(1001);
    org.setUuid(Util.getRandomKey());
    org.setOrgId(16929);
    clientOrgRepository.save(org);
    return new ResponseEntity<>(HttpStatus.OK);

  }
/*
  @PutMapping("/{id}")
  public void editTask(@PathVariable long id, @RequestBody Task task) {
    Task existingTask = taskRepository.findById(id).get();
    Assert.notNull(existingTask, "Task not found");
    existingTask.setDescription(task.getDescription());
    taskRepository.save(existingTask);
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable long id) {
    taskRepository.deleteById(id);
  }*/
}
