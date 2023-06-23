package com.example.avitobybraincell.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {
    @Id
    private int id;
    private int author;
    private int pk;
    private LocalDateTime createdAt;
    private String text;
}
