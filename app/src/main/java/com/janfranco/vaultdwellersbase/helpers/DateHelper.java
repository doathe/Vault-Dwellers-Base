package com.janfranco.vaultdwellersbase.helpers;

import java.util.Date;

public class DateHelper {

    private static final long ONE_MIN_IN_MS = 60000;

    public Date getCurrentDate() {
        return new Date();
    }

    public long convertToMilliseconds(Date date) {
        return date.getTime();
    }

    public Date convertFromMilliseconds(long milliseconds) {
        return new Date(milliseconds);
    }

    public int compareDates(Date d1, Date d2) {
        return d1.compareTo(d2);
    }

    public Date addMinutesToDate(Date date, int minutes) {
        return new Date(date.getTime() + minutes * ONE_MIN_IN_MS);
    }

}
