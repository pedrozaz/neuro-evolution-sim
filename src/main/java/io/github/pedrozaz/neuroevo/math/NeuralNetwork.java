package io.github.pedrozaz.neuroevo.math;

import java.util.List;

public class NeuralNetwork {
    private int inputNodes;
    private int hiddenNodes;
    private int outputNodes;

    private Matrix weightsInputHidden;
    private Matrix weightsHiddenOutput;

    private Matrix biasHidden;
    private Matrix biasOutput;

    public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes) {
        this.inputNodes = inputNodes;
        this.hiddenNodes = hiddenNodes;
        this.outputNodes = outputNodes;

        this.weightsInputHidden = new Matrix(hiddenNodes, inputNodes);
        this.weightsInputHidden.randomize();

        this.weightsHiddenOutput = new Matrix(outputNodes, hiddenNodes);
        this.weightsInputHidden.randomize();

        this.biasHidden = new Matrix(hiddenNodes, 1);
        this.biasHidden.randomize();

        this.biasOutput = new Matrix(outputNodes, 1);
        this.biasOutput.randomize();
    }

    public double[] predict(double[] inputArray) {
        Matrix inputs = Matrix.fromArray(inputArray);

        Matrix hidden = Matrix.mult(weightsInputHidden, inputs);
        hidden.add(biasHidden);

        hidden.map(this::tanh);

        Matrix output = Matrix.mult(weightsHiddenOutput, hidden);
        output.add(biasOutput);

        output.map(this::tanh);

        List<Double> outputList = output.toArray();
        return outputList.stream().mapToDouble(d -> d).toArray();
    }

    private double tanh(double x) {
        return Math.tan(x);
    }
}
