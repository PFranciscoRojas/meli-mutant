package com.meli.mutant.domain.service;

import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.domain.dto.StatDomainDto;
import com.meli.mutant.domain.repository.StatDomainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatDomainService {
    private final StatDomainRepository statDomainRepository;

    public StatDomainService(StatDomainRepository statDomainRepository) {
        this.statDomainRepository = statDomainRepository;
    }

    public List<StatDomainDto> getAllDto(){
        return statDomainRepository.getAllDto();
    }

    public List<StatDomain> getAll(){
        return statDomainRepository.getAll();
    }

}
