package co.m1ke.basic.terminal;

import co.m1ke.basic.BasicService;
import co.m1ke.basic.logger.Logger;
import co.m1ke.basic.terminal.defaults.AboutCommand;
import co.m1ke.basic.terminal.defaults.StopCommand;
import co.m1ke.basic.terminal.defaults.UptimeCommand;
import co.m1ke.basic.terminal.defaults.VersionCommand;
import co.m1ke.basic.utils.Timings;

import java.util.HashMap;
import java.util.Scanner;

public class Terminal {

    private static HashMap<String, TerminalCommand> registeredCommands = new HashMap<>();
    private static HashMap<BasicService, Thread> registeredTerminals = new HashMap<>();
    private static HashMap<BasicService, Scanner> scannerSessions = new HashMap<>();

    private static Thread terminalThread;
    private static long startup;
    private static Logger logger;

    public static void initialize(BasicService service, TerminalCommand... commands) {
        Timings timings = new Timings("Terminal", "Initialization");
        startup = System.currentTimeMillis();
        logger = new Logger("Terminal");

        Terminal.register(
                new AboutCommand(service),
                new StopCommand(service),
                new UptimeCommand(service),
                new VersionCommand(service)
        );
        Terminal.register(commands);

        Scanner scan = new Scanner(System.in);
        terminalThread = new Thread(() -> {
            while (registeredTerminals.containsKey(service)) {
                String cmd = scan.nextLine();
                String[] args = new String[]{};

                if (cmd.contains(" ")) {
                    args = cmd.substring(cmd.indexOf(' ') + 1).split(" ");
                    cmd = cmd.split(" ")[0];
                }

                TerminalCommand command = registeredCommands.get(cmd.toLowerCase());

                if (command != null) {
                    command.setUsedAlias(cmd.toLowerCase());
                    command.execute(args);
                } else {
                    logger.info("Unknown command, check for typos?");
                }
            }
        }, "Terminal Service");
        timings.complete(() -> {
            registeredTerminals.put(service, terminalThread);
            scannerSessions.put(service, scan);
            terminalThread.start();
            logger.info(service.getClass().getSimpleName() + ": Terminal service activated.");
        });
    }

    public static void close(BasicService source) {
        if (!registeredTerminals.containsKey(source)
                || !scannerSessions.containsKey(source)) {
            throw new IllegalArgumentException("Unable to find sessions for " + source.getClass().getSimpleName());
        }
        registeredTerminals.get(source).interrupt();
        registeredTerminals.remove(source);

        scannerSessions.get(source).close();
        scannerSessions.remove(source);

        logger.info("Terminal service deactivated.");
    }

    public static void register(TerminalCommand command) {
        for (String a : command.getNames()) {
            registeredCommands.put(a, command);
        }
    }

    public static void register(TerminalCommand... commands) {
        for (TerminalCommand cmd : commands) {
            register(cmd);
        }
    }

    public static HashMap<String, TerminalCommand> getCommands() {
        return registeredCommands;
    }

    public static HashMap<BasicService, Scanner> getScannerSessions() {
        return scannerSessions;
    }

    public static long getStartup() {
        return startup;
    }

}
