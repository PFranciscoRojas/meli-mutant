package com.meli.mutant.domain.service;

import com.meli.mutant.domain.repository.StatDomainRepository;
import com.meli.mutant.persistence.mapper.StatMapper;
import com.meli.mutant.web.dto.StatDto;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class StatService {
    private final StatDomainRepository statDomainRepository;
    private final StatMapper mapper;
    public StatService(StatDomainRepository statDomainRepository, StatMapper mapper) {
        this.statDomainRepository = statDomainRepository;
        this.mapper = mapper;
    }

    public CompletableFuture<StatDto> getStat(){
        StatDto statFuture = mapper.toStatDto(statDomainRepository.getStat());
        return CompletableFuture.completedFuture(statFuture);
    }

}
