package net.javaguides.springbootrestapi.bean;

public class Employee {

    private String ssn;
    private String firstName;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String sex;

    @Override
    public String toString() {
        return "Employee{" +
                "ssn='" + ssn + '\'' +
                ", firstName='" + firstName + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public Employee(String ssn, String firstName, String sex) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.sex = sex;
    }
}
