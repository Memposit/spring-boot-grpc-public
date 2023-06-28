package com.memposit.grpc.server.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import com.memposit.grpc.HealthCheckGrpc;
import com.memposit.grpc.HealthCheckGrpc.HealthCheckImplBase;
import com.memposit.grpc.HealthCheckRequest;
import com.memposit.grpc.HealthCheckResponse;
import com.memposit.grpc.HealthCheckResponse.ServingStatus;

import static com.memposit.grpc.HealthCheckResponse.ServingStatus.SERVING;
import static com.memposit.grpc.HealthCheckResponse.ServingStatus.SERVICE_UNKNOWN;

@GrpcService
public class HealthCheckServiceImpl extends HealthCheckImplBase {

  @Override
  public void check(HealthCheckRequest request,
      StreamObserver<HealthCheckResponse> responseObserver) {
    HealthCheckResponse response = HealthCheckResponse.newBuilder()
        .setStatus(getServingStatus(request)).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void watch(HealthCheckRequest request,
      StreamObserver<HealthCheckResponse> responseObserver) {
    HealthCheckResponse response = HealthCheckResponse.newBuilder()
        .setStatus(getServingStatus(request)).build();
    responseObserver.onNext(response);
  }

  private ServingStatus getServingStatus(HealthCheckRequest request) {
    return request.getService().equals(HealthCheckGrpc.SERVICE_NAME) ? SERVING : SERVICE_UNKNOWN;
  }
}
