package ru.nk.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Natalia
 * @since 09-Jul-17.
 */
public class BigInteger {
    private final List<Integer> digits;

    public BigInteger(){
        this(0);
    }

    public BigInteger(int i){
        if (i < 0){
            throw new IllegalArgumentException("Negative numbers are not supported yet");
        }

        digits = new ArrayList<Integer>();

        if (i == 0){
            digits.add(0);
            return;
        }

        while (i > 0) {
            digits.add(i % 10);
            i /= 10;
        }
    }

    public BigInteger(String digits) {
        if (digits == null || digits.isEmpty()) {
            throw new IllegalArgumentException("Digits are empty");
        }
        if (!digits.chars().allMatch(Character::isDigit)){
            throw new IllegalArgumentException("Digits are not in range [0,9]");
        }

        this.digits = new ArrayList<>();
        for (int i = digits.length() - 1; i >= 0; --i){
            this.digits.add(digits.charAt(i) - '0');
        }

        trimLeadingZeros();
    }

    public BigInteger(List<Integer> digits) {
        if (digits == null || digits.isEmpty()) {
            throw new IllegalArgumentException("Digits are empty");
        }
        if (!digits.stream().allMatch(i -> 0 <= i && i <= 9)){
            throw new IllegalArgumentException("Digits are not in range [0,9]");
        }

        this.digits = new ArrayList<>(digits);
        trimLeadingZeros();
    }

    public List<Integer> getDigits() {
        return new ArrayList<>(digits);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = digits.size() - 1; i >= 0; --i){
            builder.append(digits.get(i));
        }

        return builder.toString();
    }

    public BigInteger add(BigInteger other){
        List<Integer> sumDigits = new ArrayList<>();

        int length = Math.max(this.size(), other.size());
        int carry = 0;
        for (int i = 0; i < length; ++i){
            int sum = this.getDigit(i) + other.getDigit(i) + carry;
            sumDigits.add(sum % 10);
            carry = sum / 10;
        }

        if (carry != 0){
            sumDigits.add(carry);
        }

        return new BigInteger(sumDigits);
    }

    public BigInteger multiply(BigInteger other) {
        if (this.isZero() || other.isZero()) {
            return new BigInteger(0);
        }

        if (this.size() == 1 && other.size() == 1) {
            return new BigInteger(this.getDigit(0) * other.getDigit(0));
        }

        int maxSize = Math.max(this.size(), other.size());

        int secondHalfIndex = maxSize / 2;

        BigInteger x0 = this.getSubInteger(0, secondHalfIndex);
        BigInteger x1 = this.getSubInteger(secondHalfIndex, maxSize);

        BigInteger y0 = other.getSubInteger(0, secondHalfIndex);
        BigInteger y1 = other.getSubInteger(secondHalfIndex, maxSize);

        BigInteger z2 = x1.multiply(y1);
        BigInteger z0 = x0.multiply(y0);
        BigInteger z1 = x1.multiply(y0).add(x0.multiply(y1));

        return z2.multiplyByPowerOf10(secondHalfIndex * 2).add(z1.multiplyByPowerOf10(secondHalfIndex)).add(z0);
    }

    public int size(){
        return digits.size();
    }

    public boolean isZero(){
        return digits.size() == 1 && getDigit(0) == 0;
    }

    public BigInteger getSubInteger(int from, int to){
        if (from < 0){
            throw new IllegalArgumentException("Negative index 'from'");
        }

        if (from >= to){
            throw new IllegalArgumentException("'to' should be greater than 'from'");
        }

        List<Integer> result = new ArrayList<>();

        for (int i = from; i < to; ++i){
            result.add(getDigit(i));
        }

        return new BigInteger(result);
    }

    public BigInteger multiplyByPowerOf10(int power){
        if (power < 0){
            throw new IllegalArgumentException("'power' should not be negative");
        }

        List<Integer> resultDigits = new ArrayList<>(Collections.nCopies(power, 0));
        resultDigits.addAll(this.digits);

        return new BigInteger(resultDigits);
    }

    private int getDigit(int index){
        if (index >= digits.size()){
            return 0;
        }

        return digits.get(index);
    }

    private void trimLeadingZeros(){
        int startDigitIndex = digits.size() - 1;

        while (startDigitIndex >= 0 && digits.get(startDigitIndex) == 0){
            --startDigitIndex;
        }

        if (startDigitIndex == -1){
            startDigitIndex = 0;
        }

        if (startDigitIndex == digits.size() - 1){
            return;
        }

        digits.subList(startDigitIndex + 1, digits.size()).clear();
    }
}
