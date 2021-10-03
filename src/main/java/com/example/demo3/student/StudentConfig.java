package com.example.demo3.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student a = new Student("stud1", "1@gmail.com", LocalDate.of(2005,Month.JANUARY,7));
            Student b = new Student("stud2", "2@gmail.com", LocalDate.of(2000,Month.JANUARY,7));
            repository.saveAll(List.of(a,b));
        };
    };
}
