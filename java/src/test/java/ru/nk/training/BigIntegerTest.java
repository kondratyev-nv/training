package ru.nk.training;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Natalia
 * @since 09-Jul-17.
 */
public class BigIntegerTest {
    @Test(expected = IllegalArgumentException.class)
    public void throwsIfConstructingFromNegativeNumber() throws Exception {
        new BigInteger(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIfConstructingFromEmptyString() throws Exception {
        new BigInteger("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIfConstructingFromEmptyList() throws Exception {
        new BigInteger(new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIfConstructingFromNotAllDigitString() throws Exception {
        new BigInteger("12345q");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIfConstructingFromNotAllDigitList() throws Exception {
        new BigInteger(Arrays.asList(1, 2, 3, 4, 5, 10));
    }

    @Test
    public void constructsFromZeroIntegerCorrectly() throws Exception {
        BigInteger i = new BigInteger(0); assertEquals(1, i.getDigits().size());
        assertEquals(0, (long) i.getDigits().get(0));
    }

    @Test
    public void constructsFromZeroStringCorrectly() throws Exception {
        BigInteger i = new BigInteger("0"); assertEquals(1, i.getDigits().size());
        assertEquals(0, (long) i.getDigits().get(0));
    }

    @Test
    public void constructsFromZeroListCorrectly() throws Exception {
        BigInteger i = new BigInteger(Arrays.asList(0)); assertEquals(1, i.getDigits().size());
        assertEquals(0, (long) i.getDigits().get(0));
    }

    @Test
    public void defaultConstructorReturnsZero() throws Exception {
        BigInteger i = new BigInteger(); assertEquals(1, i.getDigits().size());
        assertEquals(0, (long) i.getDigits().get(0));
    }

    @Test
    public void constructsFromIntegerCorrectly() throws Exception {
        BigInteger i = new BigInteger(12345); assertEquals(5, i.getDigits().size());
        assertEquals(Arrays.asList(5, 4, 3, 2, 1), i.getDigits());
    }

    @Test
    public void constructsFromStringCorrectly() throws Exception {
        BigInteger i = new BigInteger("12345"); assertEquals(5, i.getDigits().size());
        assertEquals(Arrays.asList(5, 4, 3, 2, 1), i.getDigits());
    }

    @Test
    public void constructsFromListCorrectly() throws Exception {
        BigInteger i = new BigInteger(Arrays.asList(5, 4, 3, 2, 1));
        assertEquals(5, i.getDigits().size());
        assertEquals(Arrays.asList(5, 4, 3, 2, 1), i.getDigits());
    }

    @Test
    public void constructsFromStringSkippingLeadingZeros() throws Exception {
        BigInteger i = new BigInteger("0006780900"); assertEquals(7, i.getDigits().size());
        assertEquals(Arrays.asList(0, 0, 9, 0, 8, 7, 6), i.getDigits());
    }

    @Test
    public void constructsFromListSkippingLeadingZeros() throws Exception {
        BigInteger i = new BigInteger(Arrays.asList(0, 0, 9, 0, 8, 7, 6, 0, 0, 0));
        assertEquals(7, i.getDigits().size());
        assertEquals(Arrays.asList(0, 0, 9, 0, 8, 7, 6), i.getDigits());
    }

    @Test
    public void toStringReturnsDigitStringInCorrectOrder() throws Exception {
        int value = 1234567; BigInteger i = new BigInteger(value);
        assertEquals(Integer.toString(value), i.toString());
    }

    @Test
    public void sizeReturnsCountOrDigits() throws Exception {
        int value = 1234567; BigInteger i = new BigInteger(value);
        assertEquals(7, i.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSubIntegerThrowsIfFromIsNegative() throws Exception {
        BigInteger i = new BigInteger(1234567);
        i.getSubInteger(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSubIntegerThrowsIfToIsLessThanFrom() throws Exception {
        BigInteger i = new BigInteger(1234567);
        i.getSubInteger(2, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSubIntegerThrowsIfToIsEqualToFrom() throws Exception {
        BigInteger i = new BigInteger(1234567);
        i.getSubInteger(2, 2);
    }

    @Test
    public void getSubIntegerReturnsSubListIfIndicesAreInside() throws Exception {
        BigInteger i = new BigInteger(1234567);
        BigInteger subInteger = i.getSubInteger(2, 6);
        assertEquals("2345", subInteger.toString());
    }

    @Test
    public void getSubIntegerReturnsZeroIfIndicesAreOutside() throws Exception {
        BigInteger i = new BigInteger(1234567);
        BigInteger subInteger = i.getSubInteger(10, 20);
        assertEquals("0", subInteger.toString());
    }

    @Test
    public void getSubIntegerReturnsSubListToTheLastDigitIfToIsOutside() throws Exception {
        BigInteger i = new BigInteger(1234567);
        BigInteger subInteger = i.getSubInteger(2, 10);
        assertEquals("12345", subInteger.toString());
    }

    @Test
    public void isZeroReturnsTrueIfZero() throws Exception {
        assertTrue(new BigInteger(0).isZero());
    }

    @Test
    public void isZeroReturnsFalseIfNotZero() throws Exception {
        assertFalse(new BigInteger(12).isZero());
    }

    @Test(expected = IllegalArgumentException.class)
    public void multiplyByPowerOf10ThrowsIfPowerIsNegative() throws Exception {
        new BigInteger(123).multiplyByPowerOf10(-1);
    }

    @Test
    public void multiplyByPowerOf10ReturnsSameNumberIfPowerIsOne() throws Exception {
        BigInteger i = new BigInteger(123);
        BigInteger result = i.multiplyByPowerOf10(0);
        assertEquals(i.toString(), result.toString());
    }

    @Test
    public void multiplyByPowerOf10ReturnsMultipliedNumber() throws Exception {
        BigInteger i = new BigInteger(123);
        BigInteger result = i.multiplyByPowerOf10(5);
        assertEquals("12300000", result.toString());
    }

    @Test
    public void multiplyByPowerOf10ReturnsZeroIfNumberIsZero() throws Exception {
        BigInteger i = new BigInteger(0);
        BigInteger result = i.multiplyByPowerOf10(5);
        assertEquals("0", result.toString());
    }

    @Test
    public void addingSingleDigitIntegers() throws Exception {
        int a = 1; int b = 2;

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.add(bigB);

        assertEquals(Integer.toString(a + b), result.toString());
    }

    @Test
    public void addingSingleDigitIntegerWithZero() throws Exception {
        int a = 0; int b = 2;

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.add(bigB);

        assertEquals(Integer.toString(a + b), result.toString());
    }

    @Test
    public void addingSingleDigitIntegersWithOverflow() throws Exception {
        int a = 6; int b = 7;

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.add(bigB);

        assertEquals(Integer.toString(a + b), result.toString());
    }

    @Test
    public void addingMultipleDigitIntegers() throws Exception {
        int a = 32349; int b = 18405;

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.add(bigB);

        assertEquals(Integer.toString(a + b), result.toString());
    }

    @Test
    public void addingIntegersOfDifferentSize() throws Exception {
        int a = 9973993; int b = 18;

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.add(bigB);

        assertEquals(Integer.toString(a + b), result.toString());
    }

    @Test
    public void addingIntegersOfDifferentSizeWithOverflow() throws Exception {
        int a = 99999999; int b = 18;

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.add(bigB);

        assertEquals(Integer.toString(a + b), result.toString());
    }

    @Test
    public void addingIntegersBiggerThanInt() throws Exception {
        String a = "475837598275640"; String b = "93927596485668";

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.add(bigB);

        java.math.BigInteger testResult = (new java.math.BigInteger(a)).add(new java.math.BigInteger(
                b));

        assertEquals(testResult.toString(), result.toString());
    }

    @Test
    public void multiplyingSingleDigitIntegers() throws Exception {
        int a = 3; int b = 2;

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.multiply(bigB);

        assertEquals(Integer.toString(a * b), result.toString());
    }

    @Test
    public void multiplyingSingleDigitIntegerWithZero() throws Exception {
        int a = 0; int b = 2;

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.multiply(bigB);

        assertEquals(Integer.toString(a * b), result.toString());
    }

    @Test
    public void multiplyingSingleDigitIntegerWithOne() throws Exception {
        int a = 7; int b = 1;

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.multiply(bigB);

        assertEquals(Integer.toString(a * b), result.toString());
    }

    @Test
    public void multiplyingSingleDigitIntegersWithOverflow() throws Exception {
        int a = 6; int b = 7;

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.multiply(bigB);

        assertEquals(Integer.toString(a * b), result.toString());
    }

    @Test
    public void multiplyingMultipleDigitIntegerBySingleDigitInteger() throws Exception {
        int a = 323; int b = 2;

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.multiply(bigB);

        assertEquals(Integer.toString(a * b), result.toString());
    }

    @Test
    public void multiplyingMultipleDigitIntegers() throws Exception {
        int a = 323; int b = 184;

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.multiply(bigB);

        assertEquals(Integer.toString(a * b), result.toString());
    }

    @Test
    public void multiplyingIntegersOfDifferentSize() throws Exception {
        int a = 99307; int b = 180;

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.multiply(bigB);

        assertEquals(Integer.toString(a * b), result.toString());
    }

    @Test
    public void multiplyingIntegersBiggerThanInt() throws Exception {
        String a = "475837598275640"; String b = "93927596485668";

        BigInteger bigA = new BigInteger(a); BigInteger bigB = new BigInteger(b);
        BigInteger result = bigA.multiply(bigB);

        java.math.BigInteger testResult = (new java.math.BigInteger(a)).multiply(new java.math.BigInteger(
                b));

        assertEquals(testResult.toString(), result.toString());
    }


}