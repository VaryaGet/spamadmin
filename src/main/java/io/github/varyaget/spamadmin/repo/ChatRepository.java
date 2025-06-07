package io.github.varyaget.spamadmin.repo;

import io.github.varyaget.spamadmin.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Chat findByTgId(Long chatId);
    Boolean existsByTgId(Long chatId);

    Chat getByTgId(Long tgId);
}
