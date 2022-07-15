package com.meli.mutant.persistence.mongo;

import com.meli.mutant.persistence.entity.Stat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatMongoRepository extends MongoRepository<Stat,String> {

}
