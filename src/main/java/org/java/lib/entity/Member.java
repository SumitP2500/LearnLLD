package org.java.lib.entity;

import lombok.Getter;
import lombok.Setter;

public class Member extends User{

    @Setter
    @Getter
    private int borrowedBooksCount;
    final int MAX_BORROW_LIMIT=5;

    public Member(String name, String contactInfo) {
        super(name, contactInfo);
    }

    @Override
    public void displayDashboard() {
        System.out.println("Member Dashboard -> Books Borrowed : "+borrowedBooksCount);
    }

    @Override
    public boolean canBorrowBooks() {
        return borrowedBooksCount < MAX_BORROW_LIMIT;
    }
}
