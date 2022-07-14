package com.meli.mutant.domain.service;

import com.meli.mutant.domain.model.StatModel;
import com.meli.mutant.domain.repository.StatDomainRepository;
import com.meli.mutant.persistence.mapper.StatMapper;
import com.meli.mutant.web.dto.StatDto;
import org.springframework.stereotype.Service;

@Service
public class StatService {
    private final StatDomainRepository statDomainRepository;
    private final StatMapper mapper;

    public StatService(StatDomainRepository statDomainRepository, StatMapper mapper) {
        this.statDomainRepository = statDomainRepository;
        this.mapper = mapper;
    }

    public StatDto getStat(){
        return mapper.toStatDto(statDomainRepository.getStat());
    }

}
