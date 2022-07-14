package com.meli.mutant.domain.service;

import com.meli.mutant.domain.repository.DnaSequenceDomainRepository;
import com.meli.mutant.domain.repository.StatDomainRepository;
import com.meli.mutant.web.exceptions.ExceptionDna;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class DnaSequenceServiceTest {

    DnaSequenceDomainRepository dnaSequenceDomainRepository = Mockito.mock(DnaSequenceDomainRepository.class);
    StatDomainRepository statDomainRepository = Mockito.mock(StatDomainRepository.class);
    DnaSequenceService dnaSequenceService = new DnaSequenceService(dnaSequenceDomainRepository,statDomainRepository);

    /****
     *  DNA Structure
     *  */

    @Test
    public void be_exception_when_sequence_dna_is_null() {

        ExceptionDna thrown = Assertions.assertThrows(ExceptionDna.class, () -> {
            dnaSequenceService.validateDnaSequence(null);
        });
        Assertions.assertEquals("Sequence Dna mustn't be NULL and size dna should be even", thrown.getMessage());
    }
    @Test
    public void be_exception_when_sequence_dna_is_empty() {

        ExceptionDna thrown = Assertions.assertThrows(ExceptionDna.class, () -> {
            String[] dna = {"", " ", "       ", "  ", "    ","                 "};
            dnaSequenceService.validateDnaSequence(dna);
        });
        Assertions.assertEquals("Sequence only accept characters[A - T - C - G] and shouldn't be empty", thrown.getMessage());
    }
    @Test
    public void be_exception_when_sequence_dna_is_not_even_size() {

        ExceptionDna thrown = Assertions.assertThrows(ExceptionDna.class, () -> {
            String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG","CCCCTA"};
            dnaSequenceService.validateDnaSequence(dna);
        });
        Assertions.assertEquals("Sequence Dna mustn't be NULL and size dna should be even", thrown.getMessage());
    }
    @Test
    public void be_exception_when_sequence_dna_contains_character_diferents_to_A_T_C_G() {

        ExceptionDna thrown = Assertions.assertThrows(ExceptionDna.class, () -> {
            String[] dna = {"ATGCGA", "CACCGC", "TTATGT", "AGAYYG","TCACTG","CCCCTA"};
            dnaSequenceService.validateDnaSequence(dna);
        });
        Assertions.assertEquals("Sequence only accept characters[A - T - C - G] and shouldn't be empty", thrown.getMessage());
    }
    @Test
    public void be_exception_when_sequence_dna_contains_length_strings_and_length_array_are_differents() {

        ExceptionDna thrown = Assertions.assertThrows(ExceptionDna.class, () -> {
            String[] dna = {"ATGCGA", "CACCCT", "TTATGT", "TTATGT"};
            dnaSequenceService.validateDnaSequence(dna);
        });
        Assertions.assertEquals("Sequence Dna should be and array of NxN", thrown.getMessage());
    }

    @Test
    public void be_exception_when_sequence_dna_contains_string_with_diferent_size() {

        ExceptionDna thrown = Assertions.assertThrows(ExceptionDna.class, () -> {
            String[] dna = {"ATGCGA", "CACCCT", "TTATGT", "AGAG","TCACTG","CCCCTA"};
            dnaSequenceService.validateDnaSequence(dna);
        });
        Assertions.assertEquals("Sequence Dna contain strings with different size", thrown.getMessage());
    }


    /**
     * Cases when Dna Structure is a Mutant
     */
    @Test
    public void be_mutant_if_sequence_dna_has_more_than_4_equal_letters_horizontal() {
        String[] dna = {"ATGCGA", "CCCCTA", "TTATGT", "CAGTGC","CACCTC","AGAATG"};
        assertTrue(dnaSequenceService.isMutant(dna));
    }

    @Test
    public void be_mutant_if_sequence_dna_has_more_than_4_equal_letters_vertical() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "CAGTGC","CACCGC","AGAAGG"};
        assertTrue(dnaSequenceService.isMutant(dna));
    }

    @Test
    public void be_mutant_if_sequence_dna_has_more_than_4_equal_letters_diagonal_left_to_right() {
        String[] dna = {"CTGCGA", "CAGTTC", "TTATGT", "CAGAGC","CACCAC","AGAAGG"};
        assertTrue(dnaSequenceService.isMutant(dna));
    }

    @Test
    public void be_mutant_if_sequence_dna_has_more_than_4_equal_letters_diagonal_right_to_left() {
        String[] dna = {"CTGCGA", "CACTTC", "TCATGT", "CAGGGC","CACCAC","AGAAGG"};
        assertTrue(dnaSequenceService.isMutant(dna));
    }

    /**
     * Cases when Dna Structure is a Human
     */

    @Test
    public void be_mutant_if_sequence_dna_is_a_human_6x6() {
        String[] dna = {"CTGCGA", "CACTTC", "TGATGT", "CAGGGC","CACCAC","AGATTT"};
        assertFalse(dnaSequenceService.isMutant(dna));
    }

    @Test
    public void be_mutant_if_sequence_dna_is_a_human_4x4() {
        String[] dna = {"CTGC", "CACT", "TGAT", "GGGC"};
        assertFalse(dnaSequenceService.isMutant(dna));
    }



}