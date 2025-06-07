package io.github.mambichnaya.spamadmin.dto;

import lombok.Data;

@Data
public class SettingsDto {
    private Long chatId;
    private Long adminId;
    private boolean usernameCheck;
    private boolean dbCheck;
    private boolean photoCheck;
    private boolean bioCheck;
    private boolean messageCheck;
}
