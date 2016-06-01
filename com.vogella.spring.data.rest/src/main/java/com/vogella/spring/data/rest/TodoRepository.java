package com.vogella.spring.data.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="tasks", path="tasks")
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
