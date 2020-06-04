# SWENG894
The full repository for the SWENG894 Group 4 Summer 2020 project.

![Frontend](https://github.com/jchbrown/SWENG894/workflows/Frontend%20CI/badge.svg)

![Backend](https://github.com/jchbrown/SWENG894/workflows/Backend%20CI/badge.svg)

## Setup a Local Development Enviroment

### Setup a .env file to define enviroment variables
Copy the `example.env` file and rename it `.env`. Change the enviromental variables if necessary.


### Start up the containers
If running Windows, make sure that Docker File Sharing is enabled over the folder that houses the application. To do so:
1. Go to Docker Desktop --> `Settings`
2. `Resources` --> `File Sharing`
3. Add `SWENG` folder, then click `Apply & Restart`.

Run the following command in you preferred shell to start up the database, backend, and frontend. (Windows: you must use Powershell)
```
docker-compose up
```

### Stop the containers
Run the following command to stop all the containers.
```
docker-compose down
```
### Completely clean docker from the system
Run the following commands to stop and remove all containers
```
chmod u+x ./docker-clean
./docker-clean
```
