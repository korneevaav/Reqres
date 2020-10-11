package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Salary {
    @Expose
    double from;
    @Expose
    double to;
    @Expose
    String currency;
    @Expose
    double averageUSD;

}
