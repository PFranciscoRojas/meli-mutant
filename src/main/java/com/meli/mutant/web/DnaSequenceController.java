package com.meli.mutant.web;

import com.meli.mutant.domain.DnaSequenceDomain;
import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.domain.dto.DnaSequenceDto;
import com.meli.mutant.domain.service.DnaSequenceDomainService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DnaSequenceController {
    private final DnaSequenceDomainService dnaSequenceDomainService;
    private final Log LOGGER = LogFactory.getLog(DnaSequenceDomainService.class);

    public DnaSequenceController(DnaSequenceDomainService dnaSequenceDomainService) {
        this.dnaSequenceDomainService = dnaSequenceDomainService;

    }

    @PostMapping("/mutant")
    public ResponseEntity<DnaSequenceDomain> isMutant(@RequestBody DnaSequenceDto dnaSequence) {
        LOGGER.debug("dnaSequence by Postman: " + dnaSequence);

        String[] dna = dnaSequence.getDna().toArray(new String[0]);
        boolean isDnaMutant = dnaSequenceDomainService.isMutant(dna);
        DnaSequenceDomain dnaSequenceDomain = new DnaSequenceDomain();
        dnaSequenceDomain.setDna(dna);
        dnaSequenceDomain.setMutant(isDnaMutant);
        LOGGER.debug("dnaSequenceDomain by Postman: "+ dnaSequenceDomain);

        if (isDnaMutant) {
            return new ResponseEntity<>(dnaSequenceDomainService.save(dnaSequenceDomain), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(dnaSequenceDomainService.save(dnaSequenceDomain), HttpStatus.FORBIDDEN);
        }
    }

}
