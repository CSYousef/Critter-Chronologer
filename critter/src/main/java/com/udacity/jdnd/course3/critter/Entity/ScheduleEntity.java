package com.udacity.jdnd.course3.critter.Entity;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @ManyToMany(targetEntity = EmployeeEntity.class)
    private List<EmployeeEntity> employee;
    @ManyToMany(targetEntity = PetEntity.class)
    private List<PetEntity> pet;
    private LocalDate date;
    @ElementCollection
    private Set<EmployeeSkill> activities;

    public ScheduleEntity(long id, List<EmployeeEntity> employee, List<PetEntity> pet, LocalDate date, Set<EmployeeSkill> activities) {
        this.id = id;
        this.employee = employee;
        this.pet = pet;
        this.date = date;
        this.activities = activities;
    }
    public ScheduleEntity(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<EmployeeEntity> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeeEntity> employee) {
        this.employee = employee;
    }

    public List<PetEntity> getPet() {
        return pet;
    }

    public void setPet(List<PetEntity> pet) {
        this.pet = pet;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
