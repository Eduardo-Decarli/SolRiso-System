package com.decarli.solriso_system.model.security;

import com.decarli.solriso_system.model.enums.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class Admin {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private Role role;
}
