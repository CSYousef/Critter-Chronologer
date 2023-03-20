package com.udacity.jdnd.course3.critter.Entity;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Nationalized
    private String name;
    private String phoneNumber;
    private String notes;
    @OneToMany(targetEntity = PetEntity.class, cascade = CascadeType.ALL)
    private List<PetEntity> pet;

    public CustomerEntity(long id, String name, String phoneNumber, String notes, List<PetEntity> pet) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pet = pet;
    }
 public CustomerEntity(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<PetEntity> getPet() {
        return pet;
    }

    public void setPet(List<PetEntity> pet) {
        this.pet = pet;
    }
}
