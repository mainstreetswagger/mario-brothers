package telegram;

import dbcontext.MarioBrothersDBContext;
import dbcontext.models.MarioUser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotRequestHandler {
    public BotRequestHandler() {
        this.dbContext = new MarioBrothersDBContext();
        this.userStatus = new HashMap<Long, UserStatus>();
        this.customers = new HashMap<Long, Customer>();
        InlineKeyboardButton orders = InlineKeyboardButton.builder()
                .text("My Orders").callbackData("orders")
                .build();
        InlineKeyboardButton meals = InlineKeyboardButton.builder()
                .text("Order A Meal").callbackData("meals")
                .build();
        this.allButtons = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(orders, meals)).build();
        this.ordersButton = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(orders)).build();
        this.mealsButton = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(meals)).build();
    }
    private MarioBrothersDBContext dbContext;
    private Update update;
    private Message userMessageContext;
    private User userContext;
    private MarioUser marioUser;
    private InlineKeyboardMarkup allButtons;
    private InlineKeyboardMarkup ordersButton;
    private InlineKeyboardMarkup mealsButton;
    private Map<Long, UserStatus> userStatus;
    private Map<Long, Customer> customers;

    public SendMessage handleRequest(Update update) {
        this.update = update;
        this.userMessageContext = update.getMessage();
        this.userContext = this.userMessageContext.getFrom();
        return checkUserStatus();
    }

    private SendMessage checkUserStatus() {
        UserStatus status = userStatus.get(this.userContext.getId());
        if(status != null) {
            switch (status) {
                case UserName:
                    return askPassword();
                case Password:
                    return askPassword();
                case ChooseAction:
                    return chooseAction();
                case Meals:
                    break;
                case OrdersList:
                    break;
                case OrderDetails:
                    break;
                default:
                    return askUserName();
            }
        }
        return askUserName();
    }

    private SendMessage askUserName() {
        customers.put(userContext.getId(), new Customer());
        userStatus.put(userContext.getId(), UserStatus.UserName);
        String msg = "Hi!\nEnter your user name:";
        return send(msg, null);
    }

    private SendMessage askPassword() {
        String userName = this.userMessageContext.getText().trim();
        if(dbContext.getUserRepository().hasUser(userName)) {
            customers.get(userContext.getId()).setName(userName);
            userStatus.put(userContext.getId(), UserStatus.ChooseAction);
            String msg = "Enter your password:";
            return send(msg, null);
        }
        customers.remove(userContext.getId());
        userStatus.remove(userContext.getId());
        return send(null, null);
    }

    public SendMessage chooseAction() {
        String userName = customers.get(userContext.getId()).getName();
        String password = userMessageContext.getText();
        marioUser = dbContext.getUserRepository().getUser(userName, password);
        if(marioUser != null) {
            String msg = "What would you like to do?";
            return send(msg, this.allButtons);
        }
        customers.remove(userContext.getId());
        userStatus.remove(userContext.getId());
        return send(null, null);
    }


    private SendMessage send(String message, InlineKeyboardMarkup inlineKeyboardMarkup) {
        String defaultMessage = "There's no such a user. Bye!";
        if(message == null || message == "")
            message = defaultMessage;
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);

        if(inlineKeyboardMarkup != null)
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage.setChatId(this.userContext.getId().toString());

        return sendMessage;
    }

    InlineKeyboardButton next = InlineKeyboardButton.builder()
            .text("Next").callbackData("next")
            .build();

    InlineKeyboardButton back = InlineKeyboardButton.builder()
            .text("Back").callbackData("back")
            .build();

    InlineKeyboardButton url = InlineKeyboardButton.builder()
            .text("Tutorial")
            .url("https://core.telegram.org/bots/api")
            .build();
    private boolean screaming = false;

    private InlineKeyboardMarkup keyboardM1;
    private InlineKeyboardMarkup keyboardM2;

    public SendMessage sendMenu(Long who, String txt){
        keyboardM1 = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(next)).build();
        SendMessage sm = SendMessage.builder().chatId(who.toString())
                .parseMode("HTML").text(txt)
                .replyMarkup(keyboardM1).build();
        return sm;
    }
    public SendMessage sendMenu1(Long who, String txt){
        keyboardM2 = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(back))
                .keyboardRow(List.of(url))
                .build();
        SendMessage sm = SendMessage.builder().chatId(who.toString())
                .parseMode("HTML").text(txt)
                .replyMarkup(keyboardM2).build();
        return sm;
    }
}
