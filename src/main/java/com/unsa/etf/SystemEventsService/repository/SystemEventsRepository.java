package com.unsa.etf.SystemEventsService.repository;

import com.unsa.etf.SystemEventsService.Model.SystemEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemEventsRepository extends JpaRepository<SystemEvent, String> {
}
