/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.student;

import java.time.*;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author First Digit
 */
@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student john =    new Student(
                "John Doe",
                "john.doe@gmail.com",
                LocalDate.of(1999, Month.JANUARY, 12)
            );
            Student lisa =    new Student(
                "Lisa Mona",
                "lisa.mona@gmail.com",
                LocalDate.of(2000, Month.JANUARY, 12)                
            );
            Student rose =    new Student(
                "Rose Mary",
                "rose.mary@gmail.com",
                LocalDate.of(2001, Month.JANUARY, 12)
            );
            Student sam =    new Student(
                "Sam Loko",
                "sam.loko@gmail.com",
                LocalDate.of(1991, Month.JANUARY, 12)
            );
            
            repository.saveAll(List.of(john, lisa, rose, sam));
        };
    }
}
