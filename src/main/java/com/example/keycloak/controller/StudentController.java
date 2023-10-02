package com.example.keycloak.controller;

import com.example.keycloak.entity.Student;
import com.example.keycloak.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
    private final StudentRepository studentRepository;

    @PostMapping
    @RolesAllowed("manager")
    public Student addStudent(@RequestBody final Student student){
        return studentRepository.save(student);
    }

    @GetMapping
    @RolesAllowed({"manager","user"})
    public List<Student> getStudents(@RequestHeader(name = "authorization",required = false) final String accessToken) {
        System.out.println(accessToken);

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        KeycloakPrincipal principal = (KeycloakPrincipal)auth.getPrincipal();
//        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
//        AccessToken accessTokenForKeyCloak = session.getToken();
//        String username = accessTokenForKeyCloak.getPreferredUsername();
//        String emailID = accessTokenForKeyCloak.getEmail();
//        String lastname = accessTokenForKeyCloak.getFamilyName();
//        String firstname = accessTokenForKeyCloak.getGivenName();
//        String realmName = accessTokenForKeyCloak.getIssuer();
//        AccessToken.Access realmAccess = accessTokenForKeyCloak.getRealmAccess();
//        realmAccess.getRoles().forEach(System.out::println);


        return studentRepository.findAll();
    }

    @GetMapping("{id}")
    @RolesAllowed({"manager","user"})
    public Student getStudent(@PathVariable Integer id){
        return studentRepository.findById(id).orElseThrow();
    }
}
