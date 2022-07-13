package com.meli.mutant.domain.repository;

import com.meli.mutant.domain.DnaSequenceDomain;

import java.util.List;


public interface DnaSequenceDomainRepository {
    DnaSequenceDomain save(DnaSequenceDomain dnaSequenceDomain);
    List<String> getByDna(List<String> dna);

}
