# testing-validation-objectmapper

Project for testing the crud application with kafka events of crud.

### Pre-Reqs:

Start the docker containers:

```sh
docker-compose up
```

Create the Topic:

```sh
docker ps 
CONTAINER ID        IMAGE                    COMMAND                  CREATED             STATUS              PORTS                                                NAMES
0d1693a6767c        wurstmeister/kafka       "start-kafka.sh"         About an hour ago   Up 32 seconds       0.0.0.0:32768->9092/tcp                              testing-validation-objectmapper_kafka_1
6f62cf5d0625        wurstmeister/zookeeper   "/bin/sh -c '/usr/sb…"   About an hour ago   Up 32 seconds       22/tcp, 2888/tcp, 3888/tcp, 0.0.0.0:2181->2181/tcp   testing-validation-objectmapper_zookeeper_1

docker exec -it 0d16 sh
kafka-topics --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic topic-test
kafka-topics --list --zookeeper zookeeper:2181
```

Send and Consume Message (AD HOC):

```sh
kafka-console-producer --broker-list localhost:9092 --topic test
kafka-console-consumer --bootstrap-server localhost:9092 --topic test --from-beginning
```

Registering schema:

```sh
cd web/src/main/resources
python3 register_schema.py http://localhost:8081 persons-avro persons.avsc
```

#### Application urls:

Zookeeper: localhost:2181

Broker: localhost:9092

Schema registry: localhost:8081

List of registries versions: http://localhost:8081/subjects/persons-avro-value/versions/

Application: localhost:8080

#### Useful Links:


[Docker images](https://github.com/confluentinc/cp-all-in-one)

[Schema registry](https://aseigneurin.github.io/2018/08/02/kafka-tutorial-4-avro-and-schema-registry.html)

[Python script for schema registry](https://gist.github.com/aseigneurin/5730c07b4136a84acb5aeec42310312c)
