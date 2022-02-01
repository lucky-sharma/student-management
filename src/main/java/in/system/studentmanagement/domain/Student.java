package in.system.studentmanagement.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String code;
    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;
    @Column(length = 100)
    private String email;
    private Long mobile;
    @Column(length = 4)
    private Integer admissionYear;
    @Column(length = 10)
    private String branchCode;
    @Column(length = 10)
    private String course;
    @Column(length = 1)
    private Integer currentSemester;
}
