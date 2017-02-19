package com.epam.tat.menu.bean;

import java.io.Serializable;

/**
 * Provides information about menu item.
 *
 * @author Uladzislau Seuruk.
 */
public class MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * String with item's id.
     */
    private String id = null;
    /**
     * String with item's description.
     */
    private String description = null;
    /**
     * String with name of item.
     */
    private String name = null;
    /**
     * String with uri to item's photo.
     */
    private String photoUri = null;
    /**
     * String with portion of item.
     */
    private String portion = null;
    /**
     * String with item's price.
     */
    private String price = null;

    public MenuItem() {}

    /**
     * Getter.
     */
    public String getId() {
        return id;
    }

    /**
     * Getter.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter.
     */
    public String getPhotoUri() {
        return photoUri;
    }

    /**
     * Getter.
     */
    public String getPortion() {
        return portion;
    }

    /**
     * Getter.
     */
    public String getPrice() {
        return price;
    }

    /**
     * Setter.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Setter.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter.
     */
    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    /**
     * Setter.
     */
    public void setPortion(String portion) {
        this.portion = portion;
    }

    /**
     * Setter.
     */
    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (this.getClass() != object.getClass()) {
            return false;
        }

        MenuItem item = (MenuItem) object;

        if(!areObjectsEquals(this.id, item.id)) {
            return false;
        }
        if(!areObjectsEquals(this.description, item.description)) {
            return false;
        }
        if(!areObjectsEquals(this.name, item.name)) {
            return false;
        }
        if(!areObjectsEquals(this.photoUri, item.photoUri)) {
            return false;
        }
        if(!areObjectsEquals(this.portion, item.portion)) {
            return false;
        }
        return areObjectsEquals(this.price, item.price);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 13 + (id == null ? 0 : id.hashCode());
        hash = hash * 31 + (description == null ? 0 : description.hashCode());
        hash = hash * 17 + (name == null ? 0 : name.hashCode());
        hash = hash * 37 + (photoUri == null ? 0 : photoUri.hashCode());
        hash = hash * 73 + (portion == null ? 0 : portion.hashCode());
        hash = hash * 59 + (price == null ? 0 : price.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName())
                .append("@id: ")
                .append(id)
                .append(", description: ")
                .append(description)
                .append(", name: ")
                .append(name)
                .append(" photoUri: ")
                .append(photoUri)
                .append(", portion: ")
                .append(portion)
                .append(", price: ")
                .append(price);
        return builder.toString();
    }

    private boolean areObjectsEquals(Object first, Object second) {
        if (first == null) {
            return second == null;
        }
        return first.equals(second);
    }
}