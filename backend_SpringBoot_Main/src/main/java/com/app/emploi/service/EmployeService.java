package com.app.emploi.service;

import java.util.List;

import org.springframework.stereotype.Service;



import com.app.emploi.model.Employe;
import com.app.emploi.repository.EmployeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class EmployeService {
    private final EmployeRepository emplRepository;
@Transactional
public Employe createEmpl(Employe empl){
    return emplRepository.save(empl);
}


public List<Employe> getAllEmployes(){
    return emplRepository.findAll();  
}

public Employe updateEmpl(String id,Employe empl ){
    if (emplRepository.existsById(id)){
        empl.setNumEmp(id);
        return emplRepository.save(empl);
    }else{
        throw new RuntimeException("employe non trouver");
    }

}
@Transactional
    public void deleteEmpl(String id){
        emplRepository.deleteById(id);
    }
}
