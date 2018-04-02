package ru.lantimat.worksearch.vacancy;

import java.util.Date;

/**
 * Created by GabdrakhmanovII on 04.09.2017.
 */

public class Vacancy {

    public String id;
    public String name;
    public String salary;
    public Date date;
    public String companyName;
    public String city;
    public String experiency;
    public String workType;
    public String fullText;
    public String numberForCall;

    public Vacancy() {
    }

    public Vacancy(String name, String salary, Date date, String companyName, String city, String experiency, String workType, String fullText, String numberForCall) {
        this.name = name;
        this.salary = salary;
        this.date = date;
        this.companyName = companyName;
        this.city = city;
        this.experiency = experiency;
        this.workType = workType;
        this.fullText = fullText;
        this.numberForCall = numberForCall;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExperiency() {
        return experiency;
    }

    public void setExperiency(String experiency) {
        this.experiency = experiency;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getNumberForCall() {
        return numberForCall;
    }

    public void setNumberForCall(String numberForCall) {
        this.numberForCall = numberForCall;
    }
}
