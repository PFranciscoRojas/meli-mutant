package com.meli.mutant.persistence.mapper;

import com.meli.mutant.domain.model.DnaSequenceModel;
import com.meli.mutant.persistence.entity.DnaSequence;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DnaSequenceMapper {
    @Mappings({
            @Mapping(source = "id",target = "idDnaSequence"),
            @Mapping(source = "dna",target = "dna"),
            @Mapping(source = "mutant",target = "mutant")
    })
    DnaSequenceModel toDnaSequenceDomain(DnaSequence dnaSequence);
    List<DnaSequenceModel> toDnaSequencesDomain(List<DnaSequenceModel> dnaSequences);
    @InheritInverseConfiguration
    DnaSequence toDnaSequence(DnaSequenceModel dnaSequenceModel);
}
