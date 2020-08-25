package ru.knastnt.meshtest.util.exception;

public class ErrorInfo {
    private static ErrorInfo last;

    public static ErrorInfo getLast() {
        return last;
    }

    public ErrorInfo(String msg) {
        this.msg = msg;
        last = this;
    }

    private final String msg;

    public String getMsg() {
        return msg;
    }
}
