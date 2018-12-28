package co.m1ke.basic.terminal.defaults;

import co.m1ke.basic.BasicService;
import co.m1ke.basic.terminal.TerminalCommand;
import co.m1ke.basic.utils.Lang;

public class AboutCommand extends TerminalCommand {

    private BasicService service;

    public AboutCommand(BasicService service) {
        super(service, new String[] { "about" }, Lang.usage(service.name(), "about"), service.name());
        this.service = service;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            logger.info(help);
            return;
        }
        logger.info(service.name() + " was made by " + service.author() + ".");
    }

}
