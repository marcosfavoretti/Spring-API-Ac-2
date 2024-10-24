package com.example.demo.domain.valueObjects;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Ra {
    private String ra;

    protected Ra(){}

    public Ra(String ra){
        if(ra.length() != 6){
            throw new IllegalArgumentException("RA NAO PODE");
        }
        this.ra = ra;
    }

      @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Ra ra = (Ra) o;
        return Objects.equals(this.ra, ra.ra);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.ra);
    }
}   
