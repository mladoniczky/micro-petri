package com.mladoniczky.micropetri.petri4j.arc.input;

import com.mladoniczky.micropetri.petri4j.arc.ArcType;
import com.mladoniczky.micropetri.petri4j.place.Place;
import com.mladoniczky.micropetri.petri4j.transition.Transition;
import lombok.Data;

@Data
public class InhibitorArc extends InputArc {

    private Long weight;

    public InhibitorArc() {
        weight = 1L;
        type = ArcType.INHIBITOR;
    }

    public InhibitorArc(String id, Place source, Transition target, Long weight) {
        super(id, ArcType.INHIBITOR, source, target);
        this.weight = weight;
    }

    @Override
    public boolean test() {
        return this.source.getResources() < weight;
    }

    @Override
    public void moveResources() {
        // nothing
    }
}
