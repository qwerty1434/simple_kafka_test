package com.example.consumer.messagequeue;

import com.example.consumer.entity.UserEntity;
import com.example.consumer.repository.ConsumerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {
    ConsumerRepository repository;

    @Autowired
    public KafkaConsumer(ConsumerRepository repository){
        this.repository = repository;
    }

    // 리스너 연결
    @KafkaListener(topics= "SendUserUsingKafKa")
    public void saveData(String kafkaMessage){
        log.info("Kafka Message: ->"+kafkaMessage);
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object,Object>>(){});
        }catch (JsonProcessingException ex){
            ex.printStackTrace();
        }
        UserEntity entity = UserEntity.builder()
                .name((String)map.get("name"))
                .build();

        repository.save(entity);

    }

}
