package com.xcell.PageMe.model;

import java.util.Map;

public class Department {
    private Long departmentId;
    private String departmentName;

    private Map<Long, Employee> employees;

    public Department(Long departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public void addEmployee(Employee employee){
        this.employees.put(employee.getUserId(), employee);
        employee.setDepartment(this);
    }

    public Employee getEmployeeById(Long employeeId){
        return employees.get(employeeId);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee.getUserId());
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Map<Long, Employee> getEmployees() {
        return employees;
    }
}
