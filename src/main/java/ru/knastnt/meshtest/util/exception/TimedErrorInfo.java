package ru.knastnt.meshtest.util.exception;

import java.util.Date;

public class TimedErrorInfo extends ErrorInfo {

    public TimedErrorInfo(String msg, Date created) {
        super(msg);
        this.created = created;
    }

    private Date created;
}
