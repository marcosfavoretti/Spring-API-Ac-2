package com.example.demo.valueObjects;

import org.junit.jupiter.api.Test;

import com.example.demo.domain.valueObjects.Ra;

import static org.junit.jupiter.api.Assertions.*;

class RaTest {

    @Test
    void shouldCreateValidRa() {
        Ra ra = new Ra("123456");
        assertNotNull(ra);
        assertEquals("123456", ra.getRa());
    }

    @Test
    void shouldThrowExceptionForInvalidRaLength() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Ra("12345"), // RA com 5 caracteres
                "Expected an exception for RA length != 6"
        );
        assertEquals("RA NAO PODE", exception.getMessage());
    }

    @Test
    void shouldBeEqualForSameRaValues() {
        Ra ra1 = new Ra("123456");
        Ra ra2 = new Ra("123456");

        assertEquals(ra1, ra2);
        assertEquals(ra1.hashCode(), ra2.hashCode());
    }

    @Test
    void shouldNotBeEqualForDifferentRaValues() {
        Ra ra1 = new Ra("123456");
        Ra ra2 = new Ra("654321");

        assertNotEquals(ra1, ra2);
    }

    @Test
    void shouldNotBeEqualToNullOrDifferentClass() {
        Ra ra = new Ra("123456");

        assertNotEquals(ra, null); // Comparação com null
        assertNotEquals(ra, "123456"); // Comparação com objeto de outro tipo
    }
}
