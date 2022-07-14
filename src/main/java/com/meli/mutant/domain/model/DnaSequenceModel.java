package com.meli.mutant.domain.model;

import java.util.List;

public class DnaSequenceModel {

    private String idDnaSequence;
    private List<String> dna;
    private Boolean mutant;

    public String getIdDnaSequence() {
        return idDnaSequence;
    }

    public void setIdDnaSequence(String idDnaSequence) {
        this.idDnaSequence = idDnaSequence;
    }

    public List<String> getDna() {
        return dna;
    }

    public void setDna(List<String> dna) {
        this.dna = dna;
    }

    public Boolean getMutant() {
        return mutant;
    }

    public void setMutant(Boolean mutant) {
        this.mutant = mutant;
    }
}
