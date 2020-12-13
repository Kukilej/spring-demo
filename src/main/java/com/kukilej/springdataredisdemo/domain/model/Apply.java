package com.kukilej.springdataredisdemo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "apply")
public class Apply implements Serializable {


    private static final long serialVersionUID = -4628882357786781599L;

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_name", nullable = false)
    private College college;

    @Column(name = "major")
    private String major;

    @Column(name = "decision")
    private String decision;



}