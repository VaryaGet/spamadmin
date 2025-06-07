package io.github.varyaget.spamadmin.repo;

import io.github.varyaget.spamadmin.dto.StatDto;
import io.github.varyaget.spamadmin.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    @Query("SELECT new io.github.varyaget.spamadmin.dto.StatDto(c.tgId,a.tgId, l.log,l.createdAt) FROM Log l join Chat c on l.chat.id = c.id join Admin a on l.admin.id = a.id WHERE l.chat.id in :chatIds")
    List<StatDto> findByChats(@Param("chatIds") Long[] chatIds);
}
