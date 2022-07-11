package com.meli.mutant.persistence.crud;

import com.meli.mutant.persistence.entity.DnaSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DnaCrudRepository extends MongoRepository<DnaSequence,String> {
}
