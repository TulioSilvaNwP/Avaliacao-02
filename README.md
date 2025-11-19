📘 Sistema Acadêmico – Backend (Spring Boot 3)

Este projeto é o backend de um sistema acadêmico, desenvolvido como prática avaliativa. Ele inclui autenticação JWT, CRUD completo de alunos e cursos, relacionamento N:N, documentação com Swagger, testes unitários, monitoramento com Actuator, além da estrutura necessária para integração com Prometheus e Grafana.

🚀 Tecnologias Utilizadas

Java 17+
Spring Boot 3
Spring Web
Spring Data JPA
Spring Security (JWT)
Spring Boot Actuator
SpringDoc OpenAPI (Swagger)
H2 Database
JUnit / Mockito

📦 Como Rodar o Projeto Localmente
🔧 1. Clonar o repositório
git clone <url-do-repositorio>
cd backend

▶️ 2. Executar o projeto

Usando Maven:
mvn spring-boot:run

Ou rodando o .jar:
mvn clean package
java -jar target/backend-0.0.1-SNAPSHOT.jar

🗄️ Banco de Dados H2
A aplicação usa o H2 em memória.

📚 Documentação da API (Swagger)

Acesse a documentação interativa:
👉 http://localhost:8080/swagger-ui.html

🔐 Autenticação (JWT)
A aplicação utiliza Spring Security + JWT.
Endpoint de Login

POST /auth/login
Exemplo de requisição:

{
  "username": "admin",
  "password": "123"
}


Resposta:

{
  "token": "jwt-token-aqui"
}

Como usar o token
Envie o header:
Authorization: Bearer <token>

👨‍🎓 Endpoints de Aluno

GET /alunos — Lista todos os alunos
POST /alunos — Cria um novo aluno
GET /alunos/{id} — Busca um aluno pelo ID
PUT /alunos/{id} — Atualiza os dados de um aluno
DELETE /alunos/{id} — Remove um aluno

📘 Endpoints de Curso

GET /cursos — Lista todos os cursos
POST /cursos — Cria um novo curso
GET /cursos/{id} — Busca um curso pelo ID
PUT /cursos/{id} — Atualiza os dados de um curso

🔗 Relacionamento Aluno–Curso

O projeto utiliza relacionamento N:N entre Aluno e Curso.
Um aluno pode estar em vários cursos
Um curso pode ter vários alunos


📈 Monitoramento com Spring Boot Actuator

Endpoints úteis:

/actuator/health
/actuator/info
/actuator/metrics
/actuator/prometheus ← usado pelo Prometheus


▶️ Rodar os containers
docker compose up -d

🧪 Testes Unitários

Os testes utilizam JUnit e Mockito.
Para executar:
mvn test


🧨 Testes de Carga e Stress (JMeter ou Gatling)
Usando Gatling (via Maven)
mvn gatling:test

O relatório será gerado automaticamente em:
/target/gatling


☁️ Link do Projeto no Render 
https://avaliacao-02.onrender.com/swagger-ui/index.html

📖 Referências

https://spring.io/projects/spring-boot
https://spring.io/projects/spring-security
https://springdoc.org
https://prometheus.io
https://grafana.com