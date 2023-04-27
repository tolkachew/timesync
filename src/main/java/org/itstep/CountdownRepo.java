package org.itstep;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountdownRepo extends JpaRepository<Countdown, Long> {
}