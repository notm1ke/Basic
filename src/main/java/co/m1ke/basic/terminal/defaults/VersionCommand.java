package co.m1ke.basic.terminal.defaults;

import co.m1ke.basic.BasicService;
import co.m1ke.basic.BasicSettings;
import co.m1ke.basic.terminal.TerminalCommand;
import co.m1ke.basic.utils.Lang;

public class VersionCommand extends TerminalCommand {

    private BasicService service;

    public VersionCommand(BasicService service) {
        super(service, new String[] { "ver", "version" }, Lang.usage(service.name(), "ver"), service.name());
        this.service = service;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            logger.info(help);
            return;
        }
        logger.info("This service is running " + service.name() + " version " + service.version() + " (Implementing Basic version " + BasicSettings.VERSION.getValue() + ") by " + BasicSettings.AUTHOR.getValue() + ".");
    }

}
