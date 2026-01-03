package io.github.pedrozaz.neuroevo;

import io.github.pedrozaz.neuroevo.math.Vector2D;
import io.github.pedrozaz.neuroevo.simulation.Agent;
import io.github.pedrozaz.neuroevo.simulation.Obstacle;
import io.github.pedrozaz.neuroevo.simulation.Population;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Population population;
    private Vector2D target;
    private List<Obstacle> obstacles;

    private int frameCount = 0;
    private static final int LIFETIME = 400;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        target = new Vector2D(WIDTH / 2.0, 50);
        population = new Population(100, target);
        obstacles = new ArrayList<>();

        obstacles.add(new Obstacle(200, 300, 400, 20));

        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

        canvas.setOnMouseClicked(e -> {
            target.x = e.getX();
            target.y = e.getY();
        });

        primaryStage.setTitle("Neuroevolution Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render(gc);
            }
        }.start();
    }

    private void update() {
        population.update(obstacles);
        frameCount++;

        if (frameCount >= LIFETIME) {
            population.evolve();
            frameCount = 0;
        }
    }

    private void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        gc.setFill(Color.RED);
        gc.fillOval(target.x, target.y, 20, 20);

        for (Obstacle obs : obstacles) {
            obs.render(gc);
        }

        population.render(gc);

        gc.setFill(Color.WHITE);
        gc.fillText("Remaining frames: " + (LIFETIME - frameCount), 10, 20);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
