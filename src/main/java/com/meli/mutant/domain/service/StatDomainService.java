package com.meli.mutant.domain.service;

import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.domain.repository.StatDomainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatDomainService {
    private final StatDomainRepository statDomainRepository;

    public StatDomainService(StatDomainRepository statDomainRepository) {
        this.statDomainRepository = statDomainRepository;
    }

    public List<StatDomain> getAll(){
        return statDomainRepository.getAll();
    }

}
