package com.fissionlab.coe.repository;

import com.fissionlab.coe.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, String> {
    List<Todo> findByCompleted(Boolean completed);
}
