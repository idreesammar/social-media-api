package com.cooksysteam1.socialmedia.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "hashtag")
@Getter
@Setter
@NoArgsConstructor
public class Hashtag {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String label;

    @CreationTimestamp
    private Timestamp firstUsed;

    @UpdateTimestamp
    private Timestamp lastUsed;

    @ManyToMany(mappedBy = "hashtags")
    private List<Tweet> tweets;
}
