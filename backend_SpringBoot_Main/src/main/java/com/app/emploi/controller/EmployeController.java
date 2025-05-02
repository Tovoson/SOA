package com.app.emploi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


import com.app.emploi.model.Employe;
import com.app.emploi.service.EmployeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
@RestController
@RequestMapping("employe")
@Tag(name = "Employe" , description = "CRUD Employe")
public class EmployeController {
    private final EmployeService emplService;
    public EmployeController(EmployeService emplService){
        this.emplService= emplService;
    }

    @PostMapping
    @Operation(summary = "Ajout employe", description = "Ajoute un employe avec numEmp, nom, nb jours et taux jour")
    public ResponseEntity<Employe> createEtablissement(@Valid @RequestBody Employe empl) {   
        return new ResponseEntity<>(emplService.createEmpl(empl), HttpStatus.CREATED);  
        }
    @GetMapping
   
    @PutMapping("/{id}")
    @Operation(summary = "modifier employe", description = "modifier un employe ")
    public ResponseEntity<Employe> updateEmpl(@PathVariable String id, @Valid @RequestBody Employe etablissement ){
        return ResponseEntity.ok(emplService.updateEmpl(id, etablissement));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "supprimer employe", description = "supprimer un employe")
    public ResponseEntity<Void>  deleteEmpl(  @PathVariable String id){
        emplService.deleteEmpl(id);
        return ResponseEntity.noContent().build();
    }

}
