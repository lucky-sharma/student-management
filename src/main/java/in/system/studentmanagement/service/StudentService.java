package in.system.studentmanagement.service;

import in.system.studentmanagement.domain.Student;
import in.system.studentmanagement.dto.StudentDTO;

import java.util.List;
import java.util.Map;

public interface StudentService {
    StudentDTO getStudentDetail(Long studentId);
    List<Student> search(Map<String, String> requestParam);
    List<Map<String, Object>> performance(Integer semester, String branchCode);
}
