package com.meli.mutant.domain.repository;

import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.domain.dto.StatDomainDto;

import java.util.List;
import java.util.Optional;

public interface StatDomainRepository {
    List<StatDomainDto> getAllDto();

    List<StatDomain> getAll();

    Optional<StatDomain> updateStatsById(String id, boolean isMutant,StatDomain statDomain);
    Optional<StatDomain> createStat(boolean isMutant,StatDomain statDomain);

}
