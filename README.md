# 🌟 SolRiso-System

Um sistema full-stack moderno desenvolvido com **Java-Spring** no backend e **Angular** no frontend, projetado para oferecer uma solução robusta e escalável.

## 📋 Visão Geral

O SolRiso-System é uma aplicação completa que combina:
- **Backend robusto** construído com Spring Boot
- **Frontend moderno** desenvolvido com Angular
- Arquitetura em camadas bem definida
- APIs RESTful para integração eficiente

---

## 🏗️ Estrutura do Projeto

```
SolRiso-System/
├── Backend/          # API REST em Java-Spring Boot
├── Frontend/         # Aplicação Angular
└── README.md         # Este arquivo
```

---

## 🚀 Início Rápido

### Pré-requisitos

#### Backend
- **Java 17** ou superior
- **Maven 3.8+** ou **Gradle**
- **MySQL 8.0+** (ou seu banco de dados preferido)

#### Frontend
- **Node.js 18+**
- **npm 9+** ou **yarn**
- **Angular CLI 20.3.4+**

### Instalação e Execução

#### 1️⃣ Backend (Java-Spring)

```bash
# Navegue até a pasta Backend
cd Backend

# Instale as dependências com Maven
mvn clean install

# Execute a aplicação
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

#### 2️⃣ Frontend (Angular)

```bash
# Navegue até a pasta Frontend
cd Frontend

# Instale as dependências
npm install

# Inicie o servidor de desenvolvimento
ng serve
```

A aplicação estará disponível em: `http://localhost:4200`

---

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

## 🛠️ Desenvolvimento

### Scripts Disponíveis

#### Backend
```bash
# Build
mvn clean package

# Testes
mvn test

# Executar com profile específico
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

#### Frontend
```bash
# Build de produção
ng build --configuration production

# Testes unitários
ng test

# Testes end-to-end
ng e2e

# Linting
ng lint
```

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

## 📦 Dependências Principais

### Backend
- Spring Boot 3.x
- Spring Web (MVC)
- Spring Data JPA
- Spring Security
- MySQL Driver / H2 (testes)
- Lombok
- Swagger/OpenAPI

### Frontend
- Angular 20.x
- RxJS
- TypeScript
- Angular Material (opcional)
- HttpClientModule

---

## 🧪 Testes

### Backend
```bash
cd Backend
mvn test
```

### Frontend
```bash
cd Frontend
ng test
```

---

## 🔐 Segurança

- Autenticação com JWT (JSON Web Tokens)
- Validação de entrada em todas as camadas
- Senhas criptografadas com BCrypt
- CORS configurado adequadamente
- Proteção contra CSRF

---

## 📝 Contribuindo

1. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
2. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
3. Push para a branch (`git push origin feature/AmazingFeature`)
4. Abra um Pull Request

---

## 📄 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

---

## 👤 Autor

**Eduardo Decarli**

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
