# webapp

This is a repository for test collaboration and development of Java based web application.

## Technologies that are to be used within project:
### Coding

- B-E:
    - JDK 8+
    - Spring Boot
    - Spring MVC
    - Gradle 4.6+ _OR_ Gradle Wrapper
- F-E:
    - HTML5/CSS3 _OR_ Twitter Bootstrap
    - React Native _OR_ React App
    - JavaScript _OR_ TypeScript
  
### Infrastructure
- VM:
    - Docker containers
    - Docker registry (remote images repo)
    - Dockerfile
- Host:
    - Nginx to deliver request to the Docker
- Tasks:
    - Jenkins for deploy and schedule tasks?
- DB:
    - H2 _OR_ Oracle for rellational DB
    - Reddis for cache
  
## Documenting and task tracking
Well, documenting will be handled using `/** */` (xo-xo) and BitBucket's WIKI. I would recommend posting some configurational scripts, deploy and build issues.

Trask tracking is necessary if we want to remember a little bit more than `TODO: Remove this pile of crap before deploy` in the code. For tracking I recommend using Trello, as it is built into BitBucket.

## Architecture
A word about architecture - I assume it should be something as integrated into Java B-E React code snippets, that will be processed using Thymeleaf templates. Suppose, this one should describe a thing or two [Boop!](https://spring.io/guides/tutorials/react-and-spring-data-rest/)