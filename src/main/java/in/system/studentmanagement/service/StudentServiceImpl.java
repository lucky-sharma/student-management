package in.system.studentmanagement.service;

import in.system.studentmanagement.domain.Student;
import in.system.studentmanagement.dto.StudentDTO;
import in.system.studentmanagement.dto.StudentSemesterDTO;
import in.system.studentmanagement.repository.CustomStudentRepository;
import in.system.studentmanagement.repository.StudentRepository;
import in.system.studentmanagement.repository.StudentSemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentSemesterRepository semesterRepository;
    @Autowired
    private CustomStudentRepository customStudentRepository;

    @Override
    public StudentDTO getStudentDetail(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            List<StudentSemesterDTO> marks = semesterRepository.findByStudentId(student.getId());
            StudentDTO studentDTO = prepareResponse(student);
            studentDTO.setMarks(marks);
            return studentDTO;
        }
        return null;
    }

    private StudentDTO prepareResponse(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setCode(student.getCode());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setMobile(student.getMobile());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setAdmissionYear(student.getAdmissionYear());
        studentDTO.setBranchCode(student.getBranchCode());
        studentDTO.setCourse(student.getCourse());
        studentDTO.setCurrentSemester(student.getCurrentSemester());
        return studentDTO;
    }

    public List<Student> search(Map<String, String> requestParam) {
        return customStudentRepository.search(requestParam);
    }

    public List<Map<String, Object>> performance(Integer semester, String branchCode) {
        List<StudentSemesterDTO> studentSemester = semesterRepository.findBySemesterAndBranchCode(semester, branchCode);
        long count75_100 = 0;
        long count50_75 = 0;
        long count25_50 = 0;
        long count0_25 = 0;
        for (StudentSemesterDTO e : studentSemester) {
            if (75 <= e.getPercentage() && 100 >= e.getPercentage()) {
                count75_100++;
            } else if (50 <= e.getPercentage() && 75 > e.getPercentage()) {
                count50_75++;
            } else if (25 <= e.getPercentage() && 50 > e.getPercentage()) {
                count25_50++;
            } else if (0 <= e.getPercentage() && 25 > e.getPercentage()) {
                count0_25++;
            }
        }
        Map<String, Object> result1 = new LinkedHashMap<>();
        result1.put("range", "75-100");
        result1.put("total", count75_100);
        Map<String, Object> result2 = new LinkedHashMap<>();
        result2.put("range", "50-75");
        result2.put("total", count50_75);
        Map<String, Object> result3 = new LinkedHashMap<>();
        result3.put("range", "25-50");
        result3.put("total", count25_50);
        Map<String, Object> result4 = new LinkedHashMap<>();
        result4.put("range", "0-25");
        result4.put("total", count0_25);
        return Arrays.asList(result1, result2, result3, result4);
    }

}
