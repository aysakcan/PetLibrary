package com.example.demo.repo;

import com.example.demo.model.Pets;
import com.example.demo.model.Users;
import org.springframework.data.repository.CrudRepository;

import java.time.Period;


public interface PetRepo extends CrudRepository<Pets, Integer>{
   Iterable<Pets> findAll();
   Pets findById(int id);

}
