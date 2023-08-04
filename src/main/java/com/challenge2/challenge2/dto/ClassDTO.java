package com.challenge2.challenge2.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassDTO {
    private String learningPath;

    private Integer sprint;

    private List<Long> students;
}
