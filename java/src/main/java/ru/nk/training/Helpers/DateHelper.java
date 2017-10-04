package ru.nk.training.Helpers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Helper methods for common operations with java.util.Date
 */
public class DateHelper {
    public final long MILLISECONDS_IN_SECOND = 1000L;
    public final long MILLISECONDS_IN_MINUTE = MILLISECONDS_IN_SECOND * 60;
    public final long MILLISECONDS_IN_HOUR = MILLISECONDS_IN_MINUTE * 60;
    public final long MILLISECONDS_IN_DAY = MILLISECONDS_IN_HOUR * 24;

    /**
     * Removes hour, minutes, seconds and milliseconds from date
     */
    public Date withTimeAtStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        withTimeAtStartOfDay(calendar);
        return calendar.getTime();
    }

    /**
     * Returns next day after specified one
     */
    public Date dayAfter(Date date) {
        return add(date, Calendar.DAY_OF_YEAR, 1);
    }

    /**
     * Returns a day before specified one
     */
    public Date dayBefore(Date date) {
        return add(date, Calendar.DAY_OF_YEAR, -1);
    }

    /**
     * Returns date shifted by the specified offset of days
     */
    public Date addDays(Date date, int offset) {
        return add(date, Calendar.DAY_OF_YEAR, offset);
    }

    /**
     * Returns date shifted by the specified offset of Calendar field
     */
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

    /**
     * Returns the earliest date of provided dates
     */
    public Date min(Date first, Date... others) {
        Date minDate = first;
        for (Date date : others) {
            if (date.compareTo(minDate) < 0) {
                minDate = date;
            }
        }
        return minDate;
    }

    /**
     * Returns the latest date of provided dates
     */
    public Date max(Date first, Date... others) {
        Date maxDate = first;
        for (Date date : others) {
            if (date.compareTo(maxDate) > 0) {
                maxDate = date;
            }
        }
        return maxDate;
    }

    /**
     * Returns year of a date
     */
    public int getYear(Date date) {
        return getCalendarField(date, Calendar.YEAR);
    }

    /**
     * Returns month of a date
     */
    public int getMonth(Date date) {
        return getCalendarField(date, Calendar.MONTH);
    }

    /**
     * Returns day of month of a date
     */
    public int getDayOfMonth(Date date) {
        return getCalendarField(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * Returns specified Calendar field of a date
     */
    public int getCalendarField(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    /**
     * Returns current date object truncated up to day
     */
    public Date getToday() {
        return withTimeAtStartOfDay(new Date());
    }

    /**
     * Returns all dates with single day offset between from and to including both of them
     */
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

    /**
     * Returns difference between two dates in days
     */
    public double diffInDays(Date afterDate, Date beforeDate) {
        return (double) (afterDate.getTime() - beforeDate.getTime()) / MILLISECONDS_IN_DAY;
    }

    private void withTimeAtStartOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
