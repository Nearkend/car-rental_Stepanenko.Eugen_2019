package ua.nure.stepanenko.thesis.model.entyty;

import ua.nure.stepanenko.thesis.model.constant.Class;
import ua.nure.stepanenko.thesis.model.constant.Mark;

import java.io.Serializable;
public class Car implements Serializable {

    private int id;
    private Mark mark;
    private Class clazz;
    private String name;
    private int cost;
    private boolean thereIs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isThereIs() {
        return thereIs;
    }

    public void setThereIs(boolean thereIs) {
        this.thereIs = thereIs;
    }

    @Override
    public String toString() {
        return "id - " + id +
                ", mark - " + mark +
                ", clazz - " + clazz +
                ", name - " + name +
                ", cost - " + cost +
                ", thereIs - " + thereIs;
    }
}
