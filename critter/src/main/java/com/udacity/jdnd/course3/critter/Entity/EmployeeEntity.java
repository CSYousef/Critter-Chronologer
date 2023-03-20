package com.udacity.jdnd.course3.critter.Entity;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.hibernate.annotations.Nationalized;
import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Nationalized
    private String name;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<EmployeeSkill> skills;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    public EmployeeEntity(long id, String name, List<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }
    public EmployeeEntity(){}

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

    public List<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
