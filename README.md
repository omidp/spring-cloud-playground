== How to build

For each folder, execute this

```
mvn clean install -DskipTests=true
```


== How to run 

* start eureka
* start trx-service
* start gateway-service


```
http://localhost:8761/
```

```
http://localhost:8788/test //use feign
OR
http://localhost:8788/trx/trxApi
```

* stop trx-service

```
http://localhost:8788/test 
```
