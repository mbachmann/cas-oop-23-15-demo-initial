package com.example.demoinitial.config;

import com.example.demoinitial.domain.Person;
import com.example.demoinitial.utils.HasLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;

@Configuration
public class MyBean implements CommandLineRunner, HasLogger {
    MyComponent myComponent;

    @Inject
    @Named("felixMuster")
    Person felixMuster;

    Person maxMustermann;

    @Autowired
    public MyBean(MyComponent myComponent, @Qualifier("maxMustermann") Person maxMustermann) {
        this.myComponent = myComponent;
        this.maxMustermann = maxMustermann;
    }

    public void run(String[] args) {
        myComponent.hello();
        getLogger().info("getTestValue = " + myComponent.getTestValue());
        getLogger().debug("Felix Muster Test " + felixMuster.toString());
        getLogger().debug("Max Mustermann Test " + maxMustermann.toString());
        getLogger().info("Command Line Runner ");
    }
}
