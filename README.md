# Biblioteca - Sistema de Gerenciamento de Livros

Um sistema de gerenciamento de biblioteca desenvolvido em Java com Spring Boot que permite buscar, cadastrar e gerenciar livros e autores.

## 🚀 Funcionalidades

- 🔍 Busca de livros por título através da API Gutenberg
- 📚 Cadastro automático de livros encontrados
- 👥 Gerenciamento de autores
- 📊 Listagem de livros por idioma
- 🎯 Busca de autores vivos em determinado ano
- 💾 Persistência de dados em banco PostgreSQL

## 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot 3.5.0
- Spring Data JPA
- PostgreSQL
- Jackson (para manipulação de JSON)
- Maven

## 📋 Pré-requisitos

- Java 21 ou superior
- Maven
- PostgreSQL
- Conexão com internet (para busca de livros)

## 🔧 Configuração do Ambiente

1. Clone o repositório:
```bash
git clone [URL_DO_REPOSITÓRIO]
```

2. Configure o banco de dados PostgreSQL:
   - Crie um banco de dados `nome_db`
   - Configure as credenciais no arquivo `.env` na raiz do projeto:
```properties
DB_URL=endereço_do_banco
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
```

3. Execute o projeto:
```bash
mvn spring-boot:run
```

## 📖 Como Usar

Ao iniciar o sistema, você terá acesso ao menu principal com as seguintes opções:

1. **Buscar livros pelo título**
   - Digite o título do livro desejado
   - O sistema buscará na API Gutenberg
   - O livro será salvo automaticamente se encontrado

2. **Lista de Livros registrados**
   - Exibe todos os livros cadastrados no sistema

3. **Lista de autores registrados**
   - Mostra todos os autores cadastrados

4. **Lista de autores vivos em determinado ano**
   - Permite buscar autores que estavam vivos em um ano específico

5. **Lista de livros por idioma**
   - Filtra livros por idioma (pt, en, es, fr)

## 🏗️ Estrutura do Projeto

```
src/main/java/br/com/alura/biblioteca/
├── DTO/                  # Objetos de transferência de dados
├── model/               # Entidades do sistema
├── repository/          # Interfaces de acesso a dados
├── service/            # Serviços e lógica de negócio
└── principal/          # Interface principal do sistema
```

## 🤝 Contribuindo

1. Faça um Fork do projeto
2. Crie uma Branch para sua Feature (`git checkout -b feature/AmazingFeature`)
3. Faça o Commit de suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Faça o Push para a Branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## ✨ Agradecimentos

- [Gutenberg Project](https://www.gutenberg.org/) pela API de livros
- [Alura](https://www.alura.com.br/) pelo conteúdo educacional
