package com.example.vehiclerentalsystembackend.model;

import org.bson.types.Binary;

import java.util.Objects;

public class VehicleImage {

    private Binary image;
    private String imageTitle;
    private String fileType;

    public VehicleImage() {
    }

    public VehicleImage(Binary image, String imageTitle, String fileType) {
        this.image = image;
        this.imageTitle = imageTitle;
        this.fileType = fileType;
    }

    public Binary getImage() {
        return image;
    }

    public void setImage(Binary image) {
        this.image = image;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleImage)) return false;
        VehicleImage that = (VehicleImage) o;
        return getImage().equals(that.getImage()) &&
                getImageTitle().equals(that.getImageTitle()) &&
                getFileType().equals(that.getFileType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImage(), getImageTitle(), getFileType());
    }

    @Override
    public String toString() {
        return "VehicleImage{" +
                "image=" + image +
                ", imageTitle='" + imageTitle + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
