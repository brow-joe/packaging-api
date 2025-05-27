# packaging-api

Application uses [Java 21](https://docs.oracle.com/en/java/javase/21/), [Spring boot 3.5.0](https://spring.io/blog/2025/05/22/spring-boot-3-5-0-available-now/) and [gradle 8.13](https://docs.gradle.org/8.13/release-notes.html)


[Open docs](http://localhost:8080/api-docs)

[Open swagger](http://localhost:8080/swagger-ui.html)


## Default credentials:

* username: `guest`

* password: `guest`


### Environment variables

| Environment variable | Default value  |
|----------------------|----------------|
| API_PORT             | 8080           |
| API_USER             | guest          |
| API_PASSWORD         | guest          |

### Docker

Preconfigured databese for build in docker-compose

```sh
$ docker build -t packaging-app .
$ docker run -d -p 8080:8080 packaging-app
```

### Security

Spring security configured, all requests pass through the spring security interface, it is possible to change the access credentials through environment variables. The default credentials are **username**: guest **password**: guest

| Environment variable |
|----------------------|
| API_USER             |
| API_PASSWORD         |


### Architecture
Architecture based on [Onion Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)


### Tests

#### Unit

* [Junit5](https://junit.org/junit5/)
* [Mockito](https://site.mockito.org/)
* [Spring-boot-starter-test](https://docs.spring.io/spring-boot/docs/1.5.7.RELEASE/reference/html/boot-features-testing.html)


#### Integration

* [io.karatelabs](https://www.karatelabs.io/)