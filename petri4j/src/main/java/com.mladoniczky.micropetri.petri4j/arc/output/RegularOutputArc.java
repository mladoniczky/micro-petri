package com.mladoniczky.micropetri.petri4j.arc.output;

import com.mladoniczky.micropetri.petri4j.arc.ArcType;
import com.mladoniczky.micropetri.petri4j.place.Place;
import com.mladoniczky.micropetri.petri4j.transition.Transition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegularOutputArc extends WeightedOutputArc {

    public RegularOutputArc() {
        super();
        type = ArcType.REGULAR;
    }

    public RegularOutputArc(Long weight) {
        super(weight);
        type = ArcType.REGULAR;
    }

    public RegularOutputArc(String id, Transition source, Place target, Long weight) {
        super(id, ArcType.REGULAR, source, target, weight);
    }

    @Override
    public void moveResources() {
        this.target.increase(weight);
    }
}
