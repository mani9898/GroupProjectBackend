package org.example.authzservice.repo;



import org.example.authservice.entity.MediaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMediaUserRepository extends JpaRepository<MediaUser, Long> {
    MediaUser findByUsername(String username);
    MediaUser findByEmail(String email);

}
