-- ROLES
MERGE INTO tb_roles (role_id, name)
KEY (role_id)
VALUES (1, 'ADMIN');

MERGE INTO tb_roles (role_id, name)
KEY (role_id)
VALUES (2, 'BASIC');

-- Cursos
MERGE INTO CURSO (id, nome, carga_horaria)
KEY (id)
VALUES (1, 'Engenharia de Software', 3600);

MERGE INTO CURSO (id, nome, carga_horaria)
KEY (id)
VALUES (2, 'Ciência da Computação', 3200);

-- Alunos
MERGE INTO ALUNO (id, nome, email, matricula)
KEY (id)
VALUES (1, 'João Silva', 'joao@example.com', '20250001');

MERGE INTO ALUNO (id, nome, email, matricula)
KEY (id)
VALUES (2, 'Maria Souza', 'maria@example.com', '20250002');

-- Relacionamentos
MERGE INTO ALUNO_CURSO (aluno_id, curso_id)
KEY (aluno_id, curso_id)
VALUES (1, 1);

MERGE INTO ALUNO_CURSO (aluno_id, curso_id)
KEY (aluno_id, curso_id)
VALUES (2, 2);
