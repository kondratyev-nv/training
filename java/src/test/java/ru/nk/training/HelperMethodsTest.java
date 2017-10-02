package ru.nk.training;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelperMethodsTest {
    private HelperMethods methods;

    @Before
    public void setUp() throws Exception {
        methods = new HelperMethods();
    }

    @Test
    public void avoidNullCanReturnFirstValue() throws Exception {
        String notNullString = methods.avoidNull("1", "2");
        assertEquals("1", notNullString);
    }

    @Test
    public void avoidNullReturnsFirstNotNullValue() throws Exception {
        String notNullString = methods.avoidNull(null, "1", "2");
        assertEquals("1", notNullString);
    }

    @Test
    public void avoidNullReturnsFirstValue() throws Exception {
        String notNullString = methods.avoidNull(null, "1", "2");
        assertEquals("1", notNullString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void avoidNullThrowsWhenNoNotNullValues() throws Exception {
        methods.avoidNull(null, null, null);
    }

    @Test
    public void doesNotUseDefaultValueWhenTargetValueIsNotNull() throws Exception {
        String value = methods.getOrDefault("1", null);
        assertEquals("1", value);
    }

    @Test
    public void usesDefaultValueWhenTargetValueIsNull() throws Exception {
        String value = methods.getOrDefault(null, "1");
        assertEquals("1", value);
    }

    @Test
    public void doesNotUseDefaultValueWhenAtLeastOneTargetValueIsNotNull() throws Exception {
        String value = methods.getOrDefault("default", null, null, "a", "b");
        assertEquals("a", value);
    }

    @Test
    public void usesDefaultValueWhenAllTargetValuesAreNull() throws Exception {
        String value = methods.getOrDefault("default", null, null, null);
        assertEquals("default", value);
    }
}
