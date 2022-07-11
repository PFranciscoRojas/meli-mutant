package com.meli.mutant.persistence;

import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.domain.repository.StatDomainRepository;
import com.meli.mutant.persistence.crud.StatCrudRepository;
import com.meli.mutant.persistence.entity.Stat;
import com.meli.mutant.persistence.mapper.StatMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatRepository implements StatDomainRepository {
    private StatCrudRepository statCrudRepository;
    private StatMapper mapper;

    public StatRepository(StatCrudRepository statCrudRepository, StatMapper statMapper) {
        this.statCrudRepository = statCrudRepository;
        this.mapper = statMapper;
    }

    @Override
    public List<StatDomain> getAll(){
        List<Stat> stats = statCrudRepository.findAll();
        return mapper.toStatsDomain(stats);
    }

}
