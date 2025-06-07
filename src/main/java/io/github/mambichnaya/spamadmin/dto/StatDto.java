package io.github.mambichnaya.spamadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StatDto {
    private Long chatId;
    private Long adminId;
    private String log;
    private LocalDateTime createdAt;

    public StatDto(Long chatId, Long adminId, String log, LocalDateTime createdAt) {
        this.chatId = chatId;
        this.adminId = adminId;
        this.log = log;
        this.createdAt = createdAt;
    }
}
