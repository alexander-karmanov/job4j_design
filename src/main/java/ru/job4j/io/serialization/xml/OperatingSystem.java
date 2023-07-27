package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "operatingSystem")
public class OperatingSystem {
    @XmlAttribute
    private String type;

    public OperatingSystem() {
    }

    public OperatingSystem(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OperatingSystem{"
                + "type='" + type + '\''
                + '}';
    }
}
