package com.memposit.grpc.server.configuration;

import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.context.annotation.Configuration;

import com.memposit.grpc.server.interceptor.LogInterceptor;

@Configuration(proxyBeanMethods = false)
public class InterceptorConfiguration {

  @GrpcGlobalServerInterceptor
  LogInterceptor logInterceptor() {
    return new LogInterceptor();
  }

}
