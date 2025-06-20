package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeEntity {
    private String id;
    private String name;
    private String company;
    private String emailAddress;
}
