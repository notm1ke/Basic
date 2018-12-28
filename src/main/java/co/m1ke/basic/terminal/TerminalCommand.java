package co.m1ke.basic.terminal;

import co.m1ke.basic.BasicService;
import co.m1ke.basic.logger.Logger;

import java.util.Scanner;

public abstract class TerminalCommand {

    public Logger logger;

    private BasicService service;
    private String[] names;
    private String usedAlias;

    public String help;
    public String head;

    public TerminalCommand(BasicService service, String[] names, String help, String head) {
        this.logger = Logger.asSingleton(service.name());
        this.service = service;
        this.names = names;
        this.help = help;
        this.head = head;
    }

    public abstract void execute(String[] args);

    public Scanner getScanner() {
        return Terminal.getScannerSessions().get(service);
    }

    public BasicService getService() {
        return service;
    }

    public void setService(BasicService service) {
        this.service = service;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String getUsedAlias() {
        return usedAlias;
    }

    public void setUsedAlias(String usedAlias) {
        this.usedAlias = usedAlias;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

}
