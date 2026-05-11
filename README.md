# 🌟 SolRiso-System

Um sistema full-stack moderno desenvolvido com **Spring Framework** para o backend e **Angular** para o frontend, foi projetado para oferecer uma solução para o gerenciamento de uma pousada, permitindo uma série de funcionalidades e sincronizações, onde o gestor pode gerenciar e escalar de uma forma mais articulada e facilitada pelo sistema, conta com sistema de login, restrições de roles, cadastro de hóspedes e reservas, integração com busca-CEP e amostragem facilitada de dados da reserva.

Também possui um sistema de gerenciamento financeiro onde o gestor possui pleno controle sobre as receitas e reservas feitas, de acordo com filtros especificados. O SolRiso-System busca criar um ecossistema completo para que a gerencia de um estabelecimento possa ser conduzida de forma integrada e com o mínimo de complicações possíveis.

---

## 🏗️ Estrutura do Projeto

Abaixo segue uma visualização geral da arquitetura de pastas do projeto, contendo informações tanto do backend quanto do frontend.

```

SolRiso-System/
├── Backend/          # API REST em Java-Spring Boot
    ├── src/          # Código backend desenvolvido
    ├── Dockerfile    # Arquivo de gerenciamento do Docker
    ├── pom.xml       # Gerenciador de pacotes do projeto backend
    ├── .gitignore    # GitIgnore do Backend
    └── README.md     # Descrição do projeto Backend
├── Frontend/         # Aplicação Angular
    ├── public/       # Arquivos de imagens do projeto
    ├── src/          # Código Frontend desenvolvido
    ├── .gitignore    # GitIgnore do Frontend
    └── README.md     # Descrição do projeto Frontend
└── README.md         # Arquivo de descrição geral do projeto

```

---

## 🚀 Stack Tecnológica

Frontend:
- NodeJS
- Angular
- TypeScript
- HTML
- CSS
- SCSS

Backend:
- Java 21
- Spring Boot 3.4.3
- Spring Security 
- Spring Data JPA
- Spring Boot Validation
- SpringDoc
- JWT
- Auth0
- Flyway
- SLF4J
- Lombok
- MapStruct
- Docker

Database: 
- PostgreSQL
- pgAdmin 4 e DBeaver
- Supabase

## 📚 Documentação

### Backend

Para informações detalhadas sobre o backend, consulte [Backend/README.md](Backend/README.md)

**Principais funcionalidades:**
- APIs RESTful
- Segurança com Spring Security
- Autenticação e autorização
- Persistência de dados com JPA/Hibernate
- Tratamento de exceções e validações

### Frontend

Para informações detalhadas sobre o frontend, consulte [Frontend/README.md](Frontend/README.md)

**Principais funcionalidades:**
- Componentes reutilizáveis
- Roteamento com Angular Router
- Gerenciamento de estado
- Comunicação com APIs via HTTP Client
- Validação de formulários

---

## 🔄 Integração Backend-Frontend

### Configuração de CORS

O backend deve estar configurado para aceitar requisições do frontend:

**Backend (application.properties ou application.yml):**
```properties
# Configurar CORS
server.servlet.context-path=/api
spring.web.cors.allowed-origins=http://localhost:4200
spring.web.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
spring.web.cors.allowed-headers=*
spring.web.cors.allow-credentials=true
```

### Endpoints da API

Consulte a documentação da API do backend ou utilize ferramentas como **Postman** ou **Swagger** para explorar os endpoints disponíveis.

---

## 📞 Suporte

Para suporte ou dúvidas, abra uma [issue](https://github.com/Eduardo-Decarli/SolRiso-System/issues) no repositório.

---

## 🔗 Links Úteis

- [Documentação Spring Boot](https://spring.io/projects/spring-boot)
- [Documentação Angular](https://angular.io/docs)
- [Maven Repository](https://mvnrepository.com/)
- [npm Packages](https://www.npmjs.com/)

---

**Desenvolvido com ❤️ por Eduardo Decarli**
