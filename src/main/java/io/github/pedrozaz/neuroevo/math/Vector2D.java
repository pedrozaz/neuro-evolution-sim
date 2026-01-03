package io.github.pedrozaz.neuroevo.math;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this(0, 0);
    }

    public void add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void sub(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void mult(double n) {
        this.x *= n;
        this.y *= n;
    }

    public double mag() {
        return Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        double m = mag();
        if (m != 0) {
            mult(1 / m);
        }
    }

    public void limit(double max) {
        if (mag() > max) {
            normalize();
            mult(max);
        }
    }

    public Vector2D copy() {
        return new Vector2D(x, y);
    }

    public static Vector2D random2D() {
        double angle = Math.random() * Math.PI * 2;
        return new Vector2D(Math.cos(angle), Math.sin(angle));
    }

    @Override
    public String toString() {
        return "Vector2D{" + "x=" + x + ", y=" + y + '}';
    }
}
