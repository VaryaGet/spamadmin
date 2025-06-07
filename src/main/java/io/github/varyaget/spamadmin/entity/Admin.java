package io.github.varyaget.spamadmin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="admins")
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long tgId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "admin_chats",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    private Set<Chat> chats = new HashSet<>();

    @OneToMany(mappedBy = "log", fetch = FetchType.LAZY)
    private List<Log> logs = new ArrayList<>();
}
