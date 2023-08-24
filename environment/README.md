# Setting a local environment for Rule Ideas Collector app

## MongoDB & Mongo Express

The [mongodb/docker-compose.yaml](mongodb/docker-compose.yaml) allows to start a dockerized MongoDB and the Mongo Express Web UI.

### Start
- `cd mongodb`
- `docker-compose up` to start the containers
- `docker-compose down` to stop the containers

Access Mongo Express on http://localhost:8081 to manage the running MongoDB instance.

The [mongodb/init-mongo.js](mongodb/init-mongo.js) script is used to initialize the MongoDB instance.