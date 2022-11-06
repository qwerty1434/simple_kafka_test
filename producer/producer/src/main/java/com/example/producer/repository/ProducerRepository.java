package com.example.producer.repository;

import com.example.producer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<UserEntity,Long> {
}
