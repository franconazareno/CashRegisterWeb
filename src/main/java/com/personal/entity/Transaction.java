package com.personal.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private Long id;
    private String type;
    private int one;
    private int two;
    private int five;
    private int ten;
    private int twenty;
    private Date create_date;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOne() {
        return one;
    }

    public void setOne(int one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }

    public int getFive() {
        return five;
    }

    public void setFive(int five) {
        this.five = five;
    }

    public int getTen() {
        return ten;
    }

    public void setTen(int ten) {
        this.ten = ten;
    }

    public int getTwenty() {
        return twenty;
    }

    public void setTwenty(int twenty) {
        this.twenty = twenty;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    @Override
    public String toString() {
        return "Transaction{id=" + getId() +
                ",type=" + getType() +
                ",one=" + getOne() +
                ",two=" + getTwo() +
                ",five=" + getFive() +
                ",ten=" + getTen() +
                ",twenty=" + getTwenty() +
                "}";
    }

    public static class Builder {
        private Transaction obj;

        public Builder() {
            this.obj = new Transaction();
        }

        public Builder type(String type) {
            obj.type = type;
            return this;
        }

        public Builder one(Integer one) {
            obj.one = one;
            return this;
        }

        public Builder two(Integer two) {
            obj.two = two;
            return this;
        }

        public Builder five(Integer five) {
            obj.five = five;
            return this;
        }

        public Builder ten(Integer ten) {
            obj.ten = ten;
            return this;
        }

        public Builder twenty(Integer twenty) {
            obj.twenty = twenty;
            return this;
        }

        public Transaction build() {
            return obj;
        }

    }

}
