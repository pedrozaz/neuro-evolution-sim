# Neuroevolution Simulator

A Java-based simulation where autonomous agents evolve to reach a target using Neural Networks and Genetic Algorithms.

## Overview

This project demonstrates the principles of Neuroevolution. Each agent possesses a "brain" (Neural Network) initialized with random weights. Agents that get closer to the target are selected to reproduce, passing their genes (weights) to the next generation with slight mutations. Over time, the population learns to navigate the environment and avoid obstacles without explicit programming.

## Features

* **Custom Physics Engine:** Vector-based movement (position, velocity, acceleration) handling basic 2D mechanics.
* **Neural Network from Scratch:** Implementation of Matrix operations, Feedforward algorithm, and Tanh activation function without external ML libraries (no TensorFlow/PyTorch).
* **Genetic Algorithm:** Implements natural selection, mutation, crossover, and elitism to optimize agent behavior.
* **Real-time Visualization:** Built with JavaFX to visualize the training process and generation data.

## Tech Stack

* **Language:** Java 21
* **GUI:** JavaFX
* **Build Tool:** Maven
* **Testing:** JUnit 5

## How to Run

1.  Clone the repository.
2.  Build and run using Maven:

```bash
mvn clean javafx:run
