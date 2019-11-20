package com.cci.payments.dto;

public class DictDTO implements DtoObject{
    private Long id;
    private String name;
    private String extId;
    private Long ordinal;
    private String description;
    private boolean deleted;

    public DictDTO() {
    }

    @Override
    public String toString() {
        return "DictDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", extId='" + extId + '\'' +
                ", ordinal=" + ordinal +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isDeleted() {
        return deleted;
    }


    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
