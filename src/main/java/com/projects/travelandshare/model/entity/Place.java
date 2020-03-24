package com.projects.travelandshare.model.entity;

import com.projects.travelandshare.util.Counties;
import com.projects.travelandshare.util.Types;

import javax.persistence.*;
import java.io.Serializable;
/**
 * <b>Place Entity</b>
 * Class that provides the columns to create to our database.
 * @author Cédric_P
 * */
@Entity
public class Place implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Counties county;
    private float grade;
    @Enumerated(EnumType.STRING)
    private Types type;
    private String pictureName;
    private String userReview;
    private double xaxis;
    private double yaxis;

    public Place() {
    }
    public Place(String newName, Counties newCounty, Types newType, double newXaxis, double newYaxis){
        this.name=newName;
        this.county=newCounty;
        this.type=newType;
        this.xaxis=newXaxis;
        this.yaxis=newYaxis;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Counties getCounty() {
        return county;
    }

    public void setCounty(Counties county) {
        this.county = county;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getUserReview() {
        return userReview;
    }

    public void setUserReview(String userReview) {
        this.userReview = userReview;
    }

    public double getXaxis() {
        return xaxis;
    }

    public void setXaxis(double xaxis) {
        this.xaxis = xaxis;
    }

    public double getYaxis() {
        return yaxis;
    }

    public void setYaxis(double yaxis) {
        this.yaxis = yaxis;
    }
}
