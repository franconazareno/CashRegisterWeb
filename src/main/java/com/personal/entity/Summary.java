package com.personal.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "summary")
public class Summary implements Serializable {

    private Long id;
    private int countOne;
    private int countTwo;
    private int countFive;
    private int countTen;
    private int countTwenty;
    private int valueOne;
    private int valueTwo;
    private int valueFive;
    private int valueTen;
    private int valueTwenty;
    private int valueSum;
    private Date createDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCountOne() {
        return countOne;
    }

    public void setCountOne(int countOne) {
        this.countOne = countOne;
    }

    public int getCountTwo() {
        return countTwo;
    }

    public void setCountTwo(int countTwo) {
        this.countTwo = countTwo;
    }

    public int getCountFive() {
        return countFive;
    }

    public void setCountFive(int countFive) {
        this.countFive = countFive;
    }

    public int getCountTen() {
        return countTen;
    }

    public void setCountTen(int countTen) {
        this.countTen = countTen;
    }

    public int getCountTwenty() {
        return countTwenty;
    }

    public void setCountTwenty(int countTwenty) {
        this.countTwenty = countTwenty;
    }

    public int getValueOne() {
        return valueOne;
    }

    public void setValueOne(int valueOne) {
        this.valueOne = valueOne;
    }

    public int getValueTwo() {
        return valueTwo;
    }

    public void setValueTwo(int valueTwo) {
        this.valueTwo = valueTwo;
    }

    public int getValueFive() {
        return valueFive;
    }

    public void setValueFive(int valueFive) {
        this.valueFive = valueFive;
    }

    public int getValueTen() {
        return valueTen;
    }

    public void setValueTen(int valueTen) {
        this.valueTen = valueTen;
    }

    public int getValueTwenty() {
        return valueTwenty;
    }

    public void setValueTwenty(int valueTwenty) {
        this.valueTwenty = valueTwenty;
    }

    public int getValueSum() {
        return valueSum;
    }

    public void setValueSum(int valueSum) {
        this.valueSum = valueSum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Summary{id=" + getId() +
                ",countOne=" + getCountOne() +
                ",countTwo=" + getCountTwo() +
                ",countFive=" + getCountFive() +
                ",countTen=" + getCountTen() +
                ",countTwenty=" + getCountTwenty() +
                ",valueOne=" + getValueOne() +
                ",valueTwo=" + getValueTwo() +
                ",valueFive=" + getValueFive() +
                ",valueTen=" + getValueTen() +
                ",valueTwenty=" + getValueTwenty() +
                ",valueSum=" + getValueSum() +
                ",createDate=" + getCreateDate() +
                "}";
    }

    public static class Builder {
        private Summary obj;

        public Builder() {
            this.obj = new Summary();
        }

        public Builder countOne(Integer countOne) {
            obj.countOne = countOne;
            return this;
        }

        public Builder countTwo(Integer countTwo) {
            obj.countTwo = countTwo;
            return this;
        }

        public Builder countFive(Integer countFive) {
            obj.countFive = countFive;
            return this;
        }

        public Builder countTen(Integer countTen) {
            obj.countTen = countTen;
            return this;
        }

        public Builder countTwenty(Integer countTwenty) {
            obj.countTwenty = countTwenty;
            return this;
        }

        public Builder valueOne(Integer valueOne) {
            obj.valueOne = valueOne;
            return this;
        }

        public Builder valueTwo(Integer valueTwo) {
            obj.valueTwo = valueTwo;
            return this;
        }

        public Builder valueFive(Integer valueFive) {
            obj.valueFive = valueFive;
            return this;
        }

        public Builder valueTen(Integer valueTen) {
            obj.valueTen = valueTen;
            return this;
        }

        public Builder valueTwenty(Integer valueTwenty) {
            obj.valueTwenty = valueTwenty;
            return this;
        }

        public Builder valueSum(Integer valueSum) {
            obj.valueSum = valueSum;
            return this;
        }

        public Summary build() {
            return obj;
        }

    }
}
