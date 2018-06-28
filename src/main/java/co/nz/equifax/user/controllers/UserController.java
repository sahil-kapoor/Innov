package co.nz.equifax.user.controllers;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.nz.equifax.commons.exception.AppException;
import co.nz.equifax.commons.web.ApiResponse;
import co.nz.equifax.commons.web.EmptyJsonResponse;
import co.nz.equifax.security.JwtTokenProvider;
import co.nz.equifax.user.entities.ApplicationUser;
import co.nz.equifax.user.entities.Role;
import co.nz.equifax.user.entities.RoleName;
import co.nz.equifax.user.repository.ApplicationUserRepository;
import co.nz.equifax.user.repository.RoleRepository;
import co.nz.equifax.user.requests.LoginRequest;
import co.nz.equifax.user.requests.SignUpRequest;
import co.nz.equifax.user.response.AuthenticationResponse;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	
	@Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    
  
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(applicationUserRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!",new EmptyJsonResponse()),
                    HttpStatus.BAD_REQUEST);
        }
        

        // Creating user's account
        ApplicationUser user = new ApplicationUser(signUpRequest.getUsername(),signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        ApplicationUser result = applicationUserRepository.save(user);

        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully",new EmptyJsonResponse()));
    }

}
