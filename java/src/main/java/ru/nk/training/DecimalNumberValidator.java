package ru.nk.training;

import java.util.HashMap;
import java.util.Map;

/**
 * Validate if a given string can be interpreted as a decimal number.
 * It is intended for the problem statement to be ambiguous. You should gather all requirements up front before
 * implementing one. However, here is a list of characters that can be in a valid decimal number:
 * Numbers 0-9
 * Exponent - "e"
 * Positive/negative sign - "+"/"-"
 * Decimal point - "."
 */
public class DecimalNumberValidator {
    private static final Map<State, Map<TokenType, State>> TRANSITIONS;

    static {
        TRANSITIONS = new HashMap<>();

        TRANSITIONS.put(State.INITIAL, getTransitionsFromInitial());
        TRANSITIONS.put(State.SIGN, getTransitionsFromSign());
        TRANSITIONS.put(State.INTEGER_PART, getTransitionsFromIntegerPart());
        TRANSITIONS.put(State.STARTER_POINT, getTransitionsFromStarterPoint());
        TRANSITIONS.put(State.POINT, getTransitionsFromPoint());
        TRANSITIONS.put(State.DECIMAL_PART, getTransitionsFromDecimalPart());
        TRANSITIONS.put(State.EXPONENT, getTransitionsFromExponent());
        TRANSITIONS.put(State.EXPONENT_SIGN, getTransitionsFromExponentSign());
        TRANSITIONS.put(State.EXPONENT_PART, getTransitionsFromExponentPart());
    }

    public boolean isNumber(String s) {
        s = s.trim();
        State state = State.INITIAL;
        for (char c : s.toCharArray()) {
            state = process(getType(c), state);
            if (state == State.ERROR) {
                return false;
            }
        }
        state = process(TokenType.EOL, state);
        return state == State.SUCCESS;
    }

    private static Map<TokenType, State> getTransitionsFromExponentPart() {
        Map<TokenType, State> transitions = new HashMap<>();
        transitions.put(TokenType.DIGIT, State.EXPONENT_PART);
        transitions.put(TokenType.EOL, State.SUCCESS);
        return transitions;
    }

    private static Map<TokenType, State> getTransitionsFromExponentSign() {
        Map<TokenType, State> transitions = new HashMap<>();
        transitions.put(TokenType.DIGIT, State.EXPONENT_PART);
        return transitions;
    }

    private static Map<TokenType, State> getTransitionsFromExponent() {
        Map<TokenType, State> transitions = new HashMap<>();
        transitions.put(TokenType.DIGIT, State.EXPONENT_PART);
        transitions.put(TokenType.SIGN, State.EXPONENT_SIGN);
        return transitions;
    }

    private static Map<TokenType, State> getTransitionsFromDecimalPart() {
        Map<TokenType, State> transitions = new HashMap<>();
        transitions.put(TokenType.DIGIT, State.DECIMAL_PART);
        transitions.put(TokenType.EXPONENT, State.EXPONENT);
        transitions.put(TokenType.EOL, State.SUCCESS);
        return transitions;
    }

    private static Map<TokenType, State> getTransitionsFromPoint() {
        Map<TokenType, State> transitions = new HashMap<>();
        transitions.put(TokenType.DIGIT, State.DECIMAL_PART);
        transitions.put(TokenType.EXPONENT, State.EXPONENT);
        transitions.put(TokenType.EOL, State.SUCCESS);
        return transitions;
    }

    private static Map<TokenType, State> getTransitionsFromStarterPoint() {
        Map<TokenType, State> transitions = new HashMap<>();
        transitions.put(TokenType.DIGIT, State.DECIMAL_PART);
        return transitions;
    }

    private static Map<TokenType, State> getTransitionsFromIntegerPart() {
        Map<TokenType, State> transitions = new HashMap<>();
        transitions.put(TokenType.DIGIT, State.INTEGER_PART);
        transitions.put(TokenType.EXPONENT, State.EXPONENT);
        transitions.put(TokenType.POINT, State.POINT);
        transitions.put(TokenType.EOL, State.SUCCESS);
        return transitions;
    }

    private static Map<TokenType, State> getTransitionsFromSign() {
        Map<TokenType, State> transitions = new HashMap<>();
        transitions.put(TokenType.DIGIT, State.INTEGER_PART);
        transitions.put(TokenType.POINT, State.STARTER_POINT);
        return transitions;
    }

    private static Map<TokenType, State> getTransitionsFromInitial() {
        Map<TokenType, State> transitions = new HashMap<>();
        transitions.put(TokenType.POINT, State.STARTER_POINT);
        transitions.put(TokenType.SIGN, State.SIGN);
        transitions.put(TokenType.DIGIT, State.INTEGER_PART);
        return transitions;
    }

    private static State process(TokenType tokenType, State curState) {
        Map<TokenType, State> stateTransitions = TRANSITIONS.get(curState);
        if (stateTransitions == null) {
            return State.ERROR;
        }

        return stateTransitions.getOrDefault(tokenType, State.ERROR);
    }

    private static TokenType getType(char c) {
        if (Character.isDigit(c)) {
            return TokenType.DIGIT;
        }
        if (c == '.') {
            return TokenType.POINT;
        }
        if (c == 'e' || c == 'E') {
            return TokenType.EXPONENT;
        }
        if (c == '+' || c == '-') {
            return TokenType.SIGN;
        }
        return TokenType.OTHER;
    }

    enum State {
        INITIAL,
        SIGN,
        INTEGER_PART,
        POINT,
        STARTER_POINT,
        DECIMAL_PART,
        EXPONENT,
        EXPONENT_SIGN,
        EXPONENT_PART,
        SUCCESS,
        ERROR
    }

    enum TokenType {
        DIGIT,
        SIGN,
        POINT,
        EXPONENT,
        EOL,
        OTHER
    }
}
