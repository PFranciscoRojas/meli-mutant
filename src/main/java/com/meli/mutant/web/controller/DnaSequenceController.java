package com.meli.mutant.web.controller;

import com.meli.mutant.domain.model.DnaSequenceModel;
import com.meli.mutant.persistence.entity.DnaSequence;
import com.meli.mutant.web.dto.DnaSequenceDto;
import com.meli.mutant.domain.service.DnaSequenceService;
import com.meli.mutant.web.exceptions.ExceptionDna;
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
    private final DnaSequenceService dnaSequenceService;
    private final Log LOGGER = LogFactory.getLog(DnaSequenceService.class);

    public DnaSequenceController(DnaSequenceService dnaSequenceService) {
        this.dnaSequenceService = dnaSequenceService;
    }

    @PostMapping("/mutant")
    @ApiOperation(value = "Save DNA Mutant or Human", notes = "Ex: 'AACAGA', 'AACCTA','TTCTGT', 'CAGTGC','CCTCTC','AGAATG'")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK (Is a Mutant)"),
            @ApiResponse(code = 403, message = "FORBIDDEN (Is A Human)")
    })
    public ResponseEntity<DnaSequenceModel> isMutant(@RequestBody DnaSequenceDto dnaSequence) throws ExceptionDna {

        String[] dna = dnaSequence.getDna().toArray(new String[0]);
        boolean isDnaMutant;
        DnaSequenceModel currentDnaSequence = dnaSequenceService.validateDnaDuplicate(dnaSequence.getDna());

        if (currentDnaSequence != null) {
            isDnaMutant = currentDnaSequence.getMutant();

        }else{

            isDnaMutant = dnaSequenceService.isMutant(dna);
            DnaSequenceModel dnaSequenceModel = new DnaSequenceModel();
            dnaSequenceModel.setDna(dnaSequence.getDna());
            dnaSequenceModel.setMutant(isDnaMutant);
            dnaSequenceService.saveDnaAndUpdateStats(dnaSequenceModel, isDnaMutant);

        }

        if (isDnaMutant) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
