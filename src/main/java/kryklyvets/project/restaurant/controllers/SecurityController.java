package kryklyvets.project.restaurant.controllers;

import kryklyvets.project.restaurant.dtos.UserRequest;
import kryklyvets.project.restaurant.entities.security.Role;
import kryklyvets.project.restaurant.entities.security.User;
import kryklyvets.project.restaurant.services.SecurityService;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/security")
public class SecurityController {
    private final SecurityService service;

    public void createUser(){
        var userRequest = new UserRequest();
        userRequest.setUsername("user");
        userRequest.setPassword("user");
        service.createUser(userRequest,"ROLE_USER");
        var adminRequest = new UserRequest();
        adminRequest.setUsername("admin");
        adminRequest.setPassword("admin");
        service.createUser(adminRequest, "ROLE_ADMIN");
    }


    @PostMapping("/createUser")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<User> registerUser(UserRequest userDto){
        return  ResponseEntity.ok(service.createUser(userDto, "ROLE_USER"));
    }
    @PostMapping("/createAdmin")
    public ResponseEntity<User> registerAdmin(UserRequest userDto){
        return  ResponseEntity.ok(service.createUser(userDto,"ROLE_ADMIN"));
    }
    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(service.createToken(userRequest));
    }
}
