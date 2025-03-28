package com.es.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.OffsetDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class EntityIdentifier {

    // ~ ATTRIBUTE(S) ---------------------------------------------------------

    private OffsetDateTime creationDate;

    private OffsetDateTime updateDate;

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // ~ CONSTRUCTORS(S) -----------------------------------------------------

    public EntityIdentifier() {
    }

    // ~ PERSISTENCE METHOD(S) ------------------------------------------------
    
    @PrePersist
    public void setCreationAndUpdateDate() {
        setCreationDate(OffsetDateTime.now());
        setUpdateDate(OffsetDateTime.now());
    }

    @PreUpdate
    public void setUpdateDate() {
        setUpdateDate(OffsetDateTime.now());
    }

    // ~ METHOD(S) --------------------------------------------------------------

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        EntityIdentifier parsedObject = (EntityIdentifier) object;
        return (Objects.equals(getId(), parsedObject.getId()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(getClass().getSimpleName())
                .append(" [id=")
                .append(getId())
                .append(", name=")
                .append(getName())
                .append(", creationDate=")
                .append(getCreationDate())
                .append(", updateDate=")
                .append(getUpdateDate())
                .append("]")
                .toString();
    }

    // ~ GETTER(S) SETTER(S) --------------------------------------------------

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public OffsetDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(OffsetDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
