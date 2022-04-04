package com.example.CodeFellowship.table;

import lombok.*;

import javax.persistence.*;

@Setter// use to create setter for all attribute
@Getter// use to create getter for all attribute
@NoArgsConstructor // to create deflate constructor ( constactor with out any args)
@RequiredArgsConstructor// to create constactor with arg that have  @NonNull
@Entity // to crate table for this class

public class post {
    @Setter(value = AccessLevel.NONE)
    @Id
    @GeneratedValue
//   @Column(name = "id", nullable = false)
    private Long id;

    @NonNull

    String body;
    @NonNull

    String createdAt;
    @ManyToOne
    ApplicationUser  applicationUser;
}
