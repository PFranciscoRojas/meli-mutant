package com.meli.mutant.domain.repository;

import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.persistence.entity.Stat;

import java.util.List;

public interface StatDomainRepository {
    List<StatDomain> getAll();
}
