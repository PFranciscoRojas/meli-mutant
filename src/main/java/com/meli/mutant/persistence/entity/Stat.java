package com.meli.mutant.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "stats_db")
public class Stat {
    @Id
    private String id;
    private Integer count_mutant_dna;
    private Integer count_human_dna;
    private Double ratio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public void setCount_mutant_dna(Integer count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    public Integer getCount_human_dna() {
        return count_human_dna;
    }

    public void setCount_human_dna(Integer count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}
