package io.github.mambichnaya.spamadmin.repo;

import io.github.mambichnaya.spamadmin.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
