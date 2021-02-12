package com.mladoniczky.micropetri.net.model;

import com.mladoniczky.micropetri.petri4j.arc.input.InputArc;
import com.mladoniczky.micropetri.petri4j.arc.output.OutputArc;
import org.springframework.data.annotation.Transient;

import java.util.Map;

public class Transition  extends com.mladoniczky.micropetri.petri4j.transition.Transition {

    public Transition() {
        super();
    }

    public Transition(String id, String title) {
        super(id, title);
    }

    @Override
    @Transient
    public Map<String, InputArc> getInputArcs() {
        return super.getInputArcs();
    }

    @Override
    @Transient
    public Map<String, OutputArc> getOutputArc() {
        return super.getOutputArc();
    }
}
