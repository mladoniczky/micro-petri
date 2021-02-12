package com.mladoniczky.micropetri.net.model;

import com.mladoniczky.micropetri.petri4j.arc.input.InputArc;
import com.mladoniczky.micropetri.petri4j.arc.output.OutputArc;
import org.springframework.data.annotation.Transient;

import java.util.Map;

public class Place extends com.mladoniczky.micropetri.petri4j.place.Place {

    public Place() {
        super();
    }

    public Place(String id, String title, Long resources) {
        super(id, title, resources);
    }

    @Override
    @Transient
    public Map<String, OutputArc> getInputArcs() {
        return super.getInputArcs();
    }

    @Override
    @Transient
    public Map<String, InputArc> getOutputArcs() {
        return super.getOutputArcs();
    }
}
