DROP DATABASE IF EXISTS audit_db;
CREATE DATABASE audit_db;
USE audit_db;

-- Table des utilisateurs RH
CREATE TABLE user_rh (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(40) NOT NULL,
    password VARCHAR(255) NOT NULL -- Stockage sécurisé du mot de passe (hash)
);

-- Table des employés
CREATE TABLE employee (
    matricule VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    phone VARCHAR(40) NOT NULL,
    user_id VARCHAR(36), -- RH responsable de cet employé
    FOREIGN KEY (user_id) REFERENCES user_rh(id) ON DELETE SET NULL
);

-- Table d'audit des employés
CREATE TABLE audit_employee (
    id VARCHAR(36) PRIMARY KEY,
    action_type ENUM('insert', 'delete', 'update') NOT NULL,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    employee_id VARCHAR(36) NULL, -- Doit avoir le même type que employee.matricule
    name VARCHAR(100),
    old_salary DECIMAL(10,2),
    new_salary DECIMAL(10,2),
    user_id VARCHAR(36), -- RH responsable de l'action
    FOREIGN KEY (user_id) REFERENCES user_rh(id) ON DELETE SET NULL
);

-- Trigger pour INSERT
CREATE TRIGGER after_employee_insert
AFTER INSERT ON employee
FOR EACH ROW
INSERT INTO audit_employee (id, action_type, employee_id, name, new_salary, user_id)
VALUES (UUID(), 'insert', NEW.matricule, NEW.name, NEW.salary, NEW.user_id);

-- Trigger pour UPDATE
CREATE TRIGGER after_employee_update
AFTER UPDATE ON employee
FOR EACH ROW
INSERT INTO audit_employee (id, action_type, employee_id, name, old_salary, new_salary, user_id)
VALUES (UUID(), 'update', OLD.matricule, OLD.name, OLD.salary, NEW.salary, NEW.user_id);

-- Trigger pour DELETE
CREATE TRIGGER after_employee_delete
AFTER DELETE ON employee
FOR EACH ROW
INSERT INTO audit_employee (id, action_type, employee_id, name, old_salary, user_id)
VALUES (UUID(), 'delete', OLD.matricule, OLD.name, OLD.salary, OLD.user_id);
