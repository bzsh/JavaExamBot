package by.vss.exam.command;

import by.vss.exam.command.impl.*;

public enum CommandEnum {

    //Start
    START(new StartCommand()),

    //engine command
    CARD_ENGINE_COMMAND(new CardEngineCommand()),
    TEST_ENGINE_COMMAND(new TestEngineCommand()),
    MANAGE_ENGINE_COMMAND(new ManageEngineCommand()),
    QUIT_ENGINE(new QuitEngineCommand()),

    //main menu commands
    TEST(new TestCommand()),
    CARD(new CardMenuCommand()),
    MANAGE(new ManageMenuCommand()),
    ABOUT(new AboutCommand()),

    //test menu commands
    JAVA_TEST_COMMAND(new JavaTestCommand()),
    ENGLISH_TEST_COMMAND(new EnglishTestCommand()),

    //card menu commands
    OPTIONAL(new OptionalCardsMenuCommand()),
    JAVA(new JavaCardsCommand()),
    ENGLISH(new EnglishCardsCommand()),
    BACK(new BackCommand()),

    //manage menu command
    EDIT(new EditCommand()),
    CREATE(new CreateCommand()),
    CONTROL(new ControlCommand()),


    ENTER_QUESTION(new EnterQuestionCommand()),
    ENTER_ANSWER(new EnterAnswerCommand()),

    CREATE_ENGLISH(new CreateEnglishCardCommand()),
    CREATE_JAVA(new CreateJavaCardCommand()),

    EDIT_ENGLISH(new EditEnglishCardCommand()),
    EDIT_JAVA(new EditJavaCardCommand()),

    QUIT_CREATE(new QuitCreateCardCommand()),

    CREATE_TEST_COMMAND(new CreateTestCommand()),

    RESET_SEANCE_COMMAND(new ResetManageSeanceAndQuit()),
    // test commands
    RESUME_TEST(new ResumeTestCommand()),
    MAIN_MENU(new MainMenuCommand()),
    END_TASK_TEST(new EndTaskTestCommand()),
    VIEW_TEST_RESULT(new ViewTestResultCommand()),

    //test answers commands
    A(new ACommand()),
    B(new BCommand()),
    C(new CCommand()),
    D(new DCommand()),
    E(new ECommand()),
    F(new FCommand()),

    //Card command
    PREV_CARD(new PrevCardCommand()),
    NEXT_CARD(new NextCardCommand()),
    LEARN_CARD(new LearnCardCommand()),
    ROTATE_CARD(new RotateCardCommand()),

    //other commands
    EXIT(new ExitCommand()),
    EMOJI(new EmojiCommand()),
    STATISTICS(new StatisticsCommand()),
    EXAMPLE(new ExampleCommand()),
    EMPTY(new EmptyCommand());

    private final Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public static Command getCurrentCommand(String commandString) {
        return CommandEnum.valueOf(commandString.toUpperCase()).command;
    }
}
