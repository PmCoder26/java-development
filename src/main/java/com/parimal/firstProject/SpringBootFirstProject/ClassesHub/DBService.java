package com.parimal.firstProject.SpringBootFirstProject.ClassesHub;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    private DB db;

    public String getData(){
        return db.getData();
    }

}
