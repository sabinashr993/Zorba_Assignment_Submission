package org.zorba.fileOperations;

public class Employee {
    private int empId;
    private String empName;
    private String empAddress;
    private String empMobile;
    private String department;
    private String doj;

    public Employee(int empId, String empName, String empAddress, String empMobile, String department, String doj) {
        this.empId = empId;
        this.empName = empName;
        this.empAddress = empAddress;
        this.empMobile = empMobile;
        this.department = department;
        this.doj = doj;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empAddress='" + empAddress + '\'' +
                ", empMobile='" + empMobile + '\'' +
                ", department='" + department + '\'' +
                ", doj=" + doj +
                '}';
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }
}
