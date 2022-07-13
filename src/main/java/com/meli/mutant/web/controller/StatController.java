package com.meli.mutant.web.controller;

import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.domain.dto.StatDomainDto;
import com.meli.mutant.domain.service.StatDomainService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class StatController {

    private final StatDomainService statDomainService;

    public StatController(StatDomainService statDomainService) {
        this.statDomainService = statDomainService;
    }

    @GetMapping("/stats")
    @ApiOperation("Get stats about quantity humans and mutants")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<StatDomainDto>> getAllDto() {
        return new ResponseEntity<>(statDomainService.getAllDto(), HttpStatus.OK);
    }

}
