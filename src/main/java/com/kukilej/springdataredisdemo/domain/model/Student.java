package com.kukilej.springdataredisdemo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

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

    @OneToMany(fetch = LAZY, mappedBy = "student", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Apply> applyList;
}