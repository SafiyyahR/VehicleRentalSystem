package com.example.vehiclerentalsystembackend.model;

import org.bson.types.Binary;

import java.util.Objects;

public class VehicleImage {

    private Binary image;
    private String imageTitle;

    public VehicleImage(Binary image, String imageTitle) {
        this.image = image;
        this.imageTitle = imageTitle;
    }

    public VehicleImage() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleImage)) return false;
        VehicleImage that = (VehicleImage) o;
        return getImage().equals(that.getImage()) &&
                getImageTitle().equals(that.getImageTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImage(), getImageTitle());
    }

    @Override
    public String toString() {
        return "VehicleImage{" +
                "image=" + image +
                ", imageTitle='" + imageTitle + '\'' +
                '}';
    }
}
