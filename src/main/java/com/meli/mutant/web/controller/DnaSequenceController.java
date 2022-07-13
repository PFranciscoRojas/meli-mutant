package com.meli.mutant.web.controller;

import com.meli.mutant.domain.DnaSequenceDomain;
import com.meli.mutant.domain.dto.DnaSequenceDto;
import com.meli.mutant.domain.service.DnaSequenceDomainService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
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
    @ApiOperation(value = "Save DNA Mutant or Human", notes = "Ex: 'AACAGA', 'AACCTA','TTCTGT', 'CAGTGC','CCTCTC','AGAATG'")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK (Is a Mutant)"),
            @ApiResponse(code = 403, message = "FORBIDDEN (Is A Human)")
    })
    public ResponseEntity<DnaSequenceDomain> isMutant(@RequestBody DnaSequenceDto dnaSequence) {

        String[] dna = dnaSequence.getDna().toArray(new String[0]);
        boolean alreadyExists = dnaSequenceDomainService.validateDnaDuplicate(dnaSequence.getDna());
        boolean isDnaMutant = dnaSequenceDomainService.isMutant(dna);
        if (!alreadyExists) {
            DnaSequenceDomain dnaSequenceDomain = new DnaSequenceDomain();
            dnaSequenceDomain.setDna(dnaSequence.getDna());
            dnaSequenceDomain.setMutant(isDnaMutant);
            dnaSequenceDomainService.saveDnaAndUpdateStats(dnaSequenceDomain, isDnaMutant);
        }

        if (isDnaMutant) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
