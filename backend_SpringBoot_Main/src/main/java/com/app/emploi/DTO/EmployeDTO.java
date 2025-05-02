package com.app.emploi.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeDTO {
    private String numEmp;
    private String nom;
    private int nbJours;
    private int tauxJ;
    private int salaire;
}
