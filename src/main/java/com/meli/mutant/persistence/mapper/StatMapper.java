package com.meli.mutant.persistence.mapper;

import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.persistence.entity.Stat;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface StatMapper {

    @Mappings({
            @Mapping(source = "id",target = "idStat"),
            @Mapping(source = "count_mutant_dna",target = "countMutantDna"),
            @Mapping(source = "count_human_dna",target = "countHumanDna"),
            @Mapping(source = "ratio",target = "ratioStat"),
    })
    StatDomain toStatDomain(Stat stat);

    @InheritInverseConfiguration
    Stat toStat(StatDomain statDomain);
}
