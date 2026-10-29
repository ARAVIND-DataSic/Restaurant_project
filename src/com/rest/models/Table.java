package com.rest.models;
public class Table{
    public enum AvailabilityStatus {
        FREE,OCCUPIED
    }

    private int tableId;
    private int capacity;
    private AvailabilityStatus status;

    public Table(int tableId, int capacity ){
        this.tableId = tableId;
        this.capacity = capacity;
        this.status = AvailabilityStatus.FREE;
    }

    public int getTableId() {
        return tableId;
    }
    public int getCapacity() {
        return capacity;
    }
    public AvailabilityStatus getStatus() {
        return status;
    }
    public void freeTable() {
        this.status = AvailabilityStatus.FREE;
    }
    public void occupyTable() {
        this.status = AvailabilityStatus.OCCUPIED;
    }

    public String toString() {
        return "Table ID: " + tableId + ", Capacity: " + capacity + ", Status: " + status;
    }
}




