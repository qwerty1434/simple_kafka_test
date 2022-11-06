package com.example.producer.controller;

import com.example.producer.entity.UserEntity;
import com.example.producer.messagequeue.KafkaProducer;
import com.example.producer.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {
    ProducerRepository producerRepository;
    KafkaProducer kafkaProducer;

    @Autowired
    public UserController(ProducerRepository producerRepository, KafkaProducer kafkaProducer) {
        this.producerRepository = producerRepository;
        this.kafkaProducer = kafkaProducer;
    }



    @PostMapping("/Signin")
    public void createUser(@RequestBody UserEntity userEntity){
        producerRepository.save(userEntity);
        // 여기 이름은 consumer에서 Listener에 등록한 토픽이름과 동일해야 함
        kafkaProducer.send("SendUserUsingKafKa",userEntity);
    }

}
