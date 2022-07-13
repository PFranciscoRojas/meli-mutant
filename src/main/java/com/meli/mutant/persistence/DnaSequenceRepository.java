package com.meli.mutant.persistence;

import com.meli.mutant.domain.DnaSequenceDomain;
import com.meli.mutant.domain.repository.DnaSequenceDomainRepository;
import com.meli.mutant.persistence.crud.DnaCrudRepository;
import com.meli.mutant.persistence.entity.DnaSequence;
import com.meli.mutant.persistence.mapper.DnaSequenceMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DnaSequenceRepository implements DnaSequenceDomainRepository {

    private final DnaCrudRepository dnaCrudRepository;
    private final DnaSequenceMapper mapper;

    public DnaSequenceRepository(DnaCrudRepository dnaCrudRepository, DnaSequenceMapper mapper) {
        this.dnaCrudRepository = dnaCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public DnaSequenceDomain save(DnaSequenceDomain dnaSequenceDomain) {
        DnaSequence dnaSequence = mapper.toDnaSequence(dnaSequenceDomain);
        return mapper.toDnaSequenceDomain(dnaCrudRepository.save(dnaSequence));
    }

    @Override
    public List<String> getByDna(List<String> dna) {
        return dnaCrudRepository.findByDna(dna);
    }
}
