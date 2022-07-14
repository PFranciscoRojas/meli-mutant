package com.meli.mutant.persistence.mapper;

import com.meli.mutant.domain.model.StatModel;
import com.meli.mutant.persistence.entity.Stat;
import com.meli.mutant.web.dto.StatDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StatMapper {

    @Mappings({
            @Mapping(source = "id",target = "idStat"),
            @Mapping(source = "count_mutant_dna",target = "countMutantDna"),
            @Mapping(source = "count_human_dna",target = "countHumanDna"),
            @Mapping(source = "ratio",target = "ratioStat")
    })
    StatModel toStatModel(Stat stat);

    List<StatModel> toStatsModel(List<Stat> stats);
    @Mappings({
            @Mapping(source = "countMutantDna",target = "count_mutant_dna"),
            @Mapping(source = "countHumanDna",target = "count_human_dna"),
            @Mapping(source = "ratioStat",target = "ratio")
    })
    StatDto toStatDto(StatModel statModel);
    List<StatDto> toStatsDto(List<Stat> stats);

    @InheritInverseConfiguration
    Stat toStat(StatModel statModel);
}
