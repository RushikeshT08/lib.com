package com.capgemini.project.controllers;


import com.capgemini.project.entities.Registration;
import com.capgemini.project.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    
    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Registration> getRegistrationById(@PathVariable Long id) {
        Optional<Registration> registration = registrationService.getRegistrationById(id);
        return registration.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<Registration> createRegistration(@RequestBody Registration registration) {
        Registration savedRegistration = registrationService.createRegistration(registration);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRegistration);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable Long id, @RequestBody Registration updatedRegistration) {
        Registration savedRegistration = registrationService.updateRegistration(id, updatedRegistration);
        if (savedRegistration == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(savedRegistration);
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        if (registrationService.deleteRegistration(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
// // Add this method to the RegistrationController
//
//    @GetMapping("/user/{username}")
//    public ResponseEntity<Registration> getRegistrationByUsername(@PathVariable String username) {
//        try {
//            Optional<Registration> registration = registrationService.getAllRegistrations().stream()
//                .filter(reg -> reg.getUsername().equalsIgnoreCase(username))
//                .findFirst();
//
//            if (registration.isPresent()) {
//                return ResponseEntity.ok(registration.get());
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();  // Log the exception
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }


}
