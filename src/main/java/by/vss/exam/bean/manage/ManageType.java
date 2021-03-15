package by.vss.exam.bean.manage;

public enum ManageType {
    //    EDIT_JAVA_CARD("Редактирование Java-карт"),
//    EDIT_ENGLISH_CARD("Редактирование English-карт"),
//    EDIT_TEST("Редактирование теста"),
//    CREATE_TEST("Создание теста"),
    CREATE_JAVA_CARD("Создание Java-карт"),
    CREATE_ENGLISH_CARD("Создание English-карт");

    private final String string;

    ManageType(String string) {
        this.string = string;
    }

    public String getStringOf() {
        return string;
    }
}
