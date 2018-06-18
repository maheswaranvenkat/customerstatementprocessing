package com.rabo.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="records")
public class Records {

    private List<Record> records;

    public List<Record> getRecords() {
        return records;
    }

    @XmlElement(name="record")
    public Records setRecords(List<Record> records) {
        this.records = records;
        return this;
    }
}
