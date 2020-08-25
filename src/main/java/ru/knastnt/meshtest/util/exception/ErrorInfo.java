package ru.knastnt.meshtest.util.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ErrorInfo {

    public static class TimedErrorInfo {
        private final String msg;

        @JsonFormat(shape = JsonFormat.Shape.NUMBER)
        private final Date created = new Date();

        public TimedErrorInfo(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public Date getCreated() {
            return created;
        }
    }

    private static volatile TimedErrorInfo last;

    public static TimedErrorInfo getLast() {
        return last;
    }




    public ErrorInfo(String msg) {
        this.msg = msg;
        last = new TimedErrorInfo(msg);
    }

    private final String msg;

    public String getMsg() {
        return msg;
    }
}
