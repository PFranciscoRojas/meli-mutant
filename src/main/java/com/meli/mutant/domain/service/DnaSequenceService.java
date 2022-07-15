package com.meli.mutant.domain.service;

import com.meli.mutant.domain.model.DnaSequenceModel;
import com.meli.mutant.domain.model.StatModel;
import com.meli.mutant.domain.repository.DnaSequenceDomainRepository;
import com.meli.mutant.domain.repository.StatDomainRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.meli.mutant.web.exceptions.ExceptionDna;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DnaSequenceService {
    private final Log LOGGER = LogFactory.getLog(DnaSequenceService.class);
    private final StatDomainRepository statDomainRepository;
    private final DnaSequenceDomainRepository dnaSequenceDomainRepository;

    public DnaSequenceService(DnaSequenceDomainRepository dnaSequenceDomainRepository, StatDomainRepository statDomainRepository) {
        this.dnaSequenceDomainRepository = dnaSequenceDomainRepository;
        this.statDomainRepository = statDomainRepository;
    }

    public boolean isMutant(String[] dna) {
        LOGGER.info("DnaSequenceService method isMutant: " + Arrays.toString(dna));
        validateDnaSequence(dna);
        try {
            int quantityMutants = 0;
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
                            quantityMutants++;
                        }
                    }
                    //Check sequence Vertical
                    if (i < sizeRowsSequenceDna - 3) {
                        if (dna[i].charAt(j) == dna[i + 1].charAt(j)
                                && dna[i + 1].charAt(j) == dna[i + 2].charAt(j)
                                && dna[i + 2].charAt(j) == dna[i + 3].charAt(j)) {
                            quantityMutants++;
                        }
                    }
                    //Check sequence Diagonal Left to Right
                    if (j < sizeColumnsSequenceDna - 3 && i < sizeRowsSequenceDna - 3) {
                        if (dna[i].charAt(j) == dna[i + 1].charAt(j + 1)
                                && dna[i + 1].charAt(j + 1) == dna[i + 2].charAt(j + 2)
                                && dna[i + 2].charAt(j + 2) == dna[i + 3].charAt(j + 3)) {
                            quantityMutants++;
                        }
                    }
                }
                //Check sequence Diagonal Right to Left
                for (int x = sizeColumnsSequenceDna; x > 3; x--) {
                    if (i < sizeRowsSequenceDna - 3) {
                        if (dna[i].charAt(x - 1) == dna[i + 1].charAt(x - 2)
                                && dna[i + 1].charAt(x - 2) == dna[i + 2].charAt(x - 3)
                                && dna[i + 2].charAt(x - 3) == dna[i + 3].charAt(x - 4)) {
                            quantityMutants++;
                        }
                    }
                }
                if (quantityMutants > 0) {
                    return true;
                }
            }

            return false;

        } catch (RuntimeException e) {
            LOGGER.error("Error DnaSequenceService method isMutant: " + e);
            throw new RuntimeException(e);
        }

    }

    public void validateDnaSequence(String[] dna) {
        String regexATCGLetters = "[ATCG]+";
        int sequenceSize = 0;
        if (dna == null || dna.length % 2 != 0) {
            throw new ExceptionDna("Sequence Dna mustn't be NULL and size dna should be even");
        }
        for (String sequence : dna) {
            if (sequenceSize != sequence.length() && sequenceSize != 0) {
                throw new ExceptionDna("Sequence Dna contain strings with different size");
            }
            if (sequence.trim().equals("") || !sequence.matches(regexATCGLetters)) {

                throw new ExceptionDna("Sequence only accept characters[A - T - C - G] and shouldn't be empty");
            }
            if (sequence.length() != dna.length) {
                throw new ExceptionDna("Sequence Dna should be and array of NxN");
            }
            sequenceSize = sequence.length();
        }
    }

    public CompletableFuture<DnaSequenceModel> validateDnaDuplicate(List<String> dna) {
        return CompletableFuture.completedFuture(dnaSequenceDomainRepository.getByDna(dna));
    }

    @Async("asyncExecutor")
    public void updateStats(boolean isMutant) throws InterruptedException {
        try {
            StatModel statModel = statDomainRepository.getStat();
            LOGGER.debug("DnaSequenceService updateStats: " + statModel);

            if (statModel == null) {
                statModel = new StatModel(0, 0);
            }

            if (isMutant) {
                statModel.setCountMutantDna(statModel.getCountMutantDna() + 1);
            } else {
                statModel.setCountHumanDna(statModel.getCountHumanDna() + 1);
            }

            if (statModel.getCountHumanDna() > 0) {
                statModel.setRatioStat(Math.round(((double) statModel.getCountMutantDna()
                        / (double) statModel.getCountHumanDna()) * 100.0) / 100.0);
            }
            LOGGER.info("DnaSequenceService updateStats async starts ");
            statDomainRepository.updateStats(statModel);
            LOGGER.info("DnaSequenceService updateStats async completed");


        } catch (RuntimeException e) {
            LOGGER.error("DnaSequenceService updateStats: " + e);
        }
    }

    @Async("asyncExecutor")
    public CompletableFuture<DnaSequenceModel> saveDnaAndUpdateStats(DnaSequenceModel dnaSequenceModel, boolean isDnaMutant) throws InterruptedException {
        LOGGER.debug("DnaSequenceService saveDnaAndUpdateStats-> dnaSequenceModel: " + dnaSequenceModel
                + "isDnaMutant: " + isDnaMutant);
        DnaSequenceModel dnaSequenceFuture = dnaSequenceDomainRepository.save(dnaSequenceModel);
        updateStats(isDnaMutant);
        return CompletableFuture.completedFuture(dnaSequenceFuture);
    }
}
