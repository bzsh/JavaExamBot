package by.vss.exam.command.factory;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandEnum;
import by.vss.exam.command.impl.DataReceiverCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandFactory {
    public static Logger logger = LoggerFactory.getLogger(CommandFactory.class);

    public Command defineCommand(String command) {
        Command currentCommand = new DataReceiverCommand();
        if (command != null) {
            if (command.startsWith("/")) {
                command = command.substring(1);
            }
            try {
                currentCommand = CommandEnum.getCurrentCommand(command);
            } catch (IllegalArgumentException e) {
                logger.error("No such command exists!");
            }
        }
        return currentCommand;
    }
}
