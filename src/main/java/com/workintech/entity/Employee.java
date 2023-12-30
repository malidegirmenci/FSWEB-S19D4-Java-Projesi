package com.workintech.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "employee", schema = "s19d4")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "First name can not be null")
    @Size(max = 50, message = "First name can not be greater than 50 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last name can not be null")
    @Size(max = 50, message = "Last name can not be greater than 50 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Email can not be null")
    @Size(max = 50, message = "Email can not be greater than 50 characters")
    @Column(name = "email")
    private String email;

    @Min(value = 0,message = "Salary cannot be less then 0")
    @Column(name = "salary")
    private Double salary;

}
