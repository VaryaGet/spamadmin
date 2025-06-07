package io.github.mambichnaya.spamadmin.dto;

import lombok.Data;

@Data
public class AddChatDto {
    private Long chatId;
    private Long adminId;
}
