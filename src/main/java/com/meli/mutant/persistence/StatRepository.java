package com.meli.mutant.persistence;

import com.meli.mutant.persistence.crud.StatCrudRepository;
import com.meli.mutant.persistence.entity.Stat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatRepository {
    private StatCrudRepository statCrudRepository;

    public List<Stat> getAll(){
        return statCrudRepository.findAll();
    }

    public Stat save(Stat stat){
        return statCrudRepository.save(stat);
    }
}
