package com.udacity.jdnd.course3.critter.Service;
import com.udacity.jdnd.course3.critter.Entity.CustomerEntity;
import com.udacity.jdnd.course3.critter.Entity.PetEntity;
import com.udacity.jdnd.course3.critter.Repository.customerRepo;
import com.udacity.jdnd.course3.critter.Repository.petRepo;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class petService {

    @Autowired
    private petRepo petRe;
    @Autowired
    private customerRepo CustomerRe;

    @Autowired
    private customerService customerSer;
    public PetDTO savePet(PetDTO petDTO) {
        CustomerEntity customer = CustomerRe.getOne(petDTO.getOwnerId());

        List<PetEntity> pets = new ArrayList<>();
        if(customer.getPet()!=null)
            pets.addAll(customer.getPet());

        PetEntity pet =  new PetEntity(petDTO.getId(),
                petDTO.getType(),
                petDTO.getName(),
                customer,
                petDTO.getBirthDate(),
                petDTO.getNotes()
        );

        pets.add(petRe.save(pet));
        customer.setPet(pets);
        CustomerEntity customer1=CustomerRe.save(customer);
        petDTO.setId(customer1.getPet().get(customer1.getPet().size()-1).getId());
        return petDTO;
    }

    public PetDTO getPet( long petId) {

        PetEntity M = petRe.getOne(petId);

        return EntityToDOT(M);
    }

    public List<PetDTO> getPets(){
        List<PetEntity> pets = petRe.findAll();
        List<PetDTO> petDTO = new ArrayList<>();

        for(PetEntity M : pets){

            petDTO.add(EntityToDOT(M));
        }
        return petDTO;
    }

    public List<PetDTO> getPetsByOwner( long ownerId) {
        List<PetEntity> pets = CustomerRe.getOne(ownerId).getPet();

        List<PetDTO> petDTO = new ArrayList<>();
        for(PetEntity M : pets){
            petDTO.add(EntityToDOT(M));
        }
        return petDTO;
    }

public PetDTO EntityToDOT (PetEntity M){
    PetDTO pet = new PetDTO();
        pet.setId(M.getId());
        pet.setName(M.getName());
        pet.setType(M.getType());
        pet.setNotes(M.getNotes());
        pet.setBirthDate(M.getBirthDate());
        pet.setOwnerId(M.getOwner().getId());
        return pet;
}

    public PetEntity getPetById(Long s) {
        return petRe.getOne(s);
    }
}
