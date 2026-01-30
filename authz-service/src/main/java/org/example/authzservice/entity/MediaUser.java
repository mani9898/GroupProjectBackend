package org.example.authzservice.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity(name = "social_media_users")
public class MediaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;
    @Column (nullable = false)
    private String role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private Instant loggedIn;
    private Instant updated;
    private int attempted;
    private Instant blockedUntil;

    public MediaUser() {}
    public MediaUser(Long id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public MediaUser(String username, String email, String password, String role, UserStatus status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.attempted = 0;
        this.blockedUntil = null;
        this.loggedIn = null;
        this.updated = Instant.now();
    }

    public MediaUser(String username, String password, String role, UserStatus status, Instant loggedIn, Instant updated, int attempted, Instant blockedUntil) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.loggedIn = loggedIn;
        this.updated = updated;
        this.attempted = attempted;
        this.blockedUntil = blockedUntil;
    }

    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Instant getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Instant loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    public int getAttempted() {
        return attempted;
    }

    public void setAttempted(int attempted) {
        this.attempted = attempted;
    }

    public Instant getBlockedUntil() {
        return blockedUntil;
    }

    public void setBlockedUntil(Instant blockedUntil) {
        this.blockedUntil = blockedUntil;
    }

    @Override
    public String toString() {
        return "MediaUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", status=" + status +
                ", loggedIn=" + loggedIn +
                ", updated=" + updated +
                ", attempted=" + attempted +
                ", blockedUntil=" + blockedUntil +
                '}';
    }
}
