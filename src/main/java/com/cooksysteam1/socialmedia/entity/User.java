package com.cooksysteam1.socialmedia.entity;

import com.cooksysteam1.socialmedia.entity.resource.Credentials;
import com.cooksysteam1.socialmedia.entity.resource.Profile;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private Timestamp joined;

    @Embedded
    private Credentials credentials;

    @Embedded
    private Profile profile;

    @OneToMany(mappedBy = "author")
    private List<Tweet> tweets;

    private boolean deleted;

    @ManyToMany(mappedBy = "following")
    private List<User> followers;

    @ManyToMany
    @JoinTable(
    name = "follows_following",
    joinColumns = @JoinColumn(name = "follower_id"),
    inverseJoinColumns = @JoinColumn(name = "following_id"))
    private List<User> following;

    @ManyToMany(mappedBy = "userLikes")
    private List<Tweet> tweetLikes;

    @ManyToMany(mappedBy = "userMentions")
    private List<Tweet> tweetMentions;
}