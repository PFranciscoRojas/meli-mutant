package com.meli.mutant.domain.service;

import com.meli.mutant.domain.repository.DnaSequenceDomainRepository;
import com.meli.mutant.domain.repository.StatDomainRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class DnaSequenceDomainServiceTest {

    DnaSequenceDomainRepository dnaSequenceDomainRepository = Mockito.mock(DnaSequenceDomainRepository.class);
    StatDomainRepository statDomainRepository = Mockito.mock(StatDomainRepository.class);
    StatDomainService statDomainService = Mockito.mock(StatDomainService.class);
    DnaSequenceDomainService dnaSequenceDomainService = new DnaSequenceDomainService(dnaSequenceDomainRepository,statDomainRepository,statDomainService);

    @Test
    public void be_false_when_sequence_dna_is_null() {

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            dnaSequenceDomainService.validateDnaSequence(null);
        });
        Assertions.assertEquals("Sequence Dna mustn't be NULL and size dna should be even", thrown.getMessage());

    }

    @Test
    public void be_false_when_sequence_dna_is_empty() {

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String[] dna = {"", " ", "       ", "  ", "    ","                 "};
            dnaSequenceDomainService.validateDnaSequence(dna);
        });
        Assertions.assertEquals("Sequence only accept characters[A - T - C - G] and shouldn't be empty", thrown.getMessage());
    }
    @Test
    public void be_false_when_sequence_dna_is_not_even_size() {

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG","CCCCTA"};
            dnaSequenceDomainService.validateDnaSequence(dna);
        });
        Assertions.assertEquals("Sequence Dna mustn't be NULL and size dna should be even", thrown.getMessage());
    }
    @Test
    public void be_false_when_sequence_dna_contains_character_diferents_to_A_T_C_G() {

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String[] dna = {"ATGCGA", "CACCGC", "TTATGT", "AGAYYG","TCACTG","CCCCTA"};
            dnaSequenceDomainService.validateDnaSequence(dna);
        });
        Assertions.assertEquals("Sequence only accept characters[A - T - C - G] and shouldn't be empty", thrown.getMessage());
    }
    @Test
    public void be_false_when_sequence_dna_contains_string_with_diferent_size() {

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String[] dna = {"ATGCGA", "CACCCT", "TTATGT", "AGAG","TCACTG","CCCCTA"};
            dnaSequenceDomainService.validateDnaSequence(dna);
        });
        Assertions.assertEquals("Sequence Dna contain strings with different size", thrown.getMessage());
    }

    @Test
    public void be_mutant_if_sequence_dna_has_more_than_4_equal_letters_horizontal() {
        String[] dna = {"ATGCGA", "CCCCTA", "TTATGT", "CAGTGC","CACCTC","AGAATG"};
        assertTrue(dnaSequenceDomainService.isMutant(dna));
    }

    @Test
    public void be_mutant_if_sequence_dna_has_more_than_4_equal_letters_vertical() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "CAGTGC","CACCGC","AGAAGG"};
        assertTrue(dnaSequenceDomainService.isMutant(dna));
    }

    @Test
    public void be_mutant_if_sequence_dna_has_more_than_4_equal_letters_diagonal_left_to_right() {
        String[] dna = {"CTGCGA", "CAGTTC", "TTATGT", "CAGAGC","CACCAC","AGAAGG"};
        assertTrue(dnaSequenceDomainService.isMutant(dna));
    }

    @Test
    public void be_mutant_if_sequence_dna_has_more_than_4_equal_letters_diagonal_right_to_left() {
        String[] dna = {"CTGCGA", "CACTTC", "TCATGT", "CAGGGC","CACCAC","AGAAGG"};
        assertTrue(dnaSequenceDomainService.isMutant(dna));
    }
}