package com.example.consumer.repository;

import com.example.consumer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<UserEntity,Long> {
}
