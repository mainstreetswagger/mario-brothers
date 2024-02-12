package telegram;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class BotReplyData {
    public BotReplyData() { }
    public BotReplyData(EditMessageText data, EditMessageReplyMarkup replyMarkup, AnswerCallbackQuery close, SendMessage message) {
        this.data = data;
        this.replyMarkup = replyMarkup;
        this.close = close;
        this.message = message;
    }
    private EditMessageText data;
    private EditMessageReplyMarkup replyMarkup;
    private AnswerCallbackQuery close;

    public EditMessageText getData() {
        return data;
    }

    public BotReplyData setData(EditMessageText data) {
        this.data = data;
        return this;
    }

    public EditMessageReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public BotReplyData setReplyMarkup(EditMessageReplyMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public AnswerCallbackQuery getClose() {
        return close;
    }

    public BotReplyData setClose(AnswerCallbackQuery close) {
        this.close = close;
        return this;
    }

    public SendMessage getMessage() {
        return message;
    }

    public BotReplyData setMessage(SendMessage message) {
        this.message = message;
        return this;
    }

    private SendMessage message;
}
