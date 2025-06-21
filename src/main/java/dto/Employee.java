package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {


    private Integer id;
    private String name;
    private String company;
    private String emailAddress;

    public Employee(String name, String company, String emailAddress) {
        this.name = name;
        this.company = company;
        this.emailAddress = emailAddress;
    }
}
