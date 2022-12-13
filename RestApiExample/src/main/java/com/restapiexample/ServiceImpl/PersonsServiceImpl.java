package com.restapiexample.ServiceImpl;

import com.restapiexample.entity.Persons;
import com.restapiexample.payload.PersonDto;
import com.restapiexample.repository.PersonsRepository;
import com.restapiexample.service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonsServiceImpl implements PersonsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonsRepository personsRepository;
    @Override
    public PersonDto addPerson(PersonDto personDto) {
        personDto.setPhoneNumber(passwordEncoder.encode(personDto.getPhoneNumber()));
        Persons addedPerson = personsRepository.save(personDtoEntity(personDto));

        return entitytoPersondto(addedPerson);
    }

    public Persons personDtoEntity(PersonDto personDto){
        Persons persons=new Persons();
        persons.setFirstName(personDto.getFirstName());
        persons.setLastName(personDto.getLastName());
        persons.setEmail(personDto.getEmail());
        persons.setPhoneNumber(personDto.getPhoneNumber());
        return persons;

    }

    public PersonDto entitytoPersondto(Persons addedPersons){
        PersonDto personDto=new PersonDto();
        personDto.setId(addedPersons.getId());
        personDto.setFirstName(addedPersons.getFirstName());
        personDto.setLastName(addedPersons.getLastName());
        personDto.setEmail(addedPersons.getEmail());
        personDto.setPhoneNumber(addedPersons.getPhoneNumber());
        return personDto;
    }
}
