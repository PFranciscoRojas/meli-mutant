package com.meli.mutant.persistence.crud;

import com.meli.mutant.persistence.entity.DnaSequence;
import com.meli.mutant.persistence.entity.Stat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DnaCrudRepository extends MongoRepository<DnaSequence,String> {
    List<String> findByDna(List<String> dna);
}
