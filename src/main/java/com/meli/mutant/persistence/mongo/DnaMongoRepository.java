package com.meli.mutant.persistence.mongo;

import com.meli.mutant.persistence.entity.DnaSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DnaMongoRepository extends MongoRepository<DnaSequence,String> {
    DnaSequence findByDna(List<String> dna);
}
