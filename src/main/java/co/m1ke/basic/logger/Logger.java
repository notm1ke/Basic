package co.m1ke.basic.logger;

import co.m1ke.basic.utils.JsonSerializable;
import co.m1ke.basic.utils.Lang;
import co.m1ke.basic.utils.TimeUtil;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

public class Logger implements JsonSerializable {

    public enum Color {

        RESET(Lang.RESET), RED(Lang.RED), YELLOW(Lang.YELLOW), GREEN(Lang.GREEN), CYAN(Lang.CYAN), BLUE(Lang.BLUE), PURPLE(Lang.PURPLE), BLACK(Lang.BLACK), WHITE(Lang.WHITE);

        private String color;

        Color(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    private String name;
    private Color color;

    public Logger() {
        this.name = "Basic";
        this.color = Color.GREEN;
    }

    public Logger(String name) {
        this.name = name;
        this.color = Color.GREEN;
    }

    public Logger(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public static Logger asSingleton(String name) {
        return new Logger(name);
    }

    public void raw(String msg) {
        System.out.println(msg);
    }

    public void log(String body) {
        raw(Lang.WHITE + TimeUtil.format(System.currentTimeMillis()) + color.getColor() + " | " + Lang.RESET + body);
    }

    public void log(LoggingLevel level, String body) {
        raw(Lang.WHITE + TimeUtil.format(System.currentTimeMillis()) + " " + level.getColor() + level.getName() + Lang.YELLOW + StringUtils.repeat(' ', level.getSpaces()) + "| " + Lang.RESET + body);
    }

    public void log(String color, String head, String body) {
        raw(Lang.WHITE + TimeUtil.format(System.currentTimeMillis()) + this.color.getColor() + " | [" + color + head + "] " + Lang.RESET + body);
    }

    public void log(boolean condition, String color, String head, String body) {
        if (condition)
            raw(Lang.WHITE + TimeUtil.format(System.currentTimeMillis()) + this.color.getColor() + " | [" + color + head + "] " + Lang.RESET + body);
    }

    public void unlisted(String body) {
        log(LoggingLevel.INFO, Lang.RESET + body);
    }

    public void unlisted(String body, boolean condition) {
        if (condition)
            this.unlisted(body);
    }

    public void info(String body) {
        log(LoggingLevel.INFO, Lang.WHITE + "[" + this.name + "] " + Lang.RESET + body);
    }

    public void info(String body, boolean condition) {
        if (condition)
            this.info(body);
    }

    public void debug(String body) {
        log(LoggingLevel.DEBUG, Lang.WHITE + "[" + this.name + "] " + Lang.RESET + body);
    }

    public void debug(String body, boolean condition) {
        if (condition)
            this.debug(body);
    }

    public void warning(String body) {
        log(LoggingLevel.WARNING, Lang.WHITE + "[" + this.name + "] " + Lang.RESET + body);
    }

    public void warning(String body, boolean condition) {
        if (condition)
            this.warning(body);
    }

    public void severe(String body) {
        log(LoggingLevel.SEVERE, Lang.WHITE + "[" + this.name + "] " + Lang.RESET + body);
    }

    public void severe(String body, boolean condition) {
        if (condition)
            this.severe(body);
    }

    public void except(Exception e) {
        log(LoggingLevel.SEVERE, Lang.WHITE + "[" + this.name + "] " + Lang.RESET + e.getMessage() + " (" + e.getClass().getName() + ")");
    }

    public void except(Exception e, String base) {
        log(LoggingLevel.SEVERE, Lang.WHITE + "[" + this.name + "] " + Lang.RESET + base + ": " + e.getMessage() + " (" + e.getClass().getName() + ")");
    }

    public void except(Exception e, String base, boolean condition) {
        if (condition)
            this.except(e, base);
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public JSONObject toJson() {
        return new JSONObject()
                .put("name", this.name)
                .put("color", new JSONObject()
                        .put("name", this.color.name())
                        .put("value", this.color.getColor()));
    }

}
