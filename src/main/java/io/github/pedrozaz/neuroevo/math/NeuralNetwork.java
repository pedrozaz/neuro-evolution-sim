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

    public NeuralNetwork copy() {
        NeuralNetwork clone = new NeuralNetwork(this.inputNodes, this.hiddenNodes, this.outputNodes);

        clone.weightsInputHidden = this.weightsInputHidden.copy();
        clone.weightsHiddenOutput = this.weightsHiddenOutput.copy();
        clone.biasHidden = this.biasHidden.copy();
        clone.biasOutput = this.biasOutput.copy();
        return clone;
    }

    public void mutate(double rate) {
        mutateMatrix(weightsInputHidden, rate);
        mutateMatrix(weightsHiddenOutput, rate);
        mutateMatrix(biasHidden, rate);
        mutateMatrix(biasOutput, rate);
    }

    private void mutateMatrix(Matrix m, double rate) {
        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                if (Math.random() < rate) {
                    double mutation = (Math.random() * 2 - 1) * 0.1;
                    m.data[i][j] += mutation;

                    if (m.data[i][j] > 1) m.data[i][j] = 1;
                    if (m.data[i][j] < -1) m.data[i][j] = -1;
                }
            }
        }
    }

    private double tanh(double x) {
        return Math.tan(x);
    }
}
