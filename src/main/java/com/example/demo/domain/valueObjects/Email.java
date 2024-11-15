package com.example.demo.domain.valueObjects;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Email {
    private String email;

    protected Email() {}

    public Email(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("NAO PODE COLOCAR ESSE EMAIL");
        }
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Email email = (Email) o;
        return Objects.equals(this.email, email.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.email);
    }
}
