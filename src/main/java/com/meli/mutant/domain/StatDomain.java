package com.meli.mutant.domain;

public class StatDomain {

    private String idStat;
    private Integer countMutantDna;
    private Integer countHumanDna;
    private Double ratioStat;

    public String getIdStat() {
        return idStat;
    }

    public void setIdStat(String idStat) {
        this.idStat = idStat;
    }

    public Integer getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(Integer countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public Integer getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(Integer countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public Double getRatioStat() {
        return ratioStat;
    }

    public void setRatioStat(Double ratioStat) {
        this.ratioStat = ratioStat;
    }
}
