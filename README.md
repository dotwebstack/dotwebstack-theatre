# DotWebStack Theatre

A reference implementation of the DotWebStack Framework, based on the most common standards. This project is the successor of the Linked Data Theatre 1.x.

## Run application

### Run with profile (.jar)

```bash
$ java -jar -Dspring.profiles.active=development dotwebstack-theatre-0.0.1-SNAPSHOT.jar
```

This will run the application in Test profile mode.

```bash
$ java -jar dotwebstack-theatre-0.0.1-SNAPSHOT.jar
```

This will run the application in default profile mode (production).

### Run with prebuild docker image

DotWebStack Theatre needs several configuration files. Preferably they should be stored in a config directory. These config files are:
1. application properties file (e.g. application.yml). This file is not necessarily required, but most likely you would like to set the config dir and logging level.
2. all model/trig files required to configure your DotWebStack Theatre. They should be stored in the a model directory.
3. the Open API/Swagger file. This yml file should be stored in the openapi directory.

An example setup can be found in examples/prebuild-docker-image. To run this (this configuration assumes there is a triple store running):

```bash
$ cd examples/prebuild-docker-image
$ docker run -p 80:8080 -v$PWD/config-docker:/opt/config ldt
```

### Run with docker-compose and virtuoso

An example setup can be found in examples/prebuild-docker-image. To run this (this configuration assumes there is a triple store running):

```bash
$ cd examples/dockercompose-with-virtuoso
$ docker-compose up
```

## License

The DotWebStack Theatre is published under the [GNU GPLv3 License](LICENSE.md).

