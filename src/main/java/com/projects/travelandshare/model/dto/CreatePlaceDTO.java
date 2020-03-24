package com.projects.travelandshare.model.dto;

import com.projects.travelandshare.util.Counties;
import com.projects.travelandshare.util.Types;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Type;


public class CreatePlaceDTO implements Serializable {
    private String name;
    private Counties county;
    private float grade;
    private Types type;
    private MultipartFile pictureFile;
    private String description;
    private double xaxis;
    private double yaxis;

    public CreatePlaceDTO() {
    }
    public CreatePlaceDTO(String newName, double newXaxis, double newYaxis, String newPictureName) {
    }
    public CreatePlaceDTO(String newName, Counties newCounty, Types newType, double newXaxis, double newYaxis, MultipartFile newPictureFile){
        this.name=newName;
        this.county=newCounty;
        this.type=newType;
        this.xaxis=newXaxis;
        this.yaxis=newYaxis;
        this.pictureFile=newPictureFile;
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

    public MultipartFile getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(MultipartFile pictureFile) {
        this.pictureFile = pictureFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
