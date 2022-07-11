package com.meli.mutant.web;

import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.domain.service.StatDomainService;
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
    public ResponseEntity<List<StatDomain>> getAll(){
        return new ResponseEntity<>(statDomainService.getAll(), HttpStatus.OK);
    }

}
