# akasia-exschoolapp
## Akasia School Extracurricular App

### Overview
1. Demo Running Application
Link : https://drive.google.com/file/d/1nAGPATgQ_YYIWhK95qSPOqApOw0IBaRu/view?usp=sharing

2. Project Architecture
![architecture](https://i.postimg.cc/HxkKMcWt/microservices-akasia-exschool.png)

3. Angular Client
![admin](https://i.postimg.cc/SNhFKsFJ/angular-client.png)

### Prerequisite

1. Check ng version
   ```
   ng version
   Angular CLI: 15.2.5
   ```
2. Check node version
   ```
   node -v
   v18.10.0
   ```
3. Check npm version
   ```
   npm -v
   8.19.2
   ```
4. Check docker version
   ```
   docker --version
   Docker version 20.10.23, build 7155243
   ```

### Installation Steps

1. Clone this repo
   ```
   git clone https://github.com/mademanik/akasia-exschoolapp.git
   ```

#### Running Springboot Backend Server
2. cd into exschoolapp-backend
   ```
   cd exschoolapp-backend
   ```
3. run docker compose command
   ```
   docker compose up -d
   ```
4. running server done (wait until all docker service running)

#### Running Angular Frontend Client
5. run ng version to check if angular cli@15.2.5 has installed or not
   ```
   ng version
   ```
6. if angular cli not installed, then running below command to install
   ```
   npm install -g @angular/cli@15.2.5
   ```
7. cd into exschoolapp-frontend
   ```
   cd exschoolapp-frontend
   ```
8. run npm install or yarn to download package dependency
   ```
   npm i
   ```
9. run npm start to run angular client frontend
   ```
   npm start
   ```
10. open to port http://localhost:8081 on browser to open angular web page
11. running client done