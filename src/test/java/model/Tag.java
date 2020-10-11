package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Tag {
    @Expose
    int id;
    @Expose
    String name;

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
