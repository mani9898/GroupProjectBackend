package org.example.profileservice.dtos;

import java.util.Date;

public record ProfileDto (Long id, String username, String aboutMe, String displayName,
                          String profilePictureUrl, String location, Date birthdate, String gender,
                          String secondaryImageUrl, String phoneNumber
){

}

