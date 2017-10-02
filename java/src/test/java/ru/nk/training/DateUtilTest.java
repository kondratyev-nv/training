package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class DateUtilTest {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private DateUtil util;

    @Before
    public void setUp() throws Exception {
        util = new DateUtil();
    }

    @Test
    public void getsTodayWithDayPrecision() throws Exception {
        Date today = util.getToday();

        Calendar expected = Calendar.getInstance();
        expected.setTime(new Date());
        assertDateEqualsWithDayPrecision(today,
                expected.get(Calendar.YEAR),
                expected.get(Calendar.MONTH),
                expected.get(Calendar.DATE));
    }

    @Test
    public void canGetDateFields() throws Exception {
        Date date = sdf.parse("2017-02-05");
        assertEquals(2017, util.getYear(date));
        assertEquals(1, util.getMonth(date));
        assertEquals(5, util.getDayOfMonth(date));
    }

    @Test
    public void canTruncateDateToDayPrecision() throws Exception {
        Date today = util.truncateToDay(new Date());

        Calendar expected = Calendar.getInstance();
        expected.setTime(new Date());
        assertDateEqualsWithDayPrecision(today,
                expected.get(Calendar.YEAR),
                expected.get(Calendar.MONTH),
                expected.get(Calendar.DATE));
    }

    @Test
    public void canTruncateMillisecondsToDayPrecision() throws Exception {
        long midnightMilliseconds = util.truncateToDay(new Date().getTime());

        Calendar expected = Calendar.getInstance();
        expected.setTime(new Date());
        assertDateEqualsWithDayPrecision(new Date(midnightMilliseconds),
                expected.get(Calendar.YEAR),
                expected.get(Calendar.MONTH),
                expected.get(Calendar.DATE));
    }

    @Test
    public void canAddDay() throws Exception {
        Date date = sdf.parse("2017-01-01");
        Date tomorrow = util.addDay(date, 1);
        assertDateEqualsWithDayPrecision(tomorrow, 2017, Calendar.JANUARY, 2);
    }

    @Test
    public void addingZeroReturnsSameDate() throws Exception {
        Date date = sdf.parse("2017-01-01");
        Date same = util.addDay(date, 0);
        assertEquals(date, same);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addingDateThrowsWhenDateIsNull() throws Exception {
        util.add(null, Calendar.DAY_OF_YEAR, 1);
    }

    @Test
    public void canSubtractDay() throws Exception {
        Date date = sdf.parse("2017-01-02");
        Date tomorrow = util.addDay(date, -1);
        assertDateEqualsWithDayPrecision(tomorrow, 2017, Calendar.JANUARY, 1);
    }

    @Test
    public void addDayMovesThroughMonthAndYear() throws Exception {
        Date date = sdf.parse("2016-12-31");
        Date tomorrow = util.addDay(date, 1);
        assertDateEqualsWithDayPrecision(tomorrow, 2017, Calendar.JANUARY, 1);
    }

    @Test
    public void previousDayMovesThroughMonthAndYear() throws Exception {
        Date date = sdf.parse("2017-01-01");
        Date tomorrow = util.addDay(date, -1);
        assertDateEqualsWithDayPrecision(tomorrow, 2016, Calendar.DECEMBER, 31);
    }

    @Test
    public void addDayIsTheSameAsDayAfter() throws Exception {
        Date now = new Date();
        Date tomorrowByAfter = util.addDay(now, 1);
        Date tomorrowByNext = util.dayAfter(now);
        assertEquals(tomorrowByAfter, tomorrowByNext);
    }

    @Test
    public void subtractDayIsTheSameAsDayBefore() throws Exception {
        Date now = new Date();
        Date yesterdayByAdd = util.addDay(now, -1);
        Date yesterdayByBefore = util.dayBefore(now);
        assertEquals(yesterdayByAdd, yesterdayByBefore);
    }

    @Test
    public void canGetMinDateOfSingleDate() throws Exception {
        Date now = new Date();
        Date sameDate = util.min(now);
        assertSame(now, sameDate);
    }

    @Test
    public void canGetMinDateOfMultipleDates() throws Exception {
        Date min = util.min(sdf.parse("2017-01-01"),
                sdf.parse("2015-02-05"),
                sdf.parse("2018-06-14"),
                sdf.parse("2017-01-01"));
        assertEquals(sdf.parse("2015-02-05"), min);
    }

    @Test
    public void canGetMaxDateOfSingleDate() throws Exception {
        Date now = new Date();
        Date sameDate = util.max(now);
        assertSame(now, sameDate);
    }

    @Test
    public void canGetMaxDateOfMultipleDates() throws Exception {
        Date max = util.max(sdf.parse("2017-01-01"),
                sdf.parse("2015-02-05"),
                sdf.parse("2018-06-14"),
                sdf.parse("2017-01-01"));
        assertEquals(sdf.parse("2018-06-14"), max);
    }

    @Test
    public void canGetDaysBetween() throws Exception {
        Date from = sdf.parse("2017-01-01");
        Date to = sdf.parse("2017-01-04");
        List<Date> actual = util.getDaysBetween(from, to);
        assertEquals(actual.size(), 4);
        assertEquals(Arrays.asList(from, sdf.parse("2017-01-02"), sdf.parse("2017-01-03"), to), actual);
    }

    @Test
    public void canGetDiffInDays() throws Exception {
        Date now = new Date();
        Date tomorrow = util.dayAfter(now);
        assertEquals(1.0, util.diffInDays(tomorrow, now), 0.001);
    }

    @Test
    public void canGetDecimalDiffInDays() throws Exception {
        Date now = new Date();
        Date tomorrow = util.add(util.dayAfter(now), Calendar.HOUR_OF_DAY, 12);
        assertEquals(1.5, util.diffInDays(tomorrow, now), 0.001);
    }

    private void assertDateEqualsWithDayPrecision(Date actual, int year, int month, int day) {
        Calendar actualCalendar = Calendar.getInstance();
        actualCalendar.setTime(actual);
        assertEquals(year, actualCalendar.get(Calendar.YEAR));
        assertEquals(month, actualCalendar.get(Calendar.MONTH));
        assertEquals(day, actualCalendar.get(Calendar.DATE));
        assertEquals(0, actualCalendar.get(Calendar.HOUR));
        assertEquals(0, actualCalendar.get(Calendar.MINUTE));
        assertEquals(0, actualCalendar.get(Calendar.SECOND));
        assertEquals(0, actualCalendar.get(Calendar.MILLISECOND));
    }
}
