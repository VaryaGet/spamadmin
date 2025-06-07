package io.github.mambichnaya.spamadmin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="chats")
@Getter
@Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long tgId;

    @Column(nullable = false)
    private boolean dbCheck;
    @Column(nullable = false)
    private boolean photoCheck;
    @Column(nullable = false)
    private boolean bioCheck;
    @Column(nullable = false)
    private boolean messageCheck;
    @Column(nullable = false)
    private boolean usernameCheck;

    @ManyToMany(mappedBy = "admins", fetch = FetchType.LAZY)
    private Set<Admin> admins = new HashSet<>();

    @OneToMany(mappedBy = "log", fetch = FetchType.LAZY)
    private List<Log> logs = new ArrayList<>();
}
