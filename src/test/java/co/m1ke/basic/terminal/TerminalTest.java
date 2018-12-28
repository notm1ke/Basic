package co.m1ke.basic.terminal;

import co.m1ke.basic.BasicService;
import co.m1ke.basic.logger.Logger;
import co.m1ke.basic.terminal.defaults.VersionCommand;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TerminalTest implements BasicService {

    @Override
    public String name() {
        return "Tests";
    }

    @Override
    public String author() {
        return "Mike M";
    }

    @Override
    public double version() {
        return 0.1;
    }

    private Logger logger = Logger.asSingleton("Tests");
    private Terminal terminal = new Terminal();

    @Test
    public void t1_initialize() throws InterruptedException {

        logger.debug("Attempting to start terminal service..");
        terminal.initialize(this, new VersionCommand(this));

        logger.debug("Assuring that terminal init finishes..");
        Thread.sleep(1500L);

        Assert.assertTrue(Terminal.getScannerSessions().containsKey(this));
        logger.debug("Started!");
    }

    @Test
    public void t5_close() {
        Assert.assertFalse(terminal.getScannerSessions().isEmpty());
    }

    @Test
    public void t2_getCommands() {
        Assert.assertFalse(terminal.getCommands().isEmpty());
    }

    @Test
    public void t3_getScannerSessions() {
        Assert.assertFalse(terminal.getCommands().isEmpty());
    }

    @Test
    public void t4_getStartup() {
        Assert.assertNotEquals(terminal.getStartup(), 0);
    }
}