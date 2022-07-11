package com.meli.mutant.web;

import com.meli.mutant.domain.DnaSequenceDomain;
import com.meli.mutant.domain.service.DnaSequenceDomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class DnaSequenceController {
    private final DnaSequenceDomainService dnaSequenceDomainService;

    public DnaSequenceController(DnaSequenceDomainService dnaSequenceDomainService) {
        this.dnaSequenceDomainService = dnaSequenceDomainService;
    }

    @PostMapping("/mutant")
    public ResponseEntity<DnaSequenceDomain> save(@RequestBody DnaSequenceDomain dnaSequenceDomain) {
        return new ResponseEntity<>(dnaSequenceDomainService.save(dnaSequenceDomain), HttpStatus.CREATED);
    }
}
