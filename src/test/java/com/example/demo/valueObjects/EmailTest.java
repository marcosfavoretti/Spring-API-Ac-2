package com.example.demo.valueObjects;

import org.junit.jupiter.api.Test;

import com.example.demo.domain.valueObjects.Email;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void shouldCreateValidEmail() {
        Email email = new Email("test@example.com");
        assertNotNull(email);
        assertEquals("test@example.com", email.getEmail());
    }

    @Test
    void shouldThrowExceptionForNullEmail() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Email(null),
                "Expected an exception for null email"
        );
        assertEquals("NAO PODE COLOCAR ESSE EMAIL", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForInvalidEmail() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Email("invalid-email"),
                "Expected an exception for invalid email"
        );
        assertEquals("NAO PODE COLOCAR ESSE EMAIL", exception.getMessage());
    }

    @Test
    void shouldBeEqualForSameEmailValues() {
        Email email1 = new Email("test@example.com");
        Email email2 = new Email("test@example.com");

        assertEquals(email1, email2);
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void shouldNotBeEqualForDifferentEmailValues() {
        Email email1 = new Email("test@example.com");
        Email email2 = new Email("other@example.com");

        assertNotEquals(email1, email2);
    }

    @Test
    void shouldNotBeEqualToNullOrDifferentClass() {
        Email email = new Email("test@example.com");

        assertNotEquals(email, null); // Comparação com null
        assertNotEquals(email, "test@example.com"); // Comparação com objeto de outro tipo
    }
}
