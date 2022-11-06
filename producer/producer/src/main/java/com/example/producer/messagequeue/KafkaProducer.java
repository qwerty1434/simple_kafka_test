package com.example.producer.messagequeue;


import com.example.producer.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public UserEntity send(String topic, UserEntity userEntity){
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString =""; // 값을 전달하기 위해 String타입으로 변환
        try{
            jsonInString = mapper.writeValueAsString(userEntity);
        }catch(JsonProcessingException ex){
            ex.printStackTrace();
        }

        kafkaTemplate.send(topic,jsonInString);
        log.info("Kafka Producer sent data from the Order microservice: ->" + userEntity);

        return userEntity;

    }

}
