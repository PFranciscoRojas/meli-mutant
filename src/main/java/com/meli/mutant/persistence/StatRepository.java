package com.meli.mutant.persistence;

import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.domain.dto.StatDomainDto;
import com.meli.mutant.domain.repository.StatDomainRepository;
import com.meli.mutant.persistence.crud.StatCrudRepository;
import com.meli.mutant.persistence.entity.Stat;
import com.meli.mutant.persistence.mapper.StatMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StatRepository implements StatDomainRepository {
    private final StatCrudRepository statCrudRepository;
    private final StatMapper mapper;

    public StatRepository(StatCrudRepository statCrudRepository, StatMapper statMapper) {
        this.statCrudRepository = statCrudRepository;
        this.mapper = statMapper;
    }

    @Override
    public List<StatDomainDto> getAllDto() {
        List<Stat> stats = statCrudRepository.findAll();
        return mapper.toStatsDomainDto(stats);
    }

    @Override
    public List<StatDomain> getAll() {
        List<Stat> stats = statCrudRepository.findAll();
        return mapper.toStatsDomain(stats);
    }

    @Override
    public Optional<StatDomain> updateStatsById(String id, boolean isMutant,StatDomain newStatDomain) {

        return Optional.of(
                mapper.toStatDomain(
                        statCrudRepository.findById(id)
                                .map(stat -> {
                                    stat.setCount_mutant_dna(newStatDomain.getCountMutantDna());
                                    stat.setCount_human_dna(newStatDomain.getCountHumanDna());
                                    stat.setRatio(newStatDomain.getRatioStat());
                                    return statCrudRepository.save(stat);
                                }).get()
                )
        );

    }

    @Override
    public Optional<StatDomain> createStat(boolean isMutant, StatDomain statDomain) {
        Stat stat = mapper.toStat(statDomain);
        return Optional.ofNullable(mapper.toStatDomain(statCrudRepository.save(stat)));
    }
}
