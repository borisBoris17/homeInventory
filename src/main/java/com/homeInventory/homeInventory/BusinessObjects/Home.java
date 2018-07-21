package com.homeInventory.homeInventory.BusinessObjects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="home")
public class Home {
    @Id
    private ObjectId _id;

    private String name;
    private String occupants;

    public Home() {
        this.name = "";
        this.occupants = "";
    }

    public Home(String name, String occupants) {
        this.name = name;
        this.occupants = occupants;
    }

    // ObjectId needs to be converted to string
    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }

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