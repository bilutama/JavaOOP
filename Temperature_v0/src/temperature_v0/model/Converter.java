package temperature_v0.model;

import java.util.ArrayList;

public interface Converter {
    ArrayList<String> getUnits();

    double getConvertedValue(double value, String sourceToResultUnits);
}
