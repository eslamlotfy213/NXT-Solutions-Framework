package com.automationexercise.Utilities;

import org.apache.logging.log4j.LogManager;

public class LogUtils {
    // Define the path where logs will be stored
    public static String LOGS_PATH = "test-outputs/Logs";

    // Method to log trace messages
    public static void trace(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .trace(message);
    }

    // Method to log debug messages and attach logs to Allure
    public static void debug(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .debug(message);
    }

    // Method to log info messages
    public static void info(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .info(message);
    }

    // Method to log warning messages
    public static void warn(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .warn(message);
    }

    // Method to log error messages
    public static void error(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .error(message);
    }

    // Method to log fatal messages
    public static void fatal(String message) {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .fatal(message);
    }
}
