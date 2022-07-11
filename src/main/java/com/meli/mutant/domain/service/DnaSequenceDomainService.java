package com.meli.mutant.domain.service;

import com.meli.mutant.domain.DnaSequenceDomain;
import com.meli.mutant.domain.repository.DnaSequenceDomainRepository;
import org.springframework.stereotype.Service;

@Service
public class DnaSequenceDomainService {
   private final DnaSequenceDomainRepository dnaSequenceDomainRepository;

    public DnaSequenceDomainService(DnaSequenceDomainRepository dnaSequenceDomainRepository) {
        this.dnaSequenceDomainRepository = dnaSequenceDomainRepository;
    }

    public DnaSequenceDomain save(DnaSequenceDomain dnaSequenceDomain) {
        return dnaSequenceDomainRepository.save(dnaSequenceDomain);
    }
}
