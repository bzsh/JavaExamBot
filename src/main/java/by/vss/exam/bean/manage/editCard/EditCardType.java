package by.vss.exam.bean.manage.editCard;

public enum EditCardType {
    EDIT_JAVA_CARD("Редактирование Java-карт"),
    EDIT_ENGLISH_CARD("Редактирование English-карт"),
    EDIT_USER_DATA("Редактирование данных пользователей");

    private final String string;

    EditCardType(String string) {
        this.string = string;
    }

    public String getStringOf() {
        return string;
    }
}
