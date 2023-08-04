package com.challenge2.challenge2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Classes {

    @Id
    @Column(name = "class_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Learning path não pode ser vazio.")
    @Column(name = "learning_path")
    private String learningPath;

    @Min(value = 1, message = "O valor mínimo para sprint é 1")
    @NotNull
    @Column(name= "sprint")
    private Integer sprint;

    @OneToMany(mappedBy = "classes")
    private List<Student> students = new ArrayList<>();
}
