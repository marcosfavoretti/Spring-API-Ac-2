package com.example.demo.domain.valueObjects;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Email {
    private String endereco;

    protected Email() {
    }

    public Email(String endereco) {
        if (endereco == null || !endereco.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("NAO PODE COLOCAR ESSE EMAIL");
        }
        this.endereco = endereco;

    }

    public String getEndereco() {
        return this.endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Email email = (Email) o;
        return Objects.equals(this.endereco, email.endereco);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.endereco);
    }
}
