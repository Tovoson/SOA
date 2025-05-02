package com.app.emploi.repository;




import org.springframework.data.jpa.repository.JpaRepository;




import com.app.emploi.model.Employe;

public interface EmployeRepository extends JpaRepository<Employe ,String>{
 
   
}
