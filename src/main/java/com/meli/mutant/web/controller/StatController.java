package com.meli.mutant.web.controller;

import com.meli.mutant.domain.service.StatService;
import com.meli.mutant.web.dto.StatDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/")
public class StatController {
    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping("/stats")
    @ApiOperation("Get stats about quantity humans and mutants")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<StatDto> getStat() throws ExecutionException, InterruptedException {
        CompletableFuture<StatDto> future = statService.getStat();
        return new ResponseEntity<>(future.get(), HttpStatus.OK);
    }

}
