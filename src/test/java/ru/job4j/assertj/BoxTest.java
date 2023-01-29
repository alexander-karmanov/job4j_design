package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 12);
        String name = box.whatsThis();
        assertThat(name).contains("b");
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 6);
        String name = box.whatsThis();
        assertThat(name).doesNotContain("b");
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void numberOfVerticesCube() {
        Box box = new Box(8, 2);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isGreaterThan(4);
        assertThat(vertex).isLessThan(9);
    }

    @Test
    void numberOfVerticesUnknown() {
        Box box = new Box(0, 0);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNegative();
        assertThat(vertex).isLessThan(1);
    }

    @Test
    void isExistSphere() {
        Box box = new Box(0, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue();
    }

    @Test
    void isNotExist() {
        Box box = new Box(0, 0);
        boolean isExist = box.isExist();
        assertThat(isExist).isFalse();
    }

    @Test
    void getAreaCube() {
        Box box = new Box(8, 5);
        double area = box.getArea();
        assertThat(area).isGreaterThan(149);
        assertThat(area).isLessThan(151);
    }

    @Test
    void getAreaTetrahedron() {
        Box box = new Box(4, 3);
        double area = box.getArea();
        assertThat(area).isPositive();
        assertThat(area).isCloseTo(15.58d, Percentage.withPercentage(1.0d));
    }
}
