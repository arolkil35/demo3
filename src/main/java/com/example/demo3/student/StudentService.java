package com.example.demo3.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository sRepository;

    public List<Student> getStudents() {
        return sRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = sRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("email taken............");
        }
        sRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = sRepository.existsById(studentId);
        if(!exists) {
            throw new IllegalStateException("student not there............"+studentId);
        }
        sRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = sRepository.findById(studentId)
                .orElseThrow( ()-> new IllegalStateException("student doesnt exists to update........"+studentId));
        if(name!=null && name.length()>0 && !Objects.equals(student.getName(),name)) {
            student.setName(name);
        }
        if(email!=null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = sRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw new IllegalStateException("email taken..........");
            }
            System.out.println(email+"..............");
            student.setEmail(email);
        }

    }
}
