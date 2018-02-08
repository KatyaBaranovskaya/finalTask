package by.baranovskaya.manager;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
    RU(ResourceBundle.getBundle("error", new Locale("ru", "RU"))),
    EN(ResourceBundle.getBundle("error", new Locale("en", "US"))),
    BE(ResourceBundle.getBundle("error", new Locale("be", "BY")));

    private ResourceBundle resourceBundle;

    MessageManager(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }

    public static MessageManager getLocale(String locale){
        MessageManager messageManager = null;
        switch (locale){
            case "ru_RU":
                messageManager = RU;
                break;
            case "en_US":
                messageManager = EN;
                break;
            case "be_BY":
                messageManager = BE;
                break;
        }
        return messageManager;
    }
}
