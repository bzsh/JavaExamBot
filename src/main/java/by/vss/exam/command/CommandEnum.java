package by.vss.exam.command;

import by.vss.exam.command.impl.*;

public enum CommandEnum {
    TEST(new TestCommand()),
    CARD(new CardCommand()),
    CONTRIBUTE(new MenageCommand()),
    ABOUT(new AboutCommand()),

    RESUME_TEST(new ResumeTestCommand()),
    MAIN_MENU(new MainMenuCommand()),
    END_TASK_TEST(new EndTaskTestCommand()),
    VIEW_TEST_RESULT(new ViewTestResultCommand()),

    ROTATE_CARD(new RotateCardCommand()),
    START(new StartCommand()),
    EXIT(new ExitCommand()),

    A(new ACommand()),
    B(new BCommand()),
    C(new CCommand()),
    D(new DCommand()),
    E(new ECommand()),
    F(new FCommand()),

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
