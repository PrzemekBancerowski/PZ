package com.pz.monitor.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.pz.monitor.database.Database;
import com.pz.monitor.database.QueryFactory;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

public abstract class Base {
    protected ObjectMapper mapper;
    protected QueryFactory queryFactory;

    protected static Database db = new Database();

    static {
        if (!db.connect()) {
            System.err.println("Could not connect to database. Exiting..");
            System.exit(-1);
        }
    }

    protected Base() {
        queryFactory= new QueryFactory();
        mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.setVisibility(FIELD, ANY);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new Jdk8Module());
    }

}
