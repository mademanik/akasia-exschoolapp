# akasia-exschoolapp
## Akasia School Extracurricular App

### Prerequisite

1. Check ng version
   ```
   ng version
   Angular CLI: 15.2.5
   ```
2. Check node version
   ```
   node -v
   v20.12.2
   ```
3. Check npm version
   ```
   npm -v
   10.5.0
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