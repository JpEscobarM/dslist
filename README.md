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

### Script Docker Compose
[Docker-compose.yml]([https://raw.githubusercontent.com/JpEscobarM/dslist/refs/heads/main/docker/docker-compose.yml](https://gist.github.com/JpEscobarM/629b7d2d6ec9665e2dea6e42a993fa75)).
