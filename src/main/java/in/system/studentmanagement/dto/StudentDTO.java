package in.system.studentmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDTO {
     private Long id;
     private String code;
     private String firstName;
     private String lastName;
     private String email;
     private Long mobile;
     private Integer admissionYear;
     private String branchCode;
     private String course;
     private Integer currentSemester;
     private List<StudentSemesterDTO> marks;
}
