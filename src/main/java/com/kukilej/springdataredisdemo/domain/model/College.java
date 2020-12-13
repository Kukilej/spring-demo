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
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "college")
public class College implements Serializable {

    private static final long serialVersionUID = -5128506251041559131L;

    @Id
    @Column(name = "college_name")
    private String collegeName;

    @Column(name = "state")
    @Size(min = 2, max = 2)
    @NotNull(message = "state must not be null.")
    private String state;

    @Column(name = "enrollment")
    @Positive
    @Max(50000)
    private int enrollment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "college", cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Apply> applyList;


}