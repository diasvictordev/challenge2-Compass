package com.challenge2.challenge2.services.impl;

import com.challenge2.challenge2.entities.Classes;
import com.challenge2.challenge2.repositories.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService{
    private ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {

        this.classRepository = classRepository;
    }

    public List<Classes> getAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Classes> getClassById(Long id) {

        return classRepository.findById(id);
    }


    public Classes saveClass(Classes c) {
        return classRepository.save(c);
    }

    public void deleteClass(Long id) {

        classRepository.deleteById(id);
    }


}