package com.memposit.grpc.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memposit.grpc.client.service.HealthCheckClientService;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {

  private final HealthCheckClientService healthCheckClientService;

  @GetMapping("/check")
  public ResponseEntity<String> check(@RequestParam String serviceName) {
    return new ResponseEntity<>(healthCheckClientService.check(serviceName), HttpStatus.OK);
  }
}
