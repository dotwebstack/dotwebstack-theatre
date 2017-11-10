# DotWebStack Theatre

A reference implementation of the DotWebStack Framework, based on the most common standards. This project is the successor of the Linked Data Theatre 1.x.

[![Build Status](https://travis-ci.org/dotwebstack/dotwebstack-theatre.svg?branch=master)](https://travis-ci.org/dotwebstack/dotwebstack-theatre)

All generic dotwebstack documentation can be found in [https://github.com/dotwebstack/dotwebstack].

## Run application

### Configuration

DotWebStack Theatre needs several configuration files. Preferably they should be stored in a config directory. These config files are:
1. application properties file (e.g. application.yml). This file is not necessarily required, but most likely you would like to set the config dir and logging level.
2. all model/trig files required to configure your DotWebStack Theatre. They should be stored in the a model directory.
3. the Open API/Swagger file. This yml file should be stored in the openapi directory.

More information about the configuration can be found in [https://github.com/dotwebstack/dotwebstack].

### Run with profile (.jar)

Prerequisites:
- A config directory with configuration files (see above) in the startup directory.
- Java 8 installed.

```bash
$ java -jar -Dspring.profiles.active=development dotwebstack-theatre-0.0.1-SNAPSHOT.jar
```

This will run the application in Test profile mode.

```bash
$ java -jar dotwebstack-theatre-0.0.1-SNAPSHOT.jar
```

This will run the application in default profile mode (production).

### Run with prebuild docker image

An example setup can be found in examples/prebuild-docker-image. To run this (this configuration assumes there is a virtuoso backend running with container name virtuoso at port 8890) execute the following steps:

```bash
$ cd examples/prebuild-docker-image
$ docker run -i -p 80:8080 -v$PWD/config:/opt/config dotwebstack/dotwebstack-theatre
```

To verify if your configuration is running a GET request can be done (assuming you are at localhost) to http://localhost/dbp/api/v1/breweries:

```bash
$ curl http://localhost/dbp/api/v1/breweries
```

### Run with docker-compose and virtuoso

An example setup can be found in examples/prebuild-docker-image. This docker-compose contains 2 containers.
- virtuoso triple store
- dotwebstack

To run this example, do the following steps:
```bash
$ cd examples/dockercompose-with-virtuoso
$ docker-compose up
```

The above should work both on `*`nix systems and Windows (tested with Windows 7 and 10) but with Windows the following should be taken care of:
- On Windows 7, Docker Quickstart terminal should be started
- The composefile (including all sub dirs) should be placed somewhere in your home directory, otherwise the volume mapping is not working

To verify if your configuration is running a GET request can be done (assuming you are at localhost) to http://localhost/dbp/api/v1/breweries:

```bash
$ curl http://localhost/dbp/api/v1/breweries
```

## Release

To release a new version, run the following statement and follow instructions:

Set property dotwebstack.framework.version in the pom.xml to the newest stable dotwebstack framework version.

```
mvn release:prepare
```

Clean up afterwards:

```
mvn release:clean
```

Currently the docker hub automated build is not started after a new release (a bug) so the automated build has the started manually.
1. Goto https://hub.docker.com/r/dotwebstack/dotwebstack-theatre/~/settings/automated-builds/
2. Adapt last line: type=tag, name=vx.y.z, docker tag name=latest
3. Press trigger: now 2 builds should be started (last version and latest). Check build details tab.

## License

The DotWebStack Theatre is published under the [GNU GPLv3 License](LICENSE.md).
