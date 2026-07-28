package org.java.lib.entity;

import lombok.Getter;
import lombok.Setter;

public class Librarian extends User{

    @Getter
    @Setter
    String employeeNumber;

    @Override
    public void displayDashboard() {
        System.out.println("Librarian Dashboard -> EmployeeNumber : "+employeeNumber);
    }

    @Override
    public boolean canBorrowBooks() {
        return true;
    }

    public void addNewBook(Book book) {
        // will implement it later
    }

    public void removeBook(Book book) {
        // will implement later
    }
}
