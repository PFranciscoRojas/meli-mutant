package com.meli.mutant.persistence.repository;

import com.meli.mutant.domain.model.DnaSequenceModel;
import com.meli.mutant.domain.repository.DnaSequenceDomainRepository;
import com.meli.mutant.persistence.mongo.DnaMongoRepository;
import com.meli.mutant.persistence.entity.DnaSequence;
import com.meli.mutant.persistence.mapper.DnaSequenceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DnaSequenceRepository implements DnaSequenceDomainRepository {

    private final DnaMongoRepository dnaMongoRepository;
    private final DnaSequenceMapper mapper;

    public DnaSequenceRepository(DnaMongoRepository dnaMongoRepository, DnaSequenceMapper mapper) {
        this.dnaMongoRepository = dnaMongoRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(DnaSequenceModel dnaSequenceModel) {
        DnaSequence dnaSequence = mapper.toDnaSequence(dnaSequenceModel);
        mapper.toDnaSequenceDomain(dnaMongoRepository.save(dnaSequence));
    }

    @Override
    public DnaSequenceModel getByDna(List<String> dna) {
        return  mapper.toDnaSequenceDomain(dnaMongoRepository.findByDna(dna));
    }
}
