package com.xcell.PageMe.model;

import com.xcell.PageMe.enums.EmployeeRoles;

import java.util.EnumSet;

public class Employee {
    private Long userId;
    private String firstName;
    private String lastName;

    private Department department;
    private final EnumSet<EmployeeRoles> roles = EnumSet.noneOf(EmployeeRoles.class);

    public Employee(Long userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;

        // Everyone is a receiver, no matter what.
        this.roles.add(EmployeeRoles.RECEIVER);
    }

    public void addRole(EmployeeRoles role){
        this.roles.add(role);
    }

    public void removeRole(EmployeeRoles role){
        this.roles.remove(role);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EnumSet<EmployeeRoles> getRoles() {
        return roles;
    }
}
