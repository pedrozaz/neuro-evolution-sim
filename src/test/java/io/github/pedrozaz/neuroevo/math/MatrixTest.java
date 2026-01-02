package io.github.pedrozaz.neuroevo.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void testMultiply () {
        Matrix a = new Matrix(2, 3);
        a.data[0] = new double[] {1, 2, 3};
        a.data[1] = new double[] {4, 5, 6};

        Matrix b = new Matrix(3, 2);
        b.data[0] = new double[] {7, 8};
        b.data[1] = new double[] {9, 1};
        b.data[2] = new double[] {2, 3};

        Matrix res = Matrix.mult(a, b);

        assertEquals(2, res.rows);
        assertEquals(2, res.cols);
        assertEquals(31.0, res.data[0][0]);
        assertEquals(19.0, res.data[0][1]);
        assertEquals(85.0, res.data[1][0]);
        assertEquals(55.0, res.data[1][1]);
    }

    @Test
    void testFromArray() {
        double[] input = {1.0, 0.5, -1.0};
        Matrix m = Matrix.fromArray(input);
        assertEquals(3, m.rows);
        assertEquals(1, m.cols);
        assertEquals(0.5, m.data[1][0]);
    }
}
