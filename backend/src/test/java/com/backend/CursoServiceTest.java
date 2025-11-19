package com.backend;

import com.backend.dto.CursoDTO;
import com.backend.entity.Curso;
import com.backend.repository.CursoRepository;
import com.backend.service.CursoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CursoServiceTest {

    private CursoRepository cursoRepository;
    private CursoService cursoService;

    @BeforeEach
    void setup() {
        cursoRepository = mock(CursoRepository.class);
        cursoService = new CursoService(cursoRepository);
    }

    @Test
    void deveListarTodosCursos() {
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("Engenharia");

        when(cursoRepository.findAll()).thenReturn(List.of(curso));

        List<CursoDTO> lista = cursoService.listarTodos();

        assertEquals(1, lista.size());
        assertEquals("Engenharia", lista.get(0).getNome());
    }

    @Test
    void deveCriarCurso() {
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("ADS");
        curso.setCargaHoraria(2000);

        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        CursoDTO dto = new CursoDTO();
        dto.setNome("ADS");
        dto.setCargaHoraria(2000);

        CursoDTO salvo = cursoService.criar(dto);

        assertNotNull(salvo.getId());
        assertEquals("ADS", salvo.getNome());
        assertEquals(2000, salvo.getCargaHoraria());
    }

    @Test
    void deveAtualizarCurso() {
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNome("Antigo");

        when(cursoRepository.findById(1L)).thenReturn(Optional.of(curso));

        CursoDTO dto = new CursoDTO();
        dto.setNome("Atualizado");
        dto.setCargaHoraria(3000);

        CursoDTO atualizado = cursoService.atualizar(1L, dto);

        assertEquals("Atualizado", atualizado.getNome());
        assertEquals(3000, atualizado.getCargaHoraria());
    }

    @Test
    void deveFalharAoAtualizarCursoInexistente() {
        when(cursoRepository.findById(anyLong())).thenReturn(Optional.empty());

        CursoDTO dto = new CursoDTO();
        dto.setNome("X");

        assertThrows(RuntimeException.class, () -> cursoService.atualizar(999L, dto));
    }

    @Test
    void deveDeletarCurso() {
        when(cursoRepository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> cursoService.deletar(1L));

        verify(cursoRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveFalharAoDeletarCursoInexistente() {
        when(cursoRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(RuntimeException.class, () -> cursoService.deletar(999L));
    }
}
