package br.com.leogsouza.awesome.model;

import javax.persistence.Entity;

@Entity
public class Student extends AbstractEntity{

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


}
