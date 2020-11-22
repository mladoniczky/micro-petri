package com.mladoniczky.micropetri.petri4j.arc.input;

import com.mladoniczky.micropetri.petri4j.arc.ArcType;
import com.mladoniczky.micropetri.petri4j.place.Place;
import com.mladoniczky.micropetri.petri4j.transition.Transition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegularInputArc extends InputArc {

    private Long weight;

    public RegularInputArc() {
        weight = 1L;
        type = ArcType.REGULAR;
    }

    public RegularInputArc(String id, Place source, Transition target, Long weight) {
        super(id, ArcType.REGULAR, source, target);
        this.weight = weight;
    }

    @Override
    public boolean test() {
        return this.source.getResources() >= weight;
    }

    @Override
    public void moveResources() {
        this.source.decrease(weight);
    }
}
