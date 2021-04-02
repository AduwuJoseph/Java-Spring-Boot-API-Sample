/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author First Digit
 */
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }  

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email is already taken!");
        }
        studentRepository.save(student);
        //System.out.println("student:" + student); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("Student with ID: " + studentId + " does not exists!");
        }
        studentRepository.deleteById(studentId);
    }
    
    @Transactional
    void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with ID: " 
                        + studentId + " does not exists!"));
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);            
        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
            if(studentByEmail.isPresent()){
                throw new IllegalStateException("Email is already taken!");
            }
            student.setEmail(email);            
        }
    }

}
