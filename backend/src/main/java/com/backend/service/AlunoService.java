package com.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dto.AlunoDTO;
import com.backend.entity.Aluno;
import com.backend.entity.Curso;
import com.backend.repository.AlunoRepository;
import com.backend.repository.CursoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepo;
    private final CursoRepository cursoRepo;

    public AlunoService(AlunoRepository alunoRepo, CursoRepository cursoRepo) {
        this.alunoRepo = alunoRepo;
        this.cursoRepo = cursoRepo;
    }

    public List<AlunoDTO> listarTodos() {
        return alunoRepo.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AlunoDTO criar(AlunoDTO dto) {
        Aluno a = new Aluno();
        a.setNome(dto.getNome());
        a.setEmail(dto.getEmail());
        a.setMatricula(dto.getMatricula());
        Aluno salvo = alunoRepo.save(a);
        return toDTO(salvo);
    }

    @Transactional
    public AlunoDTO atualizar(AlunoDTO dto) {
        Aluno aluno = alunoRepo.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setMatricula(dto.getMatricula());

        return toDTO(aluno);
    }

    public void deletar(Long id) {
        if (!alunoRepo.existsById(id)) {
            throw new RuntimeException("Aluno não encontrado");
        }
        alunoRepo.deleteById(id);
    }

    @Transactional
    public AlunoDTO vincularCurso(Long alunoId, Long cursoId) {
        Aluno aluno = alunoRepo.findById(alunoId).orElseThrow();
        Curso curso = cursoRepo.findById(cursoId).orElseThrow();
        aluno.getCursos().add(curso);
        return toDTO(aluno);
    }

    public AlunoDTO buscarPorId(Long id) {
        Aluno aluno = alunoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        return toDTO(aluno);
    }

    private AlunoDTO toDTO(Aluno a) {
        AlunoDTO dto = new AlunoDTO();
        dto.setId(a.getId());
        dto.setNome(a.getNome());
        dto.setEmail(a.getEmail());
        dto.setMatricula(a.getMatricula());
        dto.setCursoIds(a.getCursos().stream()
                .map(Curso::getId)
                .collect(Collectors.toSet()));
        return dto;
    }
}
