package com.capgemini.project.services;


import com.capgemini.project.entities.Registration;
import com.capgemini.project.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Registration> getAllRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();
        System.out.println("Total registrations fetched: " + registrations.size());  
        return registrations;
    }


    public Optional<Registration> getRegistrationById(Long id) {
        return registrationRepository.findById(id);
    }

    public Registration createRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }

    public Registration updateRegistration(Long id, Registration updatedRegistration) {
        if (registrationRepository.existsById(id)) {
            updatedRegistration.setId(id);
            return registrationRepository.save(updatedRegistration);
        }
        return null;
    }

    public boolean deleteRegistration(Long id) {
        if (registrationRepository.existsById(id)) {
            registrationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
