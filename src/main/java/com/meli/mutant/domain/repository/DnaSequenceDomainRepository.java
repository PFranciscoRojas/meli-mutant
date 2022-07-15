package com.meli.mutant.domain.repository;

import com.meli.mutant.domain.model.DnaSequenceModel;
import java.util.List;

public interface DnaSequenceDomainRepository {
    DnaSequenceModel save(DnaSequenceModel dnaSequenceModel);
    DnaSequenceModel getByDna(List<String> dna);

}
