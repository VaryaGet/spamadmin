package io.github.mambichnaya.spamadmin.controller;

import io.github.mambichnaya.spamadmin.dto.AddChatDto;
import io.github.mambichnaya.spamadmin.dto.ChatDto;
import io.github.mambichnaya.spamadmin.dto.CopyChat;
import io.github.mambichnaya.spamadmin.dto.SettingsDto;
import io.github.mambichnaya.spamadmin.entity.Admin;
import io.github.mambichnaya.spamadmin.entity.Chat;
import io.github.mambichnaya.spamadmin.entity.Log;
import io.github.mambichnaya.spamadmin.repo.AdminRepository;
import io.github.mambichnaya.spamadmin.repo.ChatRepository;
import io.github.mambichnaya.spamadmin.repo.LogRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@RequestMapping("/api/v1/settings")
@AllArgsConstructor
public class SettingController {
    private final AdminRepository adminRepository;
    private final ChatRepository chatRepository;
    private final LogRepository logRepository;

    @PostMapping("login")
    @Transactional
    public void save(@RequestBody AddChatDto addChatDto) {
        var admin = adminRepository.findByTgId(addChatDto.getAdminId());
        var chat = chatRepository.findByTgId(addChatDto.getChatId());
        if (admin == null) {
            admin = new Admin();
            admin.setTgId(addChatDto.getAdminId());
            admin = adminRepository.save(admin);
        }
        if (chat == null) {
            chat = new Chat();
            chat.setTgId(addChatDto.getChatId());
            chat = chatRepository.save(chat);
        }
        admin.getChats().add(chat);//?
        adminRepository.save(admin);

        var log = new Log();
        log.setAdmin(admin);
        log.setChat(chat);
        log.setLog("Add chat and admin connection");
        logRepository.save(log);
    }

    @PostMapping("getSettings")
    public SettingsDto getSettings(@RequestBody ChatDto chatDto) {
        var chat = chatRepository.getByTgId(chatDto.getChatId());
        var settings = new SettingsDto();
        settings.setChatId(chatDto.getChatId());
        settings.setUsernameCheck(chat.isUsernameCheck());
        settings.setDbCheck(chat.isDbCheck());
        settings.setPhotoCheck(chat.isPhotoCheck());
        settings.setBioCheck(chat.isBioCheck());
        settings.setMessageCheck(chat.isMessageCheck());

        return settings;
    }

    @PostMapping()
    public void setSettings(@RequestBody SettingsDto settingsDto) throws BadRequestException {
        var admin = adminRepository.findByTgId(settingsDto.getAdminId());
        var chat = chatRepository.findByTgId(settingsDto.getChatId());
        if (!admin.getChats().contains(chat)) {
            throw new BadRequestException("Admin doesn't have chat");
        }
        chat.setUsernameCheck(settingsDto.isUsernameCheck());
        chat.setDbCheck(settingsDto.isDbCheck());
        chat.setPhotoCheck(settingsDto.isPhotoCheck());
        chat.setBioCheck(settingsDto.isBioCheck());
        chat.setMessageCheck(settingsDto.isMessageCheck());

        chatRepository.save(chat);

        var log = new Log();
        log.setAdmin(admin);
        log.setChat(chat);
        log.setLog(chatLog(chat));
        logRepository.save(log);
    }

    @PostMapping("copy")
    public void setSettings(@RequestBody CopyChat copyChat) throws BadRequestException {

        var admin = adminRepository.findByTgId(copyChat.getAdminId());
        var chatFrom = chatRepository.findByTgId(copyChat.getChatFromId());
        var chatTo = chatRepository.findByTgId(copyChat.getChatToId());

        if (!admin.getChats().contains(chatFrom) || !admin.getChats().contains(chatTo)) {
            throw new BadRequestException("Admin doesn't have chat");
        }
        chatTo.setUsernameCheck(chatFrom.isUsernameCheck());
        chatTo.setDbCheck(chatFrom.isDbCheck());
        chatTo.setPhotoCheck(chatFrom.isPhotoCheck());
        chatTo.setBioCheck(chatFrom.isBioCheck());
        chatTo.setMessageCheck(chatFrom.isMessageCheck());
        chatRepository.save(chatTo);

        var log = new Log();
        log.setAdmin(admin);
        log.setChat(chatTo);
        log.setLog(chatLog(chatTo));
        logRepository.save(log);
    }

    @PostMapping("copyForAll")
    public void setSettings(@RequestBody AddChatDto copyChat) throws BadRequestException {

        var admin = adminRepository.findByTgId(copyChat.getAdminId());
        var chatFrom = chatRepository.findByTgId(copyChat.getChatId());

        if (!admin.getChats().contains(chatFrom)) {
            throw new BadRequestException("Admin doesn't have chat");
        }

        var chats = admin.getChats();
        for(Chat chat:chats){
            chat.setUsernameCheck(chatFrom.isUsernameCheck());
            chat.setDbCheck(chatFrom.isDbCheck());
            chat.setPhotoCheck(chatFrom.isPhotoCheck());
            chat.setBioCheck(chatFrom.isBioCheck());
            chat.setMessageCheck(chatFrom.isMessageCheck());
            chatRepository.save(chat);

            var log = new Log();
            log.setAdmin(admin);
            log.setChat(chat);
            log.setLog(chatLog(chat));
            logRepository.save(log);
        }
    }

    private String chatLog(Chat chat) {
        return "Изменены настройки проверки: " + "usernameCheck=" + chat.isUsernameCheck() + ", " +
                "dbCheck=" + chat.isDbCheck() + ", " +
                "photoCheck=" + chat.isPhotoCheck() + ", " +
                "bioCheck=" + chat.isBioCheck() + ", " +
                "messageCheck=" + chat.isMessageCheck() + ", ";
    }
}
