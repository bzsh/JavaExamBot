package by.vss.exam.bean.manage.createCard;

public enum CreateCardType {
    CREATE_JAVA_CARD("Создание Java-карт"),
    CREATE_ENGLISH_CARD("Создание English-карт");

    private final String string;

    CreateCardType(String string) {
        this.string = string;
    }

    public String getStringOf() {
        return string;
    }
}
