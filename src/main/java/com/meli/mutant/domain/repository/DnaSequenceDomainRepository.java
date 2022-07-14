package com.meli.mutant.domain.repository;

import com.meli.mutant.domain.model.DnaSequenceModel;
import com.meli.mutant.persistence.entity.DnaSequence;

import java.util.List;


public interface DnaSequenceDomainRepository {
    void save(DnaSequenceModel dnaSequenceModel);
    DnaSequenceModel getByDna(List<String> dna);

}
