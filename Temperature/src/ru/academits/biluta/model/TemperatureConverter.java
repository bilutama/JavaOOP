package ru.academits.biluta.model;

import java.util.HashMap;
import java.util.function.DoubleFunction;

public class TemperatureConverter {
    private static final double ABSOLUTE_ZERO_CELSIUS = -273.15;
    private static final double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;

    public static void main(String[] args) {
        HashMap<String, DoubleFunction<Double>> converters = new HashMap<>();
        // Converting to Celsius
        converters.put("FahrenheitToCelsius", t -> (t - 32) / 1.8);
        converters.put("KelvinToCelsius", t -> t - ABSOLUTE_ZERO_CELSIUS);

        // Converting to Kelvins
        converters.put("FahrenheitToKelvin", t -> (t - 32) / 1.8 - ABSOLUTE_ZERO_CELSIUS);
        converters.put("CelsiusToKelvin", t -> t + ABSOLUTE_ZERO_CELSIUS);

        // Converting to Fahrenheits
        converters.put("KelvinsToFahrenheit", t -> 1.8 * t + 32 + ABSOLUTE_ZERO_CELSIUS);
        converters.put("CelsiusToFahrenheit", t -> 1.8 * t + 32);

        // Adding Rankin degrees
        // Converting to Rankin scale
        converters.put("KelvinToRankin", t -> 1.8 * t);
        converters.put("CelsiusToRankin", t -> (t - ABSOLUTE_ZERO_CELSIUS) * 1.8);
        converters.put("FahrenheitToRankin", t -> t - ABSOLUTE_ZERO_FAHRENHEIT);

        // Converting from Rankin scale
        converters.put("RankinToKelvin", t -> t / 1.8);
        converters.put("RankinToCelsius", t -> (t - 32 + ABSOLUTE_ZERO_FAHRENHEIT) / 1.8);
        converters.put("RankinToFahrenheit", t -> t + ABSOLUTE_ZERO_FAHRENHEIT);
        // end for Rankin scale

        //double t = 20;
        double t = 156;
        double tConverted = converters.get("RankinToCelsius").apply(t);

        System.out.println(Math.round(tConverted));
    }
}
