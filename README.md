# Shipping Service

The Shipping Service is implemented as a multi-module project containing the following modules:

1. **[shipping-core](./shipping-core)** contains the bulk of the service implementation, including
domain model, the REST service itself, as well as the data repository abstraction and its 
in-memory implementation;

2. **[shipping-atpsoda](./shipping-atpsoda)** contains the data repository implementation for 
Oracle ATP JSON (Soda) backend backend


## API

The service exposes REST API on port 7001. 

TBD (add OpenAPI support/link)

## Add ATP Soda jar files to the maven project

In order to build all the modules and create Docker images for the service, simply run the 
following commands inside the top-level `catalog` directory:

```bash
$vi ./shipping-atpsoda/src/main/resources/add_external_jars.sh

change the path to sockshop-catalog 
e.g. SOCKSHOP_CATALOG_ATPSODA=$HOME/helidon/final-code/sockshop-shipping/shipping-atpsoda

$ ./shipping-atpsoda/src/main/resources/add_external_jars.sh
```
## Building the service

In order to build all the modules and create Docker images for the service, simply run the 
following commands inside the top-level `shipping` directory:

```bash
$ mvn clean install
$ mvn package -Pdocker -DskipTests
``` 

The first command will build all the modules, run unit and integration tests, and install the
artifacts that need to be included into the Docker images into the local Maven repo.

The second command will then package those artifacts, and all of their dependencies, into
the local Docker image with the same names as the corresponding module (one image per module).

You can then manually push generated image to a Docker repository of your choice in order
to make it available to other environments.

Alternatively, you can build and push the image directly to a remote Docker repository by
running the following command instead:

```bash
$ mvn package -Pdocker -DskipTests -Ddocker.repo=<your_docker_repo> -Djib.goal=build
```

You should replace `<your_docker_repo>` in the command above with the name of the 
Docker repository that you can push images to.

## Running the service

Please refer to documentation for the individual modules above to find out how to run
different implementations of this service, including the backends they require, for local
testing.

To learn how to run the service in Kubernetes, as part of a larger Sock Shop application,
please refer to the [main documentation page](../sockshop/README.md).

## License

The Universal Permissive License (UPL), Version 1.0
