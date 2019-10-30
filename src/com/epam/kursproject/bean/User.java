package com.epam.kursproject.bean;

import java.util.Objects;

public class User {
    private String name;
    private double amountOfMoney;

    public User(String name, double amountOfMoney) {
        this.name = name;
        this.amountOfMoney = amountOfMoney;
    }

    public String getName() {
        return name;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", amountOfMoney=" + amountOfMoney +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null) return false;
        if (getClass() != o.getClass()) return false;

        User user = (User) o;

        if (Double.compare(user.amountOfMoney, amountOfMoney) != 0) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(amountOfMoney);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
