package tests;

import com.google.gson.Gson;
import org.testng.annotations.Test;

public class JasonTest {

    @Test
    public void bestRates() {
        String bestRates =  "{amount: \"2,6205\", grow: -1, delta: -0.002000000000000224, banks: [], scale: 1}\n";
        Gson gson = new Gson();
        BestRate bestRatesObject = gson.fromJson(bestRates, BestRate.class);
        System.out.println(bestRatesObject);
    }
}
