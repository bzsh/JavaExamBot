package by.vss.exam.bean.manage;

public enum ManageType {

    CREATE_JAVA_CARD("Создание Java-карт"),
    CREATE_ENGLISH_CARD("Создание English-карт"),
    CREATE_TEST("Создание теста"),

    EDIT_JAVA_CARD("Редактирование Java-карт"),
    EDIT_ENGLISH_CARD("Редактирование English-карт"),
    EDIT_TEST("Редактирование теста");

    private final String string;

    ManageType(String string) {
        this.string = string;
    }

    public String getStringOf(){
        return string;
    }
}
