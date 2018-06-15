# webapp
This application is developed for skills improvind wihtou commercial (so far) background. As for now, project is free to be modified by any member of the `multiheaded` team.

## Wiki
Small wiki for this project is mainted by the whole team. Wiki contains articles, schemes, scripts etc that should bot be forgotten.
Some interesting topics of wiki:

- [Tech Stack](https://bitbucket.org/multiheaded/webapp/wiki/Tech-Stack) - this page contains technologies that are used or we want them to be used within the project
- [Organisation](https://bitbucket.org/multiheaded/webapp/wiki/Organisation) - this one has small recommendations about tasks, management, git flow etc.
    - [Task tracking](https://bitbucket.org/multiheaded/webapp/addon/trello/trello-board)
    - [Issue tracking](https://bitbucket.org/multiheaded/webapp/issues?status=new&status=open)
- [Architecture](https://bitbucket.org/multiheaded/webapp/wiki/Achitecture) - has a word or two about infrustructure

## Docker
To make docker work it is necessary to perform following steps:

1. Install Docker from [docker.com](https://www.docker.com/)
2. Create Docker image using Dockerfile (we will create private repository that will download image instead of creating it)
    - `docker build -t webapp .`
3. Compose container from image and launch it (you can also setup IDEA Run configuration for that) 
    - `docker build -t webapp:latest . && docker run -p 4000:8080 --name webapp webapp:latest `
4. Open browser and navigate to [localhost:4000](http://localhost:4000/)