package com.mladoniczky.micropetri.net.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class Net extends com.mladoniczky.micropetri.petri4j.Net {

    @Id
    private ObjectId id;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime lastModified;

    public Net() {
        super();
    }

    public Net(String name) {
        super(name);
    }

    public Net(String id, String name) {
        super(name);
        this.id = new ObjectId(id);
    }

    public String getStringId() {
        return id.toHexString();
    }

}
