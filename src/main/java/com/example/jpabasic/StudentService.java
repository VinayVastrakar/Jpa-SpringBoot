package com.example.jpabasic;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository repo;

    public String addStudent(Student student) {
        repo.save(student);
        return "Student added Succesfully";
    }

    public Student getStudentById(int rollNo) {
        Optional<Student> optionalStudent= repo.findById(rollNo);
        if(optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        return null;
    }

    public String deleteById(int rollNo) {
        repo.deleteById(rollNo);
        return "Deleted";
    }

    public String updateAgeById(int rollNo, int age) {
        Optional<Student> student= repo.findById(rollNo);
        if(student.isPresent()){
            student.get().setAge(age);
            repo.save(student.get());
        }

        return "Age Updated";
    }

    public List<Student> findAgeAbove25(int age) {
        return repo.findByAgeGreaterThan(age);
    }

    public String deleteAll() {
        repo.deleteAll();
        return "All Entries Deleted";
    }

    public List<Student> findByName(String name) {
       return repo.findByName(name);
    }
}
