package com.udacity.jdnd.course3.critter.Entity;
import com.udacity.jdnd.course3.critter.pet.PetType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "Pets")
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private PetType type;
    @Nationalized
    private String name;
    @ManyToOne(targetEntity = CustomerEntity.class)
    private CustomerEntity owner;
    private LocalDate birthDate;
    private String notes;

    public PetEntity(long id, PetType type, String name, CustomerEntity owner, LocalDate birthDate, String notes) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
        this.notes = notes;
    }
    public PetEntity(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerEntity getOwner() {
        return owner;
    }

    public void setOwner(CustomerEntity owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
