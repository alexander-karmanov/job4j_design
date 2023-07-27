package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement(name = "smartphone")
@XmlAccessorType(XmlAccessType.FIELD)
public class Smartphone {
    @XmlAttribute
    private boolean working;

    @XmlAttribute
    private int model;

    private OperatingSystem operatingSystem;

    private String[] properties;
    public Smartphone() { }

    public Smartphone(boolean working, int model, OperatingSystem operatingSystem, String... properties) {
        this.working = working;
        this.model = model;
        this.operatingSystem = operatingSystem;
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Smartphone{"
                + "working=" + working
                + ", model=" + model
                + ", operatingSystem=" + operatingSystem
                + ", properties=" + Arrays.toString(properties)
                + '}';
    }
}
