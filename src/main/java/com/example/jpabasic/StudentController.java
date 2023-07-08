package com.example.jpabasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService serv;
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student){
        String response= serv.addStudent(student);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-by-id")
    public ResponseEntity getStudentById(@RequestParam("rollNo") int rollNo){
        Student student= serv.getStudentById(rollNo);
        if(student==null){
            return new ResponseEntity("Invalid Roll No.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student,HttpStatus.FOUND);
    }

    // delete a student by roll no
    @DeleteMapping("/delete-by-id")
    public ResponseEntity deleteById(@RequestParam("rollNo") int rollNo){
        String response= serv.deleteById(rollNo);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    // update the age of a student based on roll no -> rollNo and age as input
    @PutMapping("/update-age-by-id")
    public ResponseEntity updateAgeById(@RequestParam int rollNo,@RequestParam int age){
        String response = serv.updateAgeById(rollNo,age);
        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }


    // find all the student who have age greater than 25
    @GetMapping("/find-by-age-above-25")
    public ResponseEntity findAgeAbove25(){
        List<Student> list= serv.findAgeAbove25(25);
        return new ResponseEntity(list,HttpStatus.FOUND);
    }

    // delete all the students
    @DeleteMapping("/delete-all")
    public ResponseEntity deleteAll(){
        String response= serv.deleteAll();;

        return new ResponseEntity(response,HttpStatus.ACCEPTED);
    }

    // find all the student with a given name
    @GetMapping("/find-by-name")
    public ResponseEntity findByName(@RequestParam String name){
        List<Student> list= serv.findByName(name);
        return new ResponseEntity(list, HttpStatus.FOUND);
    }

}
