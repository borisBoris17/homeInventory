package com.homeInventory.homeInventory.BusinessObjects;

public class Home {
    private String name;
    private String occupants;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupants() {
        return occupants;
    }

    public void setOccupants(String occupants) {
        this.occupants = occupants;
    }

    public boolean validHome() {
        return getName() != null && !getName().isEmpty() && getOccupants() != null && !getOccupants().isEmpty();
    }

    public String toString() {
        return "This homes name is " + getName() + " and the the occupants are " + getOccupants() + ".";
    }
}