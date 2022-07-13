package com.meli.mutant.domain.dto;

public class StatDomainDto {

    private Integer countMutantDnaDto;
    private Integer countHumanDnaDto;
    private Double ratioStatDto;

    public Integer getCountMutantDnaDto() {
        return countMutantDnaDto;
    }

    public void setCountMutantDnaDto(Integer countMutantDnaDto) {
        this.countMutantDnaDto = countMutantDnaDto;
    }

    public Integer getCountHumanDnaDto() {
        return countHumanDnaDto;
    }

    public void setCountHumanDnaDto(Integer countHumanDnaDto) {
        this.countHumanDnaDto = countHumanDnaDto;
    }

    public Double getRatioStatDto() {
        return ratioStatDto;
    }

    public void setRatioStatDto(Double ratioStatDto) {
        this.ratioStatDto = ratioStatDto;
    }
}
