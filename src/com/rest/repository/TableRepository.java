package com.rest.repository;

import com.rest.models.Table;
import java.util.ArrayList;
import java.util.List;

public class TableRepository {
    
    private final List<Table> tables_count = new ArrayList<>();

    public TableRepository() {
        // Initialize with some tables
        tables_count.add(new Table(1, 2));
        tables_count.add(new Table(2, 4));
        tables_count.add(new Table(3, 6));
    }

    public List<Table> findAll() {
        return tables_count;
    }
}
