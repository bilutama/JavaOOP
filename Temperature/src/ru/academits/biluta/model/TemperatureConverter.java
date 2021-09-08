package ru.academits.biluta.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.DoubleFunction;

public class TemperatureConverter {
    private static final double ABSOLUTE_ZERO_CELSIUS = -273.15;
    private static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;

    private static final ArrayList<String> scales;
    private static final HashMap<String, DoubleFunction<Double>> converters;

    static {
        scales = new ArrayList<>();
        converters = new HashMap<>();

        // Add scales
        scales.add("Celsius");
        scales.add("Kelvin");
        scales.add("Fahrenheit");
        scales.add("Rankine");

        // Add converting functions

        converters.put("KelvinToCelsius", t -> t - ABSOLUTE_ZERO_CELSIUS);
        converters.put("CelsiusToKelvin", t -> t + ABSOLUTE_ZERO_CELSIUS);

        // Add Fahrenheit scale
        converters.put("FahrenheitToCelsius", t -> (t - 32) / 1.8);
        converters.put("FahrenheitToKelvin", t -> (t - 32) / 1.8 - ABSOLUTE_ZERO_CELSIUS);

        converters.put("KelvinToFahrenheit", t -> 1.8 * (t + ABSOLUTE_ZERO_CELSIUS) + 32);
        converters.put("CelsiusToFahrenheit", t -> 1.8 * t + 32);

        // Adding Rankine scale
        converters.put("KelvinToRankine", t -> 1.8 * t);
        converters.put("CelsiusToRankine", t -> (t - ABSOLUTE_ZERO_CELSIUS) * 1.8);
        converters.put("FahrenheitToRankine", t -> t - ABSOLUTE_ZERO_FAHRENHEIT);

        converters.put("RankineToKelvin", t -> t / 1.8);
        converters.put("RankineToCelsius", t -> (t - 32 + ABSOLUTE_ZERO_FAHRENHEIT) / 1.8);
        converters.put("RankineToFahrenheit", t -> t + ABSOLUTE_ZERO_FAHRENHEIT);

        try {
            validateModel();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void validateModel() throws Exception {
        double randomTemperatureForModelValidation = 156;
        double epsilon = 1e-5;

        for (int i = 0; i < scales.size(); ++i) {
            for (int j = 0; j < scales.size(); ++j) {
                if (j == i) {
                    continue;
                }

                String direction = scales.get(i) + "To" + scales.get(j);
                String reversed = scales.get(j) + "To" + scales.get(i);

                double tConverted = converters.get(reversed).apply(converters.get(direction).apply(randomTemperatureForModelValidation));

                if (Math.abs(tConverted - randomTemperatureForModelValidation) > epsilon) {
                    throw new Exception(String.format("Inconsistent conversion formulas between %s and %s", scales.get(i), scales.get(j)));
                }
            }
        }
    }

    public static double convert(double value, String direction) {
        DoubleFunction<Double> concreteConverter = converters.get(direction);

        if (concreteConverter == null) {
            throw new IllegalArgumentException(String.format("Invalid argument \"%s\" - no such function", direction));
        }

        return concreteConverter.apply(value);
    }
}