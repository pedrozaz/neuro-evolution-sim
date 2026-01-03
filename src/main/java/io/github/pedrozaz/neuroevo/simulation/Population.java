package io.github.pedrozaz.neuroevo.simulation;

import io.github.pedrozaz.neuroevo.math.Vector2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private List<Agent> agents;
    private int size;
    private Vector2D target;

    private int generation = 1;

    public Population(int size, Vector2D target) {
        this.size = size;
        this.target = target;
        this.agents = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            agents.add(new Agent(400, 550));
        }
    }

    public void update(List<Obstacle> obstacles) {
        for (Agent agent : agents) {
            agent.think(target);
            agent.update();
            agent.checkCollision(obstacles, target);
        }
    }

    public void render(GraphicsContext gc) {
        for (Agent agent : agents) {
            agent.render(gc);
        }
    }

    public void evolve() {
        sortAgentsByFitness();

        Agent champion = agents.get(0);
        System.out.println("Generation " + generation + " concluded. Lowest distance: " + champion.distanceTo(target));

        List<Agent> newAgents = new ArrayList<>();

        Agent bestClone = new Agent(400, 550);
        bestClone.setBrain(champion.getBrain().copy());
        bestClone.isChampion = true;
        newAgents.add(bestClone);

        for (int i = 1; i < size; i++) {
            Agent parent = selectParent();

            Agent child = new Agent(400, 550);
            child.setBrain(parent.getBrain().copy());

            child.getBrain().mutate(0.1);

            newAgents.add(child);
        }

        this.agents = newAgents;
        generation++;
    }

    private void sortAgentsByFitness() {
        agents.sort((a, b) -> Double.compare(a.distanceTo(target), b.distanceTo(target)));
    }

    private Agent selectParent() {
        int randomIndex1 = (int) (Math.random() * size);
        int randomIndex2 = (int) (Math.random() * size);

        Agent a1 = agents.get(randomIndex1);
        Agent a2 = agents.get(randomIndex2);

        if (a1.distanceTo(target) < a2.distanceTo(target)) {
            return a1;
        } else {
            return a2;
        }
    }

    private Agent getBestAgent() {
        Agent best  = agents.get(0);
        for (Agent a : agents) {
            if (a.distanceTo(target) < best.distanceTo(target)) {
                best = a;
            }
        }
        return best;
    }
}
