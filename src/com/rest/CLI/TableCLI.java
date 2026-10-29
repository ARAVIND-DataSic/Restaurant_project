package com.rest.CLI;

import com.rest.models.Table;
import com.rest.services.TableService;
import java.util.List;
import java.util.Scanner;

public class TableCLI {

    private final TableService service;
    private final Scanner scanner;

    public TableCLI(TableService service) {
        this(service, new Scanner(System.in));
    }

    public TableCLI(TableService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("== RESTAURANT TABLE MANAGEMENT ==");
        System.out.println("1. View All Tables");
        System.out.println("2. Exit");

        System.out.print("Enter your choice: ");
        if (!scanner.hasNextLine()) {
            return;
        }

        String input = scanner.nextLine().trim();
        if ("1".equals(input)) {
            List<Table> tables = service.getAllTables();
            System.out.println("\n== ALL TABLES ==");

            for (Table table : tables) {
                System.out.println(table);
            }
        } else if ("2".equals(input)) {
            System.out.println("THANK YOU");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}
