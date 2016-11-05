package ru.sberbank.bit.kolpakov.java.refleciton;

/**
 * Created by Georgii Kolpakov on 05.11.16.
 */
public class Person {
    private String firstName;
    private String secondName;
    private Integer age;
    private Object lock;
    private Integer inn;
    private Integer postCode;

    public Person(String firstName, String secondName, Integer age, Object lock, Integer inn, Integer postCode) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.lock = lock;
        this.inn = inn;
        this.postCode = postCode;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    private void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLock() {
        return lock.toString();
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public Integer getInn() {
        return inn;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Number postCode) {
        this.postCode = postCode.intValue();
    }
}
