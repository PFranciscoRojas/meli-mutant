package com.meli.mutant.domain.repository;

import com.meli.mutant.persistence.entity.Stat;

import java.util.List;

public interface StatRepository {
    List<Stat> getAll();
    Stat save(Stat stat);
}
