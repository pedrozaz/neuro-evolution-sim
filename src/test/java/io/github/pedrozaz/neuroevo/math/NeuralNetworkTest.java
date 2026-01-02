package io.github.pedrozaz.neuroevo.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NeuralNetworkTest {

    @Test
    void testPredictOutputShape() {
        NeuralNetwork brain = new NeuralNetwork(2, 4, 2);

        double[] input = {1.0, 0.5};
        double[] output = brain.predict(input);

        assertEquals(2, output.length);

        for (double val : output) {
            assertTrue(val >= 0.0 & val <= 1.0, "Output value should be between 0 and 1");
        }

        System.out.println("Input: [1.0, 0.5]");
        System.out.println("Output: [" + output[0] + ", " + output[1] + "]");
    }
}
