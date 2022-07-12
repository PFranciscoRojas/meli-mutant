package com.meli.mutant.domain.service;

import com.meli.mutant.domain.DnaSequenceDomain;
import com.meli.mutant.domain.repository.DnaSequenceDomainRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.logging.Logger;

@Service
public class DnaSequenceDomainService {
    private DnaSequenceDomainRepository dnaSequenceDomainRepository;
    private final Log LOGGER = LogFactory.getLog(DnaSequenceDomainService.class);

    public DnaSequenceDomainService(DnaSequenceDomainRepository dnaSequenceDomainRepository) {
        this.dnaSequenceDomainRepository = dnaSequenceDomainRepository;
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

    public boolean validateDnaSequence(String[] dna) {
        String regexATCGLetters = "[ATCG]+";
        int sequenceSize = 0;
        if (dna == null || dna.length % 2 != 0) return false;
        for (String sequence : dna) {
            if (sequenceSize != sequence.length() && sequenceSize != 0) {
                return false;
            }

            if (sequence.trim().equals("") || !sequence.matches(regexATCGLetters)) {
                return false;
            }

            sequenceSize = sequence.length();
        }
        return true;
    }


    public DnaSequenceDomain save(DnaSequenceDomain dnaSequenceDomain) {
        return dnaSequenceDomainRepository.save(dnaSequenceDomain);
    }
}
