package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.EmployeeEntity;
import com.udacity.jdnd.course3.critter.Repository.employeeRepo;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class employeeService {
    @Autowired
    private employeeRepo employeeRe;


    public EmployeeDTO saveEmployee( EmployeeDTO employeeDTO) {
        List<EmployeeSkill> employeeSkills = new ArrayList<>(employeeDTO.getSkills());

        EmployeeEntity M = new EmployeeEntity(employeeDTO.getId(),employeeDTO.getName(),employeeSkills,employeeDTO.getDaysAvailable());
        employeeDTO.setId(employeeRe.save(M).getId());
        return employeeDTO;
    }

    public EmployeeDTO getEmployee( long employeeId) {
      return EntityToDTO(employeeRe.getOne(employeeId));
    }

    public void setAvailability( Set<DayOfWeek> daysAvailable,  long employeeId) {
        EmployeeEntity M = employeeRe.getOne(employeeId);
        M.setDaysAvailable(daysAvailable);
        employeeRe.save(M);
    }

    public List<EmployeeDTO> findEmployeesForService( EmployeeRequestDTO employeeDTO) {
        List<EmployeeEntity> employees = employeeRe.getEmployeesByDaysAvailable(employeeDTO.getDate().getDayOfWeek());
        List<EmployeeEntity> employeeAvailable = new ArrayList<>();
        List<EmployeeDTO> emplDTO=new ArrayList<>();
        for(EmployeeEntity e : employees){
            if (employeeDTO.getSkills().stream().allMatch( M-> e.getSkills().stream().anyMatch(Y-> M == Y )) )
                employeeAvailable.add(e);
        }

        for (EmployeeEntity e : employeeAvailable){
            emplDTO.add(EntityToDTO(e));
        }

        return emplDTO;
    }

public EmployeeDTO EntityToDTO(EmployeeEntity M){
    EmployeeDTO employee = new EmployeeDTO();
    employee.setId(M.getId());
    employee.setName(M.getName());
    employee.setSkills(new HashSet<>(M.getSkills()));
    employee.setDaysAvailable(M.getDaysAvailable());
    return employee;
}

    public EmployeeEntity getEmployeeById(Long s) {
    return employeeRe.getOne(s);
    }
}
