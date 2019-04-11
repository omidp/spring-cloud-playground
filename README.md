=== How to build

For each folder, execute this

```
mvn clean install -DskipTests=true
```


=== How to run 

Use your favourite IDE to import the projects.

* start eureka
* start trx-service
* start qraphql-service
* start gateway-service


```
http://localhost:8761/
```

```
http://localhost:8788/ping
OR
http://localhost:8788/trx/trxApi //direct call
```

* stop trx-service

```
http://localhost:8788/trxApi //fiegn and hystrix
```


* open graphiql

```
http://localhost:8788/graphql
```

* content 

```
query{
  findTrxById(trxId:"1")
}
OR
query{
  findTrxByIdEx(trxId:"1")
}
```
