package ru.job4j.ood.ocp.example1;

public class VolumeCalculation {

    private static double calculateVolume(Cube cube) {
        return cube.getBreadth() * cube.getHeight() * cube.getLength();
    }

    public static void main(String[] args) {
        Cube cube = new Cube(10, 10, 10);
        System.out.println(calculateVolume(cube));
    }
}
