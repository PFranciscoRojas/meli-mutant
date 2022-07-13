package com.meli.mutant.persistence.crud;

import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.persistence.entity.Stat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StatCrudRepository extends MongoRepository<Stat,String> {

}
