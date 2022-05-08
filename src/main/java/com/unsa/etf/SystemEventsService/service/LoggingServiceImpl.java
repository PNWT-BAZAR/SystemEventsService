package com.unsa.etf.SystemEventsService.service;

import com.unsa.etf.SystemEventsService.LogRequest;
import com.unsa.etf.SystemEventsService.LogResponse;
import com.unsa.etf.SystemEventsService.LoggingServiceGrpc;
import com.unsa.etf.SystemEventsService.Model.SystemEvent;
import com.unsa.etf.SystemEventsService.repository.SystemEventsRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@GrpcService
@RequiredArgsConstructor
public class LoggingServiceImpl extends LoggingServiceGrpc.LoggingServiceImplBase {

    @Autowired
    private final SystemEventsRepository systemEventsRepository;

    @Override
    public void loggingService (LogRequest systemEventRequest, StreamObserver<LogResponse> responseObserver){
        try{
            SystemEvent systemEvent = new SystemEvent(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC),
                    systemEventRequest.getMicroserviceName(),
                    systemEventRequest.getActionType(),
                    systemEventRequest.getUsername(),
                    systemEventRequest.getResponseType(),
                    systemEventRequest.getResourceName());

            systemEventsRepository.save(systemEvent);

            LogResponse response = LogResponse.newBuilder().setResponseType("Successful!").build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }catch (Exception e){
            System.out.println(e.getMessage());
            LogResponse response = LogResponse.newBuilder().setResponseType("An error has occurred!").build();
        }
    }
}
