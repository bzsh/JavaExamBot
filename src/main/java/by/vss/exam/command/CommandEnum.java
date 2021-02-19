package by.vss.exam.command;

import by.vss.exam.command.impl.*;

public enum CommandEnum {

    //Start
    START(new StartCommand()),

    //engine command
    CARD_ENGINE_COMMAND(new CardEngineCommand()),
    TEST_ENGINE_COMMAND(new TestEngineCommand()),
    MANAGE_ENGINE_COMMAND(new ManageEngineCommand()),

    //main menu commands
    TEST(new TestCommand()),
    CARD(new CardCommand()),
    MANAGE(new MenageCommand()),
    ABOUT(new AboutCommand()),

    //test menu commands
    JAVA_TEST_COMMAND(new JavaTestCommand()),
    ENGLISH_TEST_COMMAND(new EnglishTestCommand()),

    //card menu commands
    LEARNED_CARDS(new LearnedCardsCommand()),
    JAVA_CARDS(new JavaCardsCommand()),
    ENGLISH_CARDS(new EnglishCardsCommand()),

    //manage menu command
    CREATE_TEST_COMMAND(new CreateTestCommand()),
    CREATE_CARD_COMMAnd(new CreateCardCommand()),

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
