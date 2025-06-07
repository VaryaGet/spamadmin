package io.github.mambichnaya.spamadmin.dto;

import lombok.Data;

@Data
public class CopyChat {
    private Long chatFromId;
    private Long chatToId;
    private Long adminId;
}
