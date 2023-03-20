package com.udacity.jdnd.course3.critter.Service;
import com.udacity.jdnd.course3.critter.Entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.Entity.PetEntity;
import com.udacity.jdnd.course3.critter.Entity.ScheduleEntity;
import com.udacity.jdnd.course3.critter.Repository.scheduleRepo;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class scheduleService {

    @Autowired
    scheduleRepo scheduleRe;
    @Autowired
    private petService petSer;

    @Autowired
    private employeeService employeeSer;

    @Autowired
    private customerService customerSer;

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {

        List<EmployeeEntity> employees = new ArrayList<>();
        List<PetEntity> pets = new ArrayList<>();

        if(scheduleDTO.getEmployeeIds()!= null )
            employees = scheduleDTO.getEmployeeIds().stream().map(s -> employeeSer.getEmployeeById(s)).collect(Collectors.toList());
        if( scheduleDTO.getPetIds() != null)
            pets = scheduleDTO.getPetIds().stream().map(s -> petSer.getPetById(s)).collect(Collectors.toList());

        ScheduleEntity M = new ScheduleEntity(scheduleDTO.getId(), employees, pets, scheduleDTO.getDate(), scheduleDTO.getActivities());
        scheduleDTO.setId(scheduleRe.save(M).getId());

        return scheduleDTO;

    }

    public List<ScheduleDTO> getAllSchedules() {

        List<ScheduleEntity> ScheduleE = scheduleRe.findAll();
        List<ScheduleDTO> ScheduleD = new ArrayList<>();

        for(ScheduleEntity M : ScheduleE){

            ScheduleD.add(EntityToDTO(M));
        }
        return ScheduleD;
    }

    public List<ScheduleDTO> getScheduleForPet(long petId) {

        List<ScheduleEntity> scheduleE = scheduleRe.getSchedulesByPet(petSer.getPetById(petId));

        List<ScheduleDTO> schedules = new ArrayList<>();

        for(ScheduleEntity m : scheduleE){
            schedules.add(EntityToDTO(m));
        }
        return schedules;
    }

    public List<ScheduleDTO> getScheduleForEmployee(long employeeId) {
        List<ScheduleEntity> scheduleE = scheduleRe.getSchedulesByEmployee(employeeSer.getEmployeeById(employeeId));
        List<ScheduleDTO> schedules = new ArrayList<>();
        for(ScheduleEntity m : scheduleE){
            schedules.add(EntityToDTO(m));
        }
        return schedules;
    }

    public List<ScheduleDTO> getScheduleForCustomer(long customerId) {

        List<ScheduleDTO>  schedules = new ArrayList<>();

        customerSer.getCustomerById(customerId).getPet()
                .stream().forEach(pet-> schedules.
                        addAll(getScheduleForPet(pet.getId())));

        return schedules;
    }

    public ScheduleDTO EntityToDTO(ScheduleEntity M){

        List petsID = new ArrayList<>();
        if(M.getPet() != null)
            for( PetEntity p :M.getPet())  {
                petsID.add(p.getId());
            }

        List empID = new ArrayList<>();
        if(M.getEmployee() != null)
            for( EmployeeEntity e :M.getEmployee())  {
                empID.add(e.getId());
            }

        ScheduleDTO schedule = new ScheduleDTO();
        schedule.setId(M.getId());
        schedule.setEmployeeIds(empID);
        schedule.setPetIds(petsID);
        schedule.setDate(M.getDate());
        schedule.setActivities(M.getActivities());
     return schedule;
    }

}