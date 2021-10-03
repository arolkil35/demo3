package com.example.demo3.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired
    StudentService sts;

    @GetMapping
    public List<Student> getStudents() {
        return sts.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        sts.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        sts.deleteStudent(studentId);
    }

    @PutMapping(path="{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false)String email){
        sts.updateStudent(studentId,name,email);
    }

}
