
package com.mladoniczky.micropetri.net.web.resource;

import com.fasterxml.jackson.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The root schema
 * <p>
 * The root schema comprises the entire JSON document.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "transitions",
    "places",
    "arcs"
})
public class NetResource {

    /**
     * The net's id
     * <p>
     * Database generated id of the Petri net.
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Database generated id of the Petri net.")
    private String id = "";
    /**
     * The net's name
     * <p>
     * The name of the net to better identify the net.
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("The name of the net to better identify the net.")
    @NotNull
    private String name = "";
    /**
     * Transitions of the net
     * <p>
     * A set of transitions of the net.
     * (Required)
     * 
     */
    @JsonProperty("transitions")
    @JsonPropertyDescription("A set of transitions of the net.")
    @Valid
    @NotNull
    private List<Transition> transitions = new ArrayList<Transition>();
    /**
     * The places of the net
     * <p>
     * A set of places of the Petri net.
     * (Required)
     * 
     */
    @JsonProperty("places")
    @JsonPropertyDescription("A set of places of the Petri net.")
    @Valid
    @NotNull
    private List<Place> places = new ArrayList<Place>();
    /**
     * The arcs of the net
     * <p>
     * A set of arcs of the net.
     * (Required)
     * 
     */
    @JsonProperty("arcs")
    @JsonPropertyDescription("A set of arcs of the net.")
    @Valid
    @NotNull
    private List<Arc> arcs = new ArrayList<Arc>();
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public NetResource() {
    }

    /**
     * 
     * @param places
     * @param name
     * @param id
     * @param transitions
     * @param arcs
     */
    public NetResource(String id, String name, List<Transition> transitions, List<Place> places, List<Arc> arcs) {
        super();
        this.id = id;
        this.name = name;
        this.transitions = transitions;
        this.places = places;
        this.arcs = arcs;
    }

    /**
     * The net's id
     * <p>
     * Database generated id of the Petri net.
     * 
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * The net's id
     * <p>
     * Database generated id of the Petri net.
     * 
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The net's name
     * <p>
     * The name of the net to better identify the net.
     * (Required)
     * 
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * The net's name
     * <p>
     * The name of the net to better identify the net.
     * (Required)
     * 
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Transitions of the net
     * <p>
     * A set of transitions of the net.
     * (Required)
     * 
     */
    @JsonProperty("transitions")
    public List<Transition> getTransitions() {
        return transitions;
    }

    /**
     * Transitions of the net
     * <p>
     * A set of transitions of the net.
     * (Required)
     * 
     */
    @JsonProperty("transitions")
    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    /**
     * The places of the net
     * <p>
     * A set of places of the Petri net.
     * (Required)
     * 
     */
    @JsonProperty("places")
    public List<Place> getPlaces() {
        return places;
    }

    /**
     * The places of the net
     * <p>
     * A set of places of the Petri net.
     * (Required)
     * 
     */
    @JsonProperty("places")
    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    /**
     * The arcs of the net
     * <p>
     * A set of arcs of the net.
     * (Required)
     * 
     */
    @JsonProperty("arcs")
    public List<Arc> getArcs() {
        return arcs;
    }

    /**
     * The arcs of the net
     * <p>
     * A set of arcs of the net.
     * (Required)
     * 
     */
    @JsonProperty("arcs")
    public void setArcs(List<Arc> arcs) {
        this.arcs = arcs;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(NetResource.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("transitions");
        sb.append('=');
        sb.append(((this.transitions == null)?"<null>":this.transitions));
        sb.append(',');
        sb.append("places");
        sb.append('=');
        sb.append(((this.places == null)?"<null>":this.places));
        sb.append(',');
        sb.append("arcs");
        sb.append('=');
        sb.append(((this.arcs == null)?"<null>":this.arcs));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.places == null)? 0 :this.places.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.transitions == null)? 0 :this.transitions.hashCode()));
        result = ((result* 31)+((this.arcs == null)? 0 :this.arcs.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NetResource) == false) {
            return false;
        }
        NetResource rhs = ((NetResource) other);
        return (((((((this.places == rhs.places)||((this.places!= null)&&this.places.equals(rhs.places)))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.transitions == rhs.transitions)||((this.transitions!= null)&&this.transitions.equals(rhs.transitions))))&&((this.arcs == rhs.arcs)||((this.arcs!= null)&&this.arcs.equals(rhs.arcs))));
    }

}
