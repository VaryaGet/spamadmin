package io.github.mambichnaya.spamadmin.repo;

import io.github.mambichnaya.spamadmin.dto.SettingsDto;
import io.github.mambichnaya.spamadmin.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Chat findByTgId(Long chatId);
    Boolean existsByTgId(Long chatId);

    SettingsDto getByTgId(Long tgId);
}
