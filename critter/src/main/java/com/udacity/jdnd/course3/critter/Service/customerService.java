package com.udacity.jdnd.course3.critter.Service;
import com.udacity.jdnd.course3.critter.Entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entity.PetEntity;
import com.udacity.jdnd.course3.critter.Repository.customerRepo;
import com.udacity.jdnd.course3.critter.Repository.petRepo;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class customerService {

    @Autowired
    private customerRepo customerRe;
    @Autowired
   private petRepo petRe;

    public CustomerDTO SaveCustomer(CustomerDTO customer){

        List pets = new ArrayList<>();
        if(customer.getPetIds() != null)
          for( Long p :customer.getPetIds())  {
              pets.add(petRe.getOne(p));
          }
        CustomerEntity M = new CustomerEntity(customer.getId(),customer.getName(),customer.getPhoneNumber(),customer.getNotes(),pets);
          customer.setId((customerRe.save(M).getId()));
        return customer;
    }


    public CustomerDTO GetOwnerByPet(Long petID){
        CustomerEntity M = petRe.getOne(petID).getOwner();
      return EntityToDOT(M);
    }



   public List<CustomerDTO> GetAllCustomers(){

        List<CustomerEntity> Entity = customerRe.findAll();
        List<CustomerDTO> Customers= new ArrayList<>();
        for(CustomerEntity M : Entity){
            Customers.add(EntityToDOT(M));
        }
        return Customers;
   }

   public CustomerDTO EntityToDOT (CustomerEntity M){
       List petsID = new ArrayList<>();
       if(M.getPet() != null)
           for( PetEntity p :M.getPet())  {
               petsID.add(p.getId());
           }
       CustomerDTO customer = new CustomerDTO();
       customer.setId(M.getId());
       customer.setName(M.getName());
       customer.setNotes(M.getNotes());
       customer.setPhoneNumber(M.getPhoneNumber());
       customer.setPetIds(petsID);
       return customer;
    }

    public CustomerEntity getCustomerById(long s) {
        return customerRe.getOne(s);
    }
}
