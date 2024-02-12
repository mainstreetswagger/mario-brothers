package telegram;

import dbcontext.MarioBrothersDBContext;
import dbcontext.models.MarioUser;
import dbcontext.models.Meal;
import dbcontext.models.Order;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotRequestHandler {
    public BotRequestHandler() {
        this.dbContext = new MarioBrothersDBContext();
        this.userStatus = new HashMap<Long, UserStatus>();
        this.customers = new HashMap<Long, Customer>();
        this.ordersText = "orders";
        this.mealsText = "meals";
        this.logoutText = "logout";
        InlineKeyboardButton orders = InlineKeyboardButton.builder()
                .text("My Orders").callbackData(ordersText)
                .build();
        InlineKeyboardButton meals = InlineKeyboardButton.builder()
                .text("Order A Meal").callbackData(mealsText)
                .build();
        InlineKeyboardButton logout = InlineKeyboardButton.builder()
                .text("Logout").callbackData(logoutText)
                .build();

        this.allButtons = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(orders, meals)).build();
        this.ordersLogoutButton = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(orders, logout)).build();
        this.mealsLogoutButton = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(meals, logout)).build();
    }
    private MarioBrothersDBContext dbContext;
    private Update update;
    private Message userMessageContext;
    private User userContext;
    private MarioUser marioUser;
    private InlineKeyboardMarkup allButtons;
    private InlineKeyboardMarkup ordersLogoutButton;
    private InlineKeyboardMarkup mealsLogoutButton;
    private Map<Long, UserStatus> userStatus;
    private Map<Long, Customer> customers;
    private String ordersText;
    private String mealsText;
    private String logoutText;

    public BotReplyData handleRequest(Update update) {
        this.update = update;
        this.userMessageContext = update.getMessage();
        this.userContext = this.userMessageContext.getFrom();
        return checkUserStatus();
    }

    private BotReplyData checkUserStatus() {
        UserStatus status = userStatus.get(this.userContext.getId());
        if(status != null) {
            switch (status) {
                case UserName:
                    return askPassword();
                case Password:
                    return askPassword();
                case ChoosingAction:
                    return chooseAction();
                case TappingButton:
                    return tapButton();
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

    private BotReplyData askUserName() {
        customers.put(userContext.getId(), new Customer());
        userStatus.put(userContext.getId(), UserStatus.UserName);
        String msg = "Hi!\nEnter your user name:";
        return send(msg, null);
    }

    private BotReplyData askPassword() {
        String userName = this.userMessageContext.getText().trim();
        if(dbContext.getUserRepository().hasUser(userName)) {
            customers.get(userContext.getId()).setName(userName);
            userStatus.put(userContext.getId(), UserStatus.ChoosingAction);
            String msg = "Enter your password:";
            return send(msg, null);
        }
        customers.remove(userContext.getId());
        userStatus.remove(userContext.getId());
        return send(null, null);
    }

    public BotReplyData chooseAction() {
        String userName = customers.get(userContext.getId()).getName();
        String password = userMessageContext.getText();
        marioUser = dbContext.getUserRepository().getUser(userName, password);
        if(marioUser != null) {
            String msg = "What would you like to do?";
            userStatus.put(userContext.getId(), UserStatus.TappingButton);
            return send(msg, this.allButtons);
        }
        customers.remove(userContext.getId());
        userStatus.remove(userContext.getId());
        return send(null, null);
    }
    private BotReplyData tapButton() {
        EditMessageText data = EditMessageText.builder()
                .chatId(this.userContext.getId().toString())
                .messageId(this.userMessageContext.getMessageId()).text("").build();

        EditMessageReplyMarkup replyMarkup = EditMessageReplyMarkup.builder()
                .chatId(this.userContext.getId().toString()).messageId(this.userMessageContext.getMessageId()).build();

        StringBuilder sb = new StringBuilder();
        if(this.userMessageContext.getText().equals(this.ordersText)) {
            List<Order> userOrders = dbContext.getOrderRepository().getOrdersByUserId(this.marioUser.getId());
            sb.append("Id\t\tDate\t\tTotal($)\t\tStatus");
            for(int i = 0; i < userOrders.size(); i++) {
                sb.append("\n");
                sb.append(userOrders.get(i).getId());
                sb.append("\t\t");
                sb.append(userOrders.get(i).getCreatedAt());
                sb.append("\t\t");
                sb.append(userOrders.get(i).getTotal());
                sb.append("\t\t");
                sb.append(userOrders.get(i).getStatus());
            }
            sb.append("\nType order id to see its details.");
            data.setText(sb.toString());
            replyMarkup.setReplyMarkup(this.mealsLogoutButton);
        } else if(this.userMessageContext.getText().equals(this.mealsText)) {
            List<Meal> mealsMenu = dbContext.getMealRepository().getMeals();
            data.setText("Type meal id and then items quantity seperated by \"-\".\nExample: 1-2.");
            replyMarkup.setReplyMarkup(this.ordersLogoutButton);
        } else if(this.userMessageContext.getText().equals(this.logoutText)) {
            customers.remove(userContext.getId());
            userStatus.remove(userContext.getId());
        }

        AnswerCallbackQuery close = AnswerCallbackQuery.builder()
                .callbackQueryId(this.update.getCallbackQuery().getId()).build();

        return new BotReplyData(data, replyMarkup,close,null);
    }


    private BotReplyData send(String message, InlineKeyboardMarkup inlineKeyboardMarkup) {
        String defaultMessage = "There's no such a user. Bye!";
        if(message == null || message == "")
            message = defaultMessage;
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);

        if(inlineKeyboardMarkup != null)
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage.setChatId(this.userContext.getId().toString());

        return new BotReplyData().setMessage(sendMessage);
    }
}
