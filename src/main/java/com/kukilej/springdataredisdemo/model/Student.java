package com.kukilej.springdataredisdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = -5766207474404481797L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name")
    @Size(min = 5, max = 50, message = "fullName length must be between 5 and 50 characters.")
    @NotNull(message = "Student name must not be null.")
    private String studentName;

    @Column(name = "gpa")
    @Positive
    @Max(4)
    private Long gpa;

    @Column(name = "highschool_size")
    @Positive
    @Max(3000)
    private Long highSchoolSize;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Apply> applyList;
}