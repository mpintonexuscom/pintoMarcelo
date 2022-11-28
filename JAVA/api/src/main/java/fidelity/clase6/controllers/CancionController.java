package fidelity.clase6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fidelity.clase6.repositories.CancionRepository;

@RestController
public class CancionController {
    
    @Autowired
    private CancionRepository cancionRepo;
    
}
