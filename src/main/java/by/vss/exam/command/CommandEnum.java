package by.vss.exam.command;

import by.vss.exam.command.impl.*;

public enum CommandEnum {

    //Start
    START(new StartCommand()),

    //engine command
    CARD_ENGINE_COMMAND(new CardEngineCommand()),
    TEST_ENGINE_COMMAND(new TestEngineCommand()),
    MANAGE_ENGINE_COMMAND(new CreateCardEngineCommand()),
    QUIT_ENGINE(new QuitEngineCommand()),

    //main menu commands
    TЕST(new TestCommand()),
    CАRD(new CardMenuCommand()),
    MАNAGE(new ManageMenuCommand()),
    АBOUT(new AboutCommand()),

    //card menu commands
    OPTIONАL(new OptionalCardsMenuCommand()),
    JAVА(new JavaCardsCommand()),
    ЕNGLISH(new EnglishCardsCommand()),
    BАCK(new BackCommand()),

    //manage menu command
    INFО(new InfoCommand()),
    CREАTE(new CreateCardCommand()),
    ЕDIT(new EditCardCommand()),

    VIEW_MANAGE_SEANCE_WARNING_MENU(new ViewCreateSeanceWarnMenuCommand()),
    VIEW_CREATE_CARD_RESULT(new ViewCreateCardResultCommand()),
    ENTER_QUESTION(new EnterQuestionCommand()),
    ENTER_ANSWER(new EnterAnswerCommand()),

    EDIT_QUESTION(new ChangeQuestionCommand()),
    EDIT_ANSWER(new ChangeAnswerCommand()),

    CREATE_ENGLISH(new CreateEnglishCardCommand()),
    CREATE_JAVA(new CreateJavaCardCommand()),

    SAVE_CARD(new SaveCardCommand()),

    SEND_TO_MODERATE(new SendToModerateCardCommand()),

    QUIT_CREATE(new QuitCreateCardCommand()),

    RESET_SEANCE_COMMAND(new ResetManageSeanceAndQuitCommand()),

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
