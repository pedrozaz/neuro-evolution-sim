package io.github.pedrozaz.neuroevo;

import io.github.pedrozaz.neuroevo.math.Vector2D;
import io.github.pedrozaz.neuroevo.simulation.Agent;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Agent agent;
    private Vector2D target;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        agent = new Agent(WIDTH / 2.0, HEIGHT / 2.0);
        target = new Vector2D(100, 100);

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
        agent.think(target);
        agent.update();
    }

    private void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        gc.setFill(Color.RED);
        gc.fillOval(target.x, target.y, 20, 20);

        agent.render(gc);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
