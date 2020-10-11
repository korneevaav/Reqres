package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

@Data
public class Pet {
    @Expose
    long id;
    @Expose
    String name;
    @Expose
    List<String> photoUrls;
    @Expose
    Category category;
    @Expose
    Tag tag;
    @Expose
    String status;

    public Pet(String name, Category category, String status) {
        this.name = name;
        this.category = category;
        this.status = status;
    }

    public Pet(long id, String name, List<String> photoUrls, Category category, Tag tag, String status) {
        this.id = id;
        this.name = name;
        this.photoUrls = photoUrls;
        this.category = category;
        this.tag = tag;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
