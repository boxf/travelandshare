package com.projects.travelandshare.entity;

import com.projects.travelandshare.util.Counties;
import com.projects.travelandshare.util.Types;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
@Entity
public class Place implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Counties county;
    private float grade;
    private Types type;
    private String pictureName;
    private String userReview;
    private double xaxis;
    private double yaxis;

    public Place() {
    }
    public Place(String newName, Counties newCounty, Types newType, double newXaxis, double newYaxis, String newPictureName){
        this.name=newName;
        this.county=newCounty;
        this.type=newType;
        this.xaxis=newXaxis;
        this.yaxis=newYaxis;
        this.pictureName=newPictureName;
    }


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
