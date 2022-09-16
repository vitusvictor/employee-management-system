package com.ema.repository;

import com.ema.entity.TimeLog;
import com.ema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeLogRepository extends JpaRepository<TimeLog, Long> {
    TimeLog findTimeLogByUser(User user);
}
