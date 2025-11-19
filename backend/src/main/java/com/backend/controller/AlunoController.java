package com.backend.controller;

import com.backend.dto.AlunoDTO;
import com.backend.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    private final AlunoService service;

    public AlunoController(AlunoService service){ this.service = service; }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listar(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> criar(@RequestBody AlunoDTO dto){
        dto.setId(null);

        AlunoDTO criado = service.criar(dto);
        URI uri = URI.create("/api/alunos/" + criado.getId());
        return ResponseEntity.created(uri).body(criado);
    }

    @PostMapping("/{alunoId}/cursos/{cursoId}")
    public ResponseEntity<AlunoDTO> vincularCurso(@PathVariable Long alunoId, @PathVariable Long cursoId){
        return ResponseEntity.ok(service.vincularCurso(alunoId, cursoId));
    }
}
