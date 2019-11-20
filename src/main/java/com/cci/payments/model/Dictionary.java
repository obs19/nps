package com.cci.payments.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="discriminator", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Dictionary extends LongIdObject implements DeletableMarker {

    @NotBlank
    @Column(name="name")
    private String name;
    @NotBlank
    @Column(name="ext_id")
    private String extId;
    @Column(name="ordinal")
    private Long ordinal;
    @Column(name="description")
    private String description;
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;

    public Dictionary() {
    }

    @Override
    public void setDeleted(boolean value) {
        this.deleted = value;
    }

    @Override
    public boolean isDeleted() {
        return this.deleted;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "name='" + name + '\'' +
                ", extId='" + extId + '\'' +
                ", ordinal=" + ordinal +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public Long getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Long ordinal) {
        this.ordinal = ordinal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
