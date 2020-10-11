package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Vacancies {
    @Expose
    ArrayList<Vacancy> items;

    public ArrayList<Vacancy> getItems() {
        return items;
    }
}
