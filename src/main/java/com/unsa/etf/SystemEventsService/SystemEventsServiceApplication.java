package com.unsa.etf.SystemEventsService;

import com.unsa.etf.SystemEventsService.repository.SystemEventsRepository;
import com.unsa.etf.SystemEventsService.service.LoggingServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class SystemEventsServiceApplication {
	@Value("server.port")
	private static int grpcPort;

	private static SystemEventsRepository systemEventsRepository;

//	public static void main(String[] args) {
//		SpringApplication.run(SystemEventsServiceApplication.class, args);
//	}

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(SystemEventsServiceApplication.class, args);

		Server server = ServerBuilder
				.forPort(grpcPort)
				.addService(new LoggingServiceImpl(SystemEventsServiceApplication.systemEventsRepository))
				.build();
		server.start();
		System.out.println("gRPC server runing on port " + server.getPort());
		server.awaitTermination();
	}

}
