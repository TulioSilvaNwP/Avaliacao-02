package com.backend.dto;

import lombok.*;

import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AlunoDTO {
    private Long id;
    private String nome;
    private String email;
    private String matricula;
    private Set<Long> cursoIds;
}
