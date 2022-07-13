package com.meli.mutant.domain.service;

import com.meli.mutant.domain.DnaSequenceDomain;
import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.domain.dto.StatDomainDto;
import com.meli.mutant.domain.repository.DnaSequenceDomainRepository;
import com.meli.mutant.domain.repository.StatDomainRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DnaSequenceDomainService {
    private DnaSequenceDomainRepository dnaSequenceDomainRepository;
    private final Log LOGGER = LogFactory.getLog(DnaSequenceDomainService.class);
    private StatDomainRepository statDomainRepository;

    private StatDomainService statDomainService;


    public DnaSequenceDomainService(DnaSequenceDomainRepository dnaSequenceDomainRepository, StatDomainRepository statDomainRepository, StatDomainService statDomainService) {
        this.dnaSequenceDomainRepository = dnaSequenceDomainRepository;
        this.statDomainRepository = statDomainRepository;
        this.statDomainService = statDomainService;
    }

    public boolean isMutant(String[] dna) {
        validateDnaSequence(dna);
        try {
            int quantitiyMutants = 0;
            int sizeRowsSequenceDna = dna.length;

            for (int i = 0; i < sizeRowsSequenceDna; i++) {
                //i-> Vertical j -> Horizontal
                int sizeColumnsSequenceDna = dna[i].length();
                for (int j = 0; j < sizeColumnsSequenceDna; j++) {

                    //Check sequence horizontal
                    if (j < sizeColumnsSequenceDna - 3) {

                        if (dna[i].charAt(j) == dna[i].charAt(j + 1)
                                && dna[i].charAt(j + 1) == dna[i].charAt(j + 2)
                                && dna[i].charAt(j + 2) == dna[i].charAt(j + 3)) {

                            quantitiyMutants++;

                        }
                    }

                    //Check sequence Vertical
                    if (i < sizeRowsSequenceDna - 3) {
                        if (dna[i].charAt(j) == dna[i + 1].charAt(j)
                                && dna[i + 1].charAt(j) == dna[i + 2].charAt(j)
                                && dna[i + 2].charAt(j) == dna[i + 3].charAt(j)) {
                            quantitiyMutants++;

                        }
                    }

                    //Check sequence Diagonal Left to Right
                    if (j < sizeColumnsSequenceDna - 3 && i < sizeRowsSequenceDna - 3) {

                        if (dna[i].charAt(j) == dna[i + 1].charAt(j + 1)
                                && dna[i + 1].charAt(j + 1) == dna[i + 2].charAt(j + 2)
                                && dna[i + 2].charAt(j + 2) == dna[i + 3].charAt(j + 3)) {
                            quantitiyMutants++;
                        }
                    }
                }

                //Check sequence Diagonal Right to Left
                for (int x = sizeColumnsSequenceDna; x > 3; x--) {
                    if (i < sizeRowsSequenceDna - 3) {

                        if (dna[i].charAt(x - 1) == dna[i + 1].charAt(x - 2)
                                && dna[i + 1].charAt(x - 2) == dna[i + 2].charAt(x - 3)
                                && dna[i + 2].charAt(x - 3) == dna[i + 3].charAt(x - 4)) {

                            quantitiyMutants++;
                        }
                    }
                }
                if (quantitiyMutants > 0) {
                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            throw (e);
        }

    }

    public void validateDnaSequence(String[] dna) {
        String regexATCGLetters = "[ATCG]+";
        int sequenceSize = 0;
        if (dna == null || dna.length % 2 != 0)
            throw new IllegalArgumentException("Sequence Dna mustn't be NULL and size dna should be even");
        for (String sequence : dna) {
            if (sequenceSize != sequence.length() && sequenceSize != 0) {
                throw new IllegalArgumentException("Sequence Dna contain strings with different size");
            }

            if (sequence.trim().equals("") || !sequence.matches(regexATCGLetters)) {
                throw new IllegalArgumentException("Sequence only accept characters[A - T - C - G] and shouldn't be empty");
            }

            sequenceSize = sequence.length();
        }
    }

    public boolean validateDnaDuplicate(List<String> dna) {

        List<String> dnaRepetido = dnaSequenceDomainRepository.getByDna(dna);
        if (dnaRepetido.isEmpty()) {
            return false;
        }
        return true;
    }


    public Optional<StatDomain> updateStats(boolean isMutant) {
        List<StatDomain> statsDomain = statDomainService.getAll();
        String idStatDomain = statsDomain.stream().map(StatDomain::getIdStat).findAny().orElseThrow();
        int countDnaHuman = statsDomain.stream().map(StatDomain::getCountHumanDna).findAny().orElseThrow();
        int countDnaMutant = statsDomain.stream().map(StatDomain::getCountMutantDna).findAny().orElseThrow();

        StatDomain statDomain = new StatDomain();
        if (isMutant) {
            statDomain.setCountMutantDna(countDnaMutant + 1);
            statDomain.setCountHumanDna(countDnaHuman);
        } else {
            statDomain.setCountMutantDna(countDnaMutant);
            statDomain.setCountHumanDna(countDnaHuman + 1);
        }
        if (countDnaHuman > 0) {
            statDomain.setRatioStat((double) countDnaHuman + 1 / (double) countDnaHuman + 1);
        }

        return statDomainRepository.updateStatsById(idStatDomain, isMutant, statDomain);
    }

    public DnaSequenceDomain saveDna(DnaSequenceDomain dnaSequenceDomain) {
        return dnaSequenceDomainRepository.save(dnaSequenceDomain);
    }
}
