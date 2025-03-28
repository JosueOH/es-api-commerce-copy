package com.es.api.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "domains")
@AttributeOverride(name = "id", column = @Column(name = "domain_id"))
public class DomainEntity extends EntityIdentifier {

    // ~ ATTRIBUTE(S) ---------------------------------------------------------

    @OneToMany(
        fetch = FetchType.LAZY, 
        mappedBy = "domainId", 
        cascade = CascadeType.ALL
    )
    private Set<UserEntity> users;

    // ~ CONSTRUCTOR(S) -------------------------------------------------------

    public DomainEntity() {
    }

    // ~ GETTER(S) SETTER(S) --------------------------------------------------

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

}
