package com.unsa.etf.SystemEventsService.Model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table
@NoArgsConstructor
public class SystemEvent {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    private LocalDateTime timestamp;
    private String microservice;
    private String actionType;
    private String username;
    private String responseType;
    private String resourceName;

    public SystemEvent(LocalDateTime timestamp, String microservice, String actionType, String username, String responseType, String resourceName) {
        this.timestamp = timestamp;
        this.microservice = microservice;
        this.actionType = actionType;
        this.username = username;
        this.responseType = responseType;
        this.resourceName = resourceName;
    }
}
