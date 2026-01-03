package io.github.pedrozaz.neuroevo.simulation;

import io.github.pedrozaz.neuroevo.math.NeuralNetwork;
import io.github.pedrozaz.neuroevo.math.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Agent {
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;

    private NeuralNetwork brain;

    private static final double MAX_SPEED = 5.0;
    private static final double MAX_FORCE = 0.2;
    private static final int SIZE = 10;

    public Agent(double x, double y) {
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();

        this.brain = new NeuralNetwork(4, 6, 2);
    }

    public void think(Vector2D target) {
        double[] inputs = new double[4];
        inputs[0] = this.position.x / 800.0;
        inputs[1] = this.position.y / 600.0;
        inputs[2] = target.x / 800.0;
        inputs[3] = target.y / 600.0;

        double[] outputs = this.brain.predict(inputs);

        double forceX = map(outputs[0], 0, 1, -MAX_FORCE, MAX_FORCE);
        double forceY = map(outputs[1], 0, 1, -MAX_FORCE, MAX_FORCE);

        Vector2D desiredForce = new Vector2D(forceX, forceY);
        applyForce(desiredForce);
    }

    public double map(double value, double start1, double start2, double stop1, double stop2) {
        return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
    }

    public void applyForce(Vector2D f) {
        this.acceleration.add(f);
    }

    public void update() {
        this.velocity.add(this.acceleration);
        this.velocity.limit(MAX_SPEED);
        this.position.add(this.velocity);
        this.acceleration.mult(0);
        handleBoundaries();
    }

    private void handleBoundaries() {
        double width = 800;
        double height = 600;

        if (position.x > width - SIZE) {
            position.x = width - SIZE;
            velocity.x *= -1;
        } else if (position.x < 0) {
            position.x = 0;
            velocity.x *= -1;
        }

        if (position.y > height - SIZE) {
            position.y = height - SIZE;
            velocity.y *= -1;
        } else if (position.y < 0) {
            position.y = 0;
            velocity.y *= -1;
        }
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITESMOKE);
        gc.fillOval(position.x, position.y, 10, 10);
    }
}
