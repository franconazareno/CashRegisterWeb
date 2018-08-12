package com.personal.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dollar")
public class Dollar implements Serializable {

    private Long id;
    private String name;
    private int denomination;
    private int count;
    private int value;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static class Builder {
        private Dollar obj;

        public Builder() {
            this.obj = new Dollar();
        }

        public Builder name(String name) {
            obj.name = name;
            return this;
        }

        public Builder denomination(int denomination) {
            obj.denomination = denomination;
            return this;
        }

        public Builder count(int count) {
            obj.count = count;
            return this;
        }

        public Builder value(int value) {
            obj.value = value;
            return this;
        }

        public Dollar build() {
            return obj;
        }
    }

}
