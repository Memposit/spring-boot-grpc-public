package com.memposit.grpc.client.service;

import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import com.memposit.grpc.HealthCheckGrpc;
import com.memposit.grpc.HealthCheckRequest;
import com.memposit.grpc.HealthCheckResponse;

@Service
public class HealthCheckClientService {

  @GrpcClient("healthCheck")
  private HealthCheckGrpc.HealthCheckBlockingStub healthCheckBlockingStub;

  public String check(String serviceName) {
    try {
      HealthCheckResponse response = this.healthCheckBlockingStub.check(
          HealthCheckRequest.newBuilder().setService(serviceName).build());
      return response.getStatus().toString();
    } catch (final StatusRuntimeException e) {
      return "FAILED with " + e.getStatus().getCode().name();
    }
  }
}
