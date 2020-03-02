package co.m1ke.basic.utils;

import co.m1ke.basic.BasicService;

import java.io.File;
import java.net.URISyntaxException;

public class JavaUtils {

    public static Class<?> getCaller() {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String clazzName = stackTrace[stackTrace.length - 1].getClassName();
        try {
            return Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static File getJarLocation() {
        try {
            return new File(BasicService.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            return null;
        }
    }

}
