package org.java.lib.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

public abstract class User {

    @Getter
    private String userId;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String contactInfo;

    @Getter
    private static int totalUsers=0;

    public User() {
        userId = generateUniqueId();
    }

    public User(String name, String contactInfo) {
        this();
        this.name=name;
        this.contactInfo=contactInfo;
        totalUsers++;
    }

    public User(User u) {
        this();
        this.name = u.name;
        this.contactInfo = u.contactInfo;
        totalUsers++;
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    public abstract void displayDashboard();

    public abstract  boolean canBorrowBooks();

}
