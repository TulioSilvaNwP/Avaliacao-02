package com.backend;

import com.backend.dto.AlunoDTO;
import com.backend.entity.Aluno;
import com.backend.entity.Curso;
import com.backend.repository.AlunoRepository;
import com.backend.repository.CursoRepository;
import com.backend.service.AlunoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlunoServiceTest {

    private AlunoRepository alunoRepository;
    private CursoRepository cursoRepository;
    private AlunoService alunoService;

    @BeforeEach
    void setup() {
        alunoRepository = mock(AlunoRepository.class);
        cursoRepository = mock(CursoRepository.class);
        alunoService = new AlunoService(alunoRepository, cursoRepository);
    }

    @Test
    void deveListarTodosAlunos() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("João");
        aluno.setEmail("joao@email.com");
        aluno.setMatricula("123");

        when(alunoRepository.findAll()).thenReturn(List.of(aluno));

        List<AlunoDTO> lista = alunoService.listarTodos();

        assertEquals(1, lista.size());
        assertEquals("João", lista.get(0).getNome());
    }

    @Test
    void deveCriarAluno() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Maria");

        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);

        AlunoDTO dto = new AlunoDTO();
        dto.setNome("Maria");

        AlunoDTO resultado = alunoService.criar(dto);

        assertNotNull(resultado.getId());
        assertEquals("Maria", resultado.getNome());
    }

    @Test
    void deveVincularCursoAoAluno() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);

        Curso curso = new Curso();
        curso.setId(10L);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(cursoRepository.findById(10L)).thenReturn(Optional.of(curso));

        AlunoDTO dto = alunoService.vincularCurso(1L, 10L);

        assertTrue(dto.getCursoIds().contains(10L));
    }

    @Test
    void deveFalharAoBuscarAlunoInexistente() {
        when(alunoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> alunoService.buscarPorId(999L));
    }
}
