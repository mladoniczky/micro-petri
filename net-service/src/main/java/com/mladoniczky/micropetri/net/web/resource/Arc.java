
package com.mladoniczky.micropetri.net.web.resource;

import com.fasterxml.jackson.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


/**
 * An arc of the net
 * <p>
 * Definition of an arc of the net.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "type",
    "source",
    "target",
    "weight"
})
public class Arc {

    /**
     * The id of the arc
     * <p>
     * User defined id of the arc of the net.
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("User defined id of the arc of the net.")
    @NotNull
    private String id = "";
    /**
     * The type of the arc
     * <p>
     * One of the available types of the arc.
     * (Required)
     * 
     */
    @JsonProperty("type")
    @JsonPropertyDescription("One of the available types of the arc.")
    @NotNull
    private Arc.Type type;
    /**
     * The id of a source node of the net
     * <p>
     * The id of a source node. Source node can be a transition or a place.
     * (Required)
     * 
     */
    @JsonProperty("source")
    @JsonPropertyDescription("The id of a source node. Source node can be a transition or a place.")
    @NotNull
    private String source = "";
    /**
     * The id of a target node of the net
     * <p>
     * The id of a target node. Target node can be a transition or a place.
     * (Required)
     * 
     */
    @JsonProperty("target")
    @JsonPropertyDescription("The id of a target node. Target node can be a transition or a place.")
    @NotNull
    private String target = "";
    /**
     * The weight of the arc
     * <p>
     * 
     * 
     */
    @JsonProperty("weight")
    @JsonPropertyDescription("")
    @DecimalMin("0")
    private Integer weight = 1;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Arc() {
    }

    /**
     * 
     * @param weight
     * @param id
     * @param source
     * @param type
     * @param target
     */
    public Arc(String id, Type type, String source, String target, Integer weight) {
        super();
        this.id = id;
        this.type = type;
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    /**
     * The id of the arc
     * <p>
     * User defined id of the arc of the net.
     * (Required)
     * 
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * The id of the arc
     * <p>
     * User defined id of the arc of the net.
     * (Required)
     * 
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * The type of the arc
     * <p>
     * One of the available types of the arc.
     * (Required)
     * 
     */
    @JsonProperty("type")
    public Type getType() {
        return type;
    }

    /**
     * The type of the arc
     * <p>
     * One of the available types of the arc.
     * (Required)
     * 
     */
    @JsonProperty("type")
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * The id of a source node of the net
     * <p>
     * The id of a source node. Source node can be a transition or a place.
     * (Required)
     * 
     */
    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    /**
     * The id of a source node of the net
     * <p>
     * The id of a source node. Source node can be a transition or a place.
     * (Required)
     * 
     */
    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * The id of a target node of the net
     * <p>
     * The id of a target node. Target node can be a transition or a place.
     * (Required)
     * 
     */
    @JsonProperty("target")
    public String getTarget() {
        return target;
    }

    /**
     * The id of a target node of the net
     * <p>
     * The id of a target node. Target node can be a transition or a place.
     * (Required)
     * 
     */
    @JsonProperty("target")
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * The weight of the arc
     * <p>
     * 
     * 
     */
    @JsonProperty("weight")
    public Integer getWeight() {
        return weight;
    }

    /**
     * The weight of the arc
     * <p>
     * 
     * 
     */
    @JsonProperty("weight")
    public void setWeight(Integer weight) {
        this.weight = weight;
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
        sb.append(Arc.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("source");
        sb.append('=');
        sb.append(((this.source == null)?"<null>":this.source));
        sb.append(',');
        sb.append("target");
        sb.append('=');
        sb.append(((this.target == null)?"<null>":this.target));
        sb.append(',');
        sb.append("weight");
        sb.append('=');
        sb.append(((this.weight == null)?"<null>":this.weight));
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
        result = ((result* 31)+((this.weight == null)? 0 :this.weight.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.source == null)? 0 :this.source.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.target == null)? 0 :this.target.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Arc) == false) {
            return false;
        }
        Arc rhs = ((Arc) other);
        return (((((((this.weight == rhs.weight)||((this.weight!= null)&&this.weight.equals(rhs.weight)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.source == rhs.source)||((this.source!= null)&&this.source.equals(rhs.source))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.target == rhs.target)||((this.target!= null)&&this.target.equals(rhs.target))));
    }


    /**
     * The type of the arc
     * <p>
     * One of the available types of the arc.
     * 
     */
    public enum Type {

        REGULAR("regular"),
        INHIBITOR("inhibitor"),
        RESET("reset"),
        READ("read");
        private final String value;
        private final static Map<String, Type> CONSTANTS = new HashMap<String, Type>();

        static {
            for (Type c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static Type fromValue(String value) {
            Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
