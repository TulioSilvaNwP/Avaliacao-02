package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {}