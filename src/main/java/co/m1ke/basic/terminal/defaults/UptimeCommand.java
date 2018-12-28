package co.m1ke.basic.terminal.defaults;

import co.m1ke.basic.BasicService;
import co.m1ke.basic.terminal.Terminal;
import co.m1ke.basic.terminal.TerminalCommand;
import co.m1ke.basic.utils.Lang;
import co.m1ke.basic.utils.TimeUtil;

public class UptimeCommand extends TerminalCommand {

    private BasicService service;

    public UptimeCommand(BasicService service) {
        super(service, new String[] { "uptime" }, Lang.usage("Service", "uptime"), "Service");
        this.service = service;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            logger.info(help);
            return;
        }
        long uptime = (System.currentTimeMillis() - Terminal.getStartup());
        logger.info(service.name() + " has been running for " + Lang.GREEN + TimeUtil.getShortenedTimeValue(uptime) + Lang.RESET + ".");
    }

}
