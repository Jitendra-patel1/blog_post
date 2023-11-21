package io.mountblue.BlogApplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @NotEmpty(message = "name should not be empty")
    @Column(name = "name")
    String name;

    @NotEmpty(message = "email should not be empty")
    @Column(name = "email",unique = true)
    String email;

    @NotEmpty(message = "comment should not be empty")
    @Column(name = "comment",columnDefinition = "TEXT")
    String comment;

    @Column(name="created_at")
    @CreationTimestamp
    Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "postId",nullable = false)
    Post post;

}