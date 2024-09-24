package com.example.UserService.repository;

import com.example.UserService.model.Session;
import com.example.UserService.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends CrudRepository<Session, UUID> {

    Optional<Session> findByToken(String token);
}
