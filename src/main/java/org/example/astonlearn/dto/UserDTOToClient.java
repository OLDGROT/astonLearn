package org.example.astonlearn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.astonlearn.model.Role;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTOToClient {
    private Long id;
    private String name;
    private String email;
    private byte age;
    private Role role;

}
