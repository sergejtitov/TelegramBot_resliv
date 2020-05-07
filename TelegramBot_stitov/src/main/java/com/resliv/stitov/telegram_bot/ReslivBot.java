package com.resliv.stitov.telegram_bot;

import com.resliv.stitov.configs.TelegramConfig;
import com.resliv.stitov.domain.model.City;
import com.resliv.stitov.services.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@AllArgsConstructor
@Component
public class ReslivBot extends TelegramLongPollingBot {
    public static final String NOT_FOUND = "Нам очень жаль, но в нашей базе такого города нет :-(";

    private final TelegramConfig tmConfig;
    private final CityService cityService;


    @Override
    public void onUpdateReceived(Update update) {
        String cityName = update.getMessage().getText().toUpperCase();
        City city = cityService.findCityByName(cityName);
        if (city == null){
            sendMsg(update.getMessage().getChatId().toString(), NOT_FOUND);
        } else {
            sendMsg(update.getMessage().getChatId().toString(), city.toString());
        }

    }

    private void sendMsg(String chatId, String text) {
        SendMessage cityInfo = new SendMessage();
        cityInfo.setChatId(chatId);
        cityInfo.setText(text);
        try {
            execute(cityInfo);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return tmConfig.getUsername();
    }

    @Override
    public String getBotToken() {
        return tmConfig.getTmToken();
    }
}
