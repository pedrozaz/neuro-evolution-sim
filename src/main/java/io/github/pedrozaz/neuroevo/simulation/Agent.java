package io.github.pedrozaz.neuroevo.simulation;

import io.github.pedrozaz.neuroevo.math.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Agent {
    private Vector2D position;
    private Vector2D velocity;
    private Vector2D acceleration;

    private static final double MAX_SPEED = 5.0;
    private static final double MAX_FORCE = 0.2;

    public Agent(double x, double y) {
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
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
        if (position.x > 800) position.x = 0;
        if (position.x < 0) position.x = 800;
        if (position.y > 600) position.y = 0;
        if (position.y < 0) position.y = 600;
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.WHITESMOKE);
        gc.fillOval(position.x, position.y, 10, 10);
    }
}
