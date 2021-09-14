package ru.academits.biluta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.DoubleFunction;

public class TemperatureConverter implements Converter {
    private static final double UNDEFINED = -999.99;

    private static final double ABSOLUTE_ZERO_CELSIUS = -273.15;
    private static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;

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
        units.add("Rankine");

        // Add Celsius and Kelvin
        converters.put("KelvinToCelsius", t -> t + ABSOLUTE_ZERO_CELSIUS);
        converters.put("CelsiusToKelvin", t -> t - ABSOLUTE_ZERO_CELSIUS);

        // Add Fahrenheits
        converters.put("FahrenheitToCelsius", t -> (t - 32) / 1.8);
        converters.put("FahrenheitToKelvin", t -> (t - 32) / 1.8 - ABSOLUTE_ZERO_CELSIUS);

        converters.put("KelvinToFahrenheit", t -> 1.8 * (t + ABSOLUTE_ZERO_CELSIUS) + 32);
        converters.put("CelsiusToFahrenheit", t -> 1.8 * t + 32);

        // Add Rankine
        converters.put("KelvinToRankine", t -> 1.8 * t);
        converters.put("CelsiusToRankine", t -> (t - ABSOLUTE_ZERO_CELSIUS) * 1.8);
        converters.put("FahrenheitToRankine", t -> t - ABSOLUTE_ZERO_FAHRENHEIT);

        converters.put("RankineToKelvin", t -> t / 1.8);
        converters.put("RankineToCelsius", t -> (t - 32 + ABSOLUTE_ZERO_FAHRENHEIT) / 1.8);
        converters.put("RankineToFahrenheit", t -> t + ABSOLUTE_ZERO_FAHRENHEIT);
    }

    public ArrayList<String> getUnits() {
        return units;
    }

    public double getConvertedValue(double value, String conversionUnits) {
        convertValue(value, conversionUnits);
        return temperatureResult;
    }

    private void convertValue(double value, String conversionUnits) {
        DoubleFunction<Double> converter = converters.get(conversionUnits);

        if (converter == null) {
            temperatureResult = UNDEFINED;
            return;
            //throw new IllegalArgumentException(String.format("Invalid argument \"%s\" - no such function", conversionUnits));
        }

        temperatureResult = converter.apply(value);
    }
}