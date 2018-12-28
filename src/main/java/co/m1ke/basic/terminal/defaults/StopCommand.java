package co.m1ke.basic.terminal.defaults;

import co.m1ke.basic.BasicService;
import co.m1ke.basic.terminal.TerminalCommand;
import co.m1ke.basic.utils.Lang;

public class StopCommand extends TerminalCommand {

    private BasicService service;

    public StopCommand(BasicService service) {
        super(service, new String[] { "stop" }, Lang.usage("Terminal", "stop"), "Terminal");
        this.service = service;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            logger.info(help);
            return;
        }
        logger.info("Stopping " + service.name());
        System.exit(-1);
    }

}
