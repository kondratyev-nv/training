package ru.nk.training;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecimalNumberValidatorTest {
    private final DecimalNumberValidator validator = new DecimalNumberValidator();

    @ParameterizedTest
    @ValueSource(strings = {
            "1",
            "853958945",
            "0.1",
            ".0",
            "0.",
            "1e-2",
            "23.678E+758",
            "+0",
            "-565.811e-11",
            " 1.66 "
    })
    public void success(String val) {
        assertTrue(validator.isNumber(val));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            ".",
            "qwerty",
            "21131e",
            "5345q1",
            "++1",
            "+",
            "E2",
            "1-",
            "+-1",
            "1..3",
            "4,5",
            "   ",
            "2 3"
    })
    public void failure(String val) {
        assertFalse(validator.isNumber(val));
    }
}