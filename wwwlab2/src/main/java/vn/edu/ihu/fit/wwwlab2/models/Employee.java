package vn.edu.ihu.fit.wwwlab2.models;

import jakarta.persistence.*;
import vn.edu.ihu.fit.wwwlab2.enums.EmployeeStatus;


import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emp_id", columnDefinition = "BIGINT(20)")
    private long id;
    @Column(columnDefinition = "VARCHAR(250)")
    private String address;
    @Column(columnDefinition = "DATETIME(6)")
    private LocalDateTime dob;
    @Column(columnDefinition = "VARCHAR(150)")
    private String email;
    @Column(name = "full_name",columnDefinition = "VARCHAR(150)")
    private String fullName;
    @Column(columnDefinition = "VARCHAR(15)")
    private String phone;
    @Column(columnDefinition = "INT(11)")
    private EmployeeStatus status;

    public Employee(Long id) {
        this.id = id;
    }

    public Employee() {

    }

    public Employee(long id, String address, LocalDateTime dob, String email, String fullName, String phone, EmployeeStatus status) {
        this.id = id;
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.status = status;
    }

    public Employee(long id, String address, String email, String fullName, String phone) {
        this.id = id;
        this.address = address;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
    }

    public Employee(String address, String email, String fullName, String phone) {
        this.address = address;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }

}
