package com.meli.mutant.domain.repository;

import com.meli.mutant.domain.model.StatModel;

public interface StatDomainRepository {
    StatModel getStat();
    void updateStats(StatModel newStatModel);
    void createStat(StatModel statModel);

}
