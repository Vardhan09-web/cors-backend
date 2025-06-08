// package com.cors.project.model;

// import jakarta.persistence.*;
// import lombok.Data;

// @Entity
// @Table(name = "users")
// @Data
// public class Users {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String fullName;

//     @Column(unique = true)
//     private String email;

//     private String password; // store hashed password
// }

package com.cors.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(nullable = false)
    private String role = "USER";
}
