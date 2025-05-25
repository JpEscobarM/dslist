# DSList
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/JpEscobarM/dslist/blob/main/LICENSE) 

## Sobre o projeto

DSList é uma aplicação backend desenvolvida para fins de estudo no **Intensivão Java Spring** evento organizado pela [DevSuperior](https://devsuperior.com.br/).

O projeto consiste em uma API backend que persiste dados sobre jogos e organiza em listas para serem exibidos no frontend.

## Modelo de Domínio
![Modelo de Domínio](https://raw.githubusercontent.com/JpEscobarM/dslist/refs/heads/main/dslist/src/main/resources/assets/dslist-model.png)

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maeven

## Trechos de código
### aplication.properties
Define o perfil ativo da aplicação para ser alterado conforme o ambiente (teste, desenvolvimento ou produção)

```
spring.profiles.active=${APP_PROFILE:test}
spring.jpa.open-in-view=false

cors.origins=${CORS_ORIGINS:http://localhost:5173,http://localhost:3000}
```
### application-test.properties
Perfil de teste, utilizando banco em memória H2.

```
# H2 Connection
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# H2 Client
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Show SQL
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
### application-dev.properties
Usado para desenvolver localmente com PostgreSQL (via Docker + pgAdmin) e gerar scripts SQL a partir das entidades JPA (Jakarta Persistence)
```
#spring.jpa.properties.jakarta.persistence.schema-generation.create-source=metadata
#spring.jpa.properties.jakarta.persistence.schema-generation.scripts.action=create
#spring.jpa.properties.jakarta.persistence.schema-generation.scripts.create-target=create.sql
#spring.jpa.properties.hibernate.hbm2ddl.delimiter=;

spring.datasource.url=jdbc:postgresql://localhost:5432/dscatalog
spring.datasource.username=postgres
spring.datasource.password=1234567

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=none
```
### application-prod.properties
Configuração segura e externa para rodar a aplicação com banco de dados real (PostgreSQL), usando variáveis de ambiente para esconder credenciais e não gerar/alterar a estrutura do banco automaticamente.

```
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.hibernate.ddl-auto=none
```
### system.properties
```
java.runtime.version=17
```

### GameRepository

```java
@Query(nativeQuery = true, value = """
		SELECT tb_game.id, tb_game.title, tb_game.game_year AS `year`, tb_game.img_url AS imgUrl,
		tb_game.short_description AS shortDescription, tb_belonging.position
		FROM tb_game
		INNER JOIN tb_belonging ON tb_game.id = tb_belonging.game_id
		WHERE tb_belonging.list_id = :listId
		ORDER BY tb_belonging.position
			""")
List<GameMinProjection> searchByList(Long listId);
```

### GameListRepository

```java
@Modifying
@Query(nativeQuery = true, value = "UPDATE tb_belonging SET position = :newPosition WHERE list_id = :listId AND game_id = :gameId")
void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);
```
## Endpoints

### 1. Listar todos os jogos
**GET** `/games`

Retorna uma lista com todos os jogos cadastrados no sistema.

**Exemplo de resposta:**
```json
[
  {
    "id": 1,
    "title": "Mass Effect Trilogy",
    "year": 2012,
    "imgUrl": "https://...",
    "shortDescription": "Lorem ipsum..."
  },
	...
]
```
### 2.Bsucar detalhes por ID

**GET** /games/{id}

Retorna os detalhes completos de um jogo específico, com base no seu ID.

```json
[
{
    "id": 5,
    "title": "Ghost of Tsushima",
    "year": 2012,
    "genre": "Role-playing (RPG), Adventure",
    "platforms": "XBox, Playstation, PC",
    "score": 4.6,
    "imgUrl": "https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/5.png",
    "shortDescription": "Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!",
    "longDescription": "Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa."
}
]
```

### 3.Listar todas as listas de jogos

**GET** /lists

Retorna todas as listas de jogos disponíveis no sistema.

```json
[
[
    {
        "id": 1,
        "name": "Aventura e RPG"
    },
    {
        "id": 2,
        "name": "Jogos de plataforma"
    }
]
]
```

### 4.Listar todas as listas de jogos

**GET** /lists/{listId}/games

Retorna todos os jogos que pertencem a uma lista específica, identificada pelo seu ID.

```json
[
  {
    "id": 6,
    "title": "Super Mario World",
    "year": 1990,
    "imgUrl": "https://...",
    "shortDescription": "Lorem ipsum..."
  },
  {
    "id": 7,
    "title": "Hollow Knight",
    "year": 2017,
    "imgUrl": "https://...",
    "shortDescription": "Lorem ipsum..."
  }
]
```

### Script Docker Compose
[Docker-compose.yml](https://gist.github.com/JpEscobarM/629b7d2d6ec9665e2dea6e42a993fa75).
