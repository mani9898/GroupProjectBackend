package org.example.profileservice.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "about_me", length = 1000)
    private String aboutMe;
    @Column(name = "display_name", length = 100)
    private String displayName;

    @Column(name = "profile_picture_url", length = 255)
    private String profilePictureUrl;
    @Column(name = "location", length = 100)
    private String location;
    @Column(name = "birthdate")
    private Date birthdate;
    @Column(name = "gender")
    private String gender;

    @Column(name = "secondary_image_url", length = 255)
    private String secondaryImageUrl;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public Profile() {

    }

    public Profile(String username, String aboutMe, String displayName, String profilePictureUrl, String location, Date birthdate, String gender, String secondaryImageUrl, String phoneNumber) {
        this.username = username;
        this.aboutMe = aboutMe;
        this.displayName = displayName;
        this.profilePictureUrl = profilePictureUrl;
        this.location = location;
        this.birthdate = birthdate;
        this.gender = gender;
        this.secondaryImageUrl = secondaryImageUrl;
        this.phoneNumber = phoneNumber;
        this.createdAt = LocalDateTime.now();
    }

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

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSecondaryImageUrl() {
        return secondaryImageUrl;
    }

    public void setSecondaryImageUrl(String secondaryImageUrl) {
        this.secondaryImageUrl = secondaryImageUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "Profile{" + "id=" + id + ", username='" + username + '\'' + ", aboutMe='" + aboutMe + '\'' + ", displayName='" + displayName + '\'' + ", profilePictureUrl='" + profilePictureUrl + '\'' + ", location='" + location + '\'' + ", birthdate=" + birthdate + ", gender='" + gender + '\'' + ", secondaryImageUrl='" + secondaryImageUrl + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", createdAt=" + createdAt + '}';
    }
}
