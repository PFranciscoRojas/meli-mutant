package com.meli.mutant.web;

import com.meli.mutant.domain.StatDomain;
import com.meli.mutant.domain.repository.StatDomainRepository;
import com.meli.mutant.domain.service.StatDomainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class StatController {

    private StatDomainService statDomainService;

    public StatController(StatDomainService statDomainService) {
        this.statDomainService = statDomainService;
    }

    @GetMapping("/stats")
    public List<StatDomain> getAll(){
        return statDomainService.getAll();
    }

}
