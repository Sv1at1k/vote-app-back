package com.example.voteapp.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private @Getter @Setter String id;
    private @NonNull @Getter @Setter String username;
    private @NonNull @Getter @Setter String password;
    private @NonNull @Getter @Setter String role;
}