package com.udacity.jdnd.course3.critter.Repository;
import com.udacity.jdnd.course3.critter.Entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.Entity.PetEntity;
import com.udacity.jdnd.course3.critter.Entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface scheduleRepo extends JpaRepository<ScheduleEntity, Long> {

    public List<ScheduleEntity> getSchedulesByPet(PetEntity pets);

    public List<ScheduleEntity> getSchedulesByEmployee(EmployeeEntity employees);


}
