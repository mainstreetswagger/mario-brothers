package telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MarioBot extends TelegramLongPollingBot {
    private final String token = "6716644422:AAHVKs1bPIn8BNr2KscRXsZAiLGe0QeHsMI";
    private final String userName = "MarioBrothersPizzeriaBot";
    private BotRequestHandler botHandler = new BotRequestHandler();
    @Override
    public String getBotUsername() {
        return this.userName;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        BotReplyData reply = botHandler.handleRequest(update);
        try {
            if(reply.getMessage() != null) {
                execute(reply.getMessage());
            }
            if(reply.getData() != null) {
                execute(reply.getData());
            }
            if(reply.getReplyMarkup() != null) {
                execute(reply.getReplyMarkup());
            }
            if(reply.getClose() != null) {
                execute(reply.getClose());
            }
        } catch(TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
