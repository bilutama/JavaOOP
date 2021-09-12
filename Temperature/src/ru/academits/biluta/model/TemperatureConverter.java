package ru.academits.biluta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.DoubleFunction;

public class TemperatureConverter implements Converter {
    private static final double ABSOLUTE_ZERO_CELSIUS = -273.15;
    //private static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;

    private static final ArrayList<String> units;
    private static final HashMap<String, DoubleFunction<Double>> converters;

    private double temperatureResult;

    static {
        units = new ArrayList<>();
        converters = new HashMap<>();

        // Add units
        units.add("Celsius");
        units.add("Kelvin");
        units.add("Fahrenheit");
        //units.add("Rankine");

        // Add Celsius and Kelvin
        converters.put("KelvinToCelsius", t -> t + ABSOLUTE_ZERO_CELSIUS);
        converters.put("CelsiusToKelvin", t -> t - ABSOLUTE_ZERO_CELSIUS);

        // Add Fahrenheits
        converters.put("FahrenheitToCelsius", t -> (t - 32) / 1.8);
        converters.put("FahrenheitToKelvin", t -> (t - 32) / 1.8 - ABSOLUTE_ZERO_CELSIUS);

        converters.put("KelvinToFahrenheit", t -> 1.8 * (t + ABSOLUTE_ZERO_CELSIUS) + 32);
        converters.put("CelsiusToFahrenheit", t -> 1.8 * t + 32);

        // Add Rankine
        /*
        converters.put("KelvinToRankine", t -> 1.8 * t);
        converters.put("CelsiusToRankine", t -> (t - ABSOLUTE_ZERO_CELSIUS) * 1.8);
        converters.put("FahrenheitToRankine", t -> t - ABSOLUTE_ZERO_FAHRENHEIT);

        converters.put("RankineToKelvin", t -> t / 1.8);
        converters.put("RankineToCelsius", t -> (t - 32 + ABSOLUTE_ZERO_FAHRENHEIT) / 1.8);
        converters.put("RankineToFahrenheit", t -> t + ABSOLUTE_ZERO_FAHRENHEIT);
*/
        try {
            checkModelConsistency();
        } catch (InvalidConversionFunctionsException ex1) {
            System.out.println(ex1.getMessage());
            try {
                throw ex1;
            } catch (InvalidConversionFunctionsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkModelConsistency() throws InvalidConversionFunctionsException {
        double randomTemperatureToCheckModelConsistency = 156;
        double epsilon = 1e-5;

        for (int i = 0; i < units.size(); ++i) {
            for (int j = 0; j < units.size(); ++j) {
                if (j == i) {
                    continue;
                }

                String direction = units.get(i) + "To" + units.get(j);
                String reversed = units.get(j) + "To" + units.get(i);

                double conversionResult = converters.get(reversed).apply(converters.get(direction).apply(randomTemperatureToCheckModelConsistency));

                if (Math.abs(conversionResult - randomTemperatureToCheckModelConsistency) > epsilon) {
                    throw new InvalidConversionFunctionsException(String.format("Inconsistent conversion formulas between %s and %s", units.get(i), units.get(j)));
                }
            }
        }
    }

    public ArrayList<String> getUnits() {
        return units;
    }

    public double getConvertedValue(double value, String direction) {
        convert(value, direction);
        return temperatureResult;
    }

    private void convert(double value, String direction) {
        DoubleFunction<Double> converter = converters.get(direction);

        if (converter == null) {
            throw new IllegalArgumentException(String.format("Invalid argument \"%s\" - no such function", direction));
        }

        temperatureResult = converter.apply(value);
    }
}