package dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class Supplier {
    private Integer id;
    private String name;
    private String company;
    private String emailAddress;

    public Supplier(String name, String company, String emailAddress) {
        this.name = name;
        this.company = company;
        this.emailAddress = emailAddress;
    }
}