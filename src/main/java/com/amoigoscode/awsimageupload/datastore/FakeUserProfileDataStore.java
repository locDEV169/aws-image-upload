package com.amoigoscode.awsimageupload.datastore;

import com.amoigoscode.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"senior",null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"junior",null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"fresher",null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"intern",null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
}
