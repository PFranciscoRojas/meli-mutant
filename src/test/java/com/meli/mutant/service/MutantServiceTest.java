package com.meli.mutant.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestEntityManager
public class MutantServiceTest {

    @Test
    public void parameter_dna_should_be_pair() {
        String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG" };


    }
}
