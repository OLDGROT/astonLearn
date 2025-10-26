package org.example.astonlearn.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.astonlearn.model.Role;
import org.springframework.hateoas.server.core.Relation;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Relation(collectionRelation = "users")
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private byte age;
    private String role;
}
