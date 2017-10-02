package ru.nk.training;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    private static final int[] DESCENDING_PERIODS = new int[]{
            Calendar.YEAR,
            Calendar.MONTH,
            Calendar.DAY_OF_MONTH,
            Calendar.HOUR_OF_DAY,
            Calendar.MINUTE,
            Calendar.SECOND,
            Calendar.MILLISECOND
    };

    public final long MILLISECONDS_IN_SECOND = 1000L;
    public final long MILLISECONDS_IN_MINUTE = MILLISECONDS_IN_SECOND * 60;
    public final long MILLISECONDS_IN_HOUR = MILLISECONDS_IN_MINUTE * 60;
    public final long MILLISECONDS_IN_DAY = MILLISECONDS_IN_HOUR * 24;

    public long truncateToDay(long milliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        truncateToDay(calendar);
        return calendar.getTimeInMillis();
    }

    public Date truncateToDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        truncateToDay(calendar);
        return calendar.getTime();
    }

    public Date dayAfter(Date date) {
        return add(date, Calendar.DAY_OF_YEAR, 1);
    }

    public Date dayBefore(Date date) {
        return add(date, Calendar.DAY_OF_YEAR, -1);
    }

    public Date addDay(Date date, int offset) {
        return add(date, Calendar.DAY_OF_YEAR, offset);
    }

    public Date add(Date date, int field, int value) {
        if (date == null) {
            throw new IllegalArgumentException();
        }
        if (value == 0) {
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, value);
        return calendar.getTime();
    }

    public Date min(Date first, Date... others) {
        Date minDate = first;
        for (Date date : others) {
            if (date.compareTo(minDate) < 0) {
                minDate = date;
            }
        }
        return minDate;
    }

    public Date max(Date first, Date... others) {
        Date maxDate = first;
        for (Date date : others) {
            if (date.compareTo(maxDate) > 0) {
                maxDate = date;
            }
        }
        return maxDate;
    }

    public int getYear(Date date) {
        return getCalendarField(date, Calendar.YEAR);
    }

    public int getMonth(Date date) {
        return getCalendarField(date, Calendar.MONTH);
    }

    public int getDayOfMonth(Date date) {
        return getCalendarField(date, Calendar.DAY_OF_MONTH);
    }

    public int getCalendarField(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    public Date getToday() {
        return truncateToDay(new Date());
    }

    public List<Date> getDaysBetween(Date from, Date to) {
        List<Date> result = new ArrayList<>();
        Date current = from;
        do {
            result.add(current);
            current = dayAfter(current);
        } while (current.before(to));
        result.add(to);
        return result;
    }

    public double diffInDays(Date afterDate, Date beforeDate) {
        return (double) (afterDate.getTime() - beforeDate.getTime()) / MILLISECONDS_IN_DAY;
    }

    private void truncateToDay(final Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
