package com.meli.mutant.domain.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestEntityManager
class DnaSequenceDomainServiceTest {
    private DnaSequenceDomainService dnaSequenceDomainService = new DnaSequenceDomainService();

    @Test
    public void be_false_when_sequence_dna_is_null() {
        assertFalse(dnaSequenceDomainService.validateDnaSequence(null));
    }

    @Test
    public void be_false_when_sequence_dna_is_empty() {
        String[] dna = {"", " ", "       ", "  ", "    ","                 "};
        assertFalse(dnaSequenceDomainService.validateDnaSequence(dna));
    }
    @Test
    public void be_false_when_sequence_dna_is_not_even_size() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG","CCCCTA"};
        assertFalse(dnaSequenceDomainService.validateDnaSequence(dna));
    }
    @Test
    public void be_false_when_sequence_dna_contains_character_diferents_to_A_T_C_G() {
        String[] dna = {"ATGCGA", "CACCGC", "TTATGT", "AGAYYG","TCACTG","CCCCTA"};
        assertFalse(dnaSequenceDomainService.validateDnaSequence(dna));
    }
    @Test
    public void be_false_when_sequence_dna_contains_string_with_diferent_size() {
        String[] dna = {"ATGCGA", "CACCCT", "TTATGT", "AGAG","TCACTG","CCCCTA"};
        assertFalse(dnaSequenceDomainService.validateDnaSequence(dna));
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