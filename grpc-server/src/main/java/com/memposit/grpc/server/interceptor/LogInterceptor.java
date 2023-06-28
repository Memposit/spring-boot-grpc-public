package com.memposit.grpc.server.interceptor;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;

import static io.grpc.Grpc.TRANSPORT_ATTR_REMOTE_ADDR;

@Slf4j
public class LogInterceptor implements ServerInterceptor {

  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
      Metadata headers, ServerCallHandler<ReqT, RespT> next) {
    log.debug("==> Request from {} to '{}', type {}.",
        call.getAttributes().get(TRANSPORT_ATTR_REMOTE_ADDR),
        call.getMethodDescriptor().getFullMethodName(), call.getMethodDescriptor().getType());
    return next.startCall(call, headers);
  }
}
