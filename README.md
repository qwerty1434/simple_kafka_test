# simple_kafka_test
## 1. Download Kafka

## 2. Change Application.yml Setting
My Envs are `Windows10`, `Mysql`
```
server:
  port: 8081
spring:
  application:
    name: producer-service
  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true
    generate-ddl: true
  datasource:
    url: jdbc:mysql://localhost:3306/producer_db
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: my_id
    password: my_pw
```

## 3. Create Schema on Your Database
If you use Mysql Workbrench, type this in your Workbrench.
  - `create schema consumer_db;`
  - `create schema producer_db;`

## 4. Run Kafka
  - Run Zookeeper
      - windows: `./bin/windows/zookeeper-server-start.bat ./config/zookeeper.properties`
  - Run Kafka
      - windows: `./bin/windows/kafka-server-start.bat ./config/server.properties`

## 5. Test
- Send a `POST` request to `localhost:8081/Signin`
  - value with JSON format `{"name" : {your_variable}}`

![image](https://user-images.githubusercontent.com/25142537/200708727-30987c86-d94d-407e-b6ae-ea88135a798e.png)

- Check your DB
  - you just request only to producer(8081)
  - But both producer_db and consumer_db are have same data

![image](https://user-images.githubusercontent.com/25142537/200708933-73f56b20-1b27-4b19-883b-c1694178f72f.png)

