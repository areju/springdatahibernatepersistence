package com.arjun.springhibernatejpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.arjun.springhibernatejpa.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
