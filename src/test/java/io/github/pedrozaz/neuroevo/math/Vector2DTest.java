package io.github.pedrozaz.neuroevo.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Vector2DTest {

    @Test
    void testAdd() {
        Vector2D v1 = new Vector2D(1, 2);
        Vector2D v2 = new Vector2D(3, 4);
        v1.add(v2);
        assertEquals(4.0, v1.x);
        assertEquals(6.0, v1.y);
    }

    @Test
    void testLimit() {
        Vector2D v = new Vector2D(10, 0);
        v.limit(5);
        assertEquals(5.0, v.x);
        assertEquals(0.0, v.y);
    }

    @Test
    void testNormalize() {
        Vector2D v = new Vector2D(10, 0);
        v.normalize();
        assertEquals(1.0, v.x);
        assertEquals(0.0, v.y);
    }
}
