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

    public AlunoController(AlunoService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listar(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> criar(@RequestBody AlunoDTO dto){
        dto.setId(null);

        AlunoDTO criado = service.criar(dto);
        URI uri = URI.create("/api/alunos/" + criado.getId());
        return ResponseEntity.created(uri).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> atualizar(@PathVariable Long id, @RequestBody AlunoDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(service.atualizar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{alunoId}/cursos/{cursoId}")
    public ResponseEntity<AlunoDTO> vincularCurso(@PathVariable Long alunoId, @PathVariable Long cursoId){
        return ResponseEntity.ok(service.vincularCurso(alunoId, cursoId));
    }
}
