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

    private Agent testAgent = new Agent(WIDTH / 2.0, HEIGHT / 2.0);

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

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
        Vector2D randomForce = Vector2D.random2D();
        randomForce.mult(0.5);

        testAgent.applyForce(randomForce);
        testAgent.update();
    }

    private void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        testAgent.render(gc);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
