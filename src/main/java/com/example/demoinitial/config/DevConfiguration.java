package com.example.demoinitial.config;

import com.example.demoinitial.domain.Person;
import com.example.demoinitial.repository.PersonRepository;
import com.example.demoinitial.utils.HasLogger;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Defines a Bean for the dev-Profile
 */
@Configuration
@Profile("dev")
// @ConditionalOnClass(name = {"org.h2.Driver"})
public class DevConfiguration implements HasLogger {

    PersonRepository personRepository;
    Person felixMuster;
    Person maxMustermann;

    @Autowired
    public DevConfiguration(PersonRepository personRepository,
                            Person felixMuster,
                            Person maxMustermann) {
        getLogger().info("Dev Configuration Class");
        this.personRepository = personRepository;
        this.felixMuster = felixMuster;
        this.maxMustermann = maxMustermann;
    }
    @PostConstruct
    public void createPersonData() {
        personRepository.save(felixMuster);
        personRepository.save(maxMustermann);
        getLogger().debug("Person felixMuster and maxMustermann saved to DB");

        List<Person> persons = personRepository.findQueryByLastName("Mustermann");
        persons.forEach(person -> getLogger().debug("person.toString() = " + person.toString()));

        personRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName")).forEach(person -> {
            personRepository.findById(person.getId())
                    .ifPresent(p -> getLogger().debug("person.toString() = " + p.toString()));
        });

    }
}
