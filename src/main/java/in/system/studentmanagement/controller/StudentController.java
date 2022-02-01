package in.system.studentmanagement.controller;

import in.system.studentmanagement.domain.Student;
import in.system.studentmanagement.dto.StudentDTO;
import in.system.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping(value = "/search")
    public ResponseEntity<Object> search(@RequestParam(required = false) Map<String, String> requestParams) {
        List<Student> studentList = studentService.search(requestParams);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("total", studentList.size());
        result.put("data", studentList);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<Object> getStudentDetailById(@PathVariable("studentId") Long studentId) {
        StudentDTO student = studentService.getStudentDetail(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(value = "/performance")
    public ResponseEntity<Object> studentPerformance(@RequestParam("semester") Integer semester,
                                                     @RequestParam("branchCode") String branchCode) {
        return new ResponseEntity<>(studentService.performance(semester, branchCode), HttpStatus.OK);
    }

}
