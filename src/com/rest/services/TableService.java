package com.rest.services;

import com.rest.models.Table;
import com.rest.repository.TableRepository;
import java.util.List;
public class TableService {
    private final TableRepository repository;

    public TableService(){
        this(new TableRepository());
    }

    public TableService(TableRepository repository) {
        this.repository = repository;
    }

    public List<Table> getAllTables() {
        return repository.findAll();
    }
}
