package com.challenge2.challenge2.services.impl;

import com.challenge2.challenge2.dto.ClassDTO;
import com.challenge2.challenge2.dto.SquadDTO;
import com.challenge2.challenge2.entities.Classes;
import com.challenge2.challenge2.entities.Squad;
import com.challenge2.challenge2.entities.Student;
import com.challenge2.challenge2.repositories.ClassRepository;
import com.challenge2.challenge2.repositories.StudentRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClassServiceImpl implements ClassService{
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository, StudentRepository studentRepository) {
        this.classRepository = classRepository;
        this.studentRepository = studentRepository;
    }

    public List<Classes> getAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Classes> getClassById(Long id) {
        return classRepository.findById(id);
    }


    public Classes saveClass(Classes c) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Classes>> violations = validator.validate(c);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Invalid entity: " + violations);
        }

        return classRepository.save(c);
    }

    public Classes createClassWithStudents(ClassDTO classDTO) {
        List<Long> studentIds = classDTO.getStudents();
        Classes c = new Classes();
        c.setLearningPath(classDTO.getLearningPath());
        c.setSprint(classDTO.getSprint());

        System.out.println("Entrou");
        for (Long studentId : studentIds) {
            Optional<Student> studentOptional = studentRepository.findById(studentId);
            Student student = studentOptional.orElseThrow(() -> new IllegalArgumentException("O aluno com ID " + studentId + " não foi encontrado."));
            System.out.println(studentOptional.get().getId());

            if (student.getSquad() != null) {
                throw new IllegalArgumentException("O aluno já se encontra em uma classe, remova-o da classe para adicioná-lo em outra");
            }

            c.getStudents().add(student);
            student.setClasses(c);
        }

        return classRepository.save(c);
    }

    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }


}