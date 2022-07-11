package com.meli.mutant.persistence.crud;

import com.meli.mutant.persistence.entity.Stat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatCrudRepository extends MongoRepository<Stat,String> {


}
