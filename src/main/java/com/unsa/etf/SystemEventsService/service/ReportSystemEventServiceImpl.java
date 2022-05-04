package com.unsa.etf.SystemEventsService.service;

import com.unsa.etf.SystemEventsService.Model.SystemEvent;
import com.unsa.etf.SystemEventsService.ReportSystemEventGrpc;
import com.unsa.etf.SystemEventsService.SystemEventRequest;
import com.unsa.etf.SystemEventsService.SystemEventResponse;
import com.unsa.etf.SystemEventsService.repository.SystemEventsRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@GrpcService
@RequiredArgsConstructor
public class ReportSystemEventServiceImpl extends ReportSystemEventGrpc.ReportSystemEventImplBase {

    @Autowired
    private final SystemEventsRepository systemEventsRepository;

    @Override
    public void reportSystemEvent (SystemEventRequest systemEventRequest, StreamObserver<SystemEventResponse> responseObserver){
        try{
            SystemEvent systemEvent = new SystemEvent(null,
                    systemEventRequest.getMicroserviceName(),
                    systemEventRequest.getActionType());

            systemEventsRepository.save(systemEvent);

            SystemEventResponse response = SystemEventResponse.newBuilder().setResponseType("Successful!").build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }catch (Exception e){
            System.out.println(e.getMessage());
            SystemEventResponse response = SystemEventResponse.newBuilder().setResponseType("An error has occurred!").build();
        }
    }
}
