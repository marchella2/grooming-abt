package com.example.tugas1_marchellaps.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {
    private String username;
    private String password;
}
