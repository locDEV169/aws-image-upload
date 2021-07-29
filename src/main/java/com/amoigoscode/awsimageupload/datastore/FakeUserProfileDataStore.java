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
//        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"senior",null));
//        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"junior",null));
//        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"fresher",null));
//        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"intern",null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("e5f02dcf-e7b2-458e-a364-13fd220693f2"), "senior", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("240c8182-2f40-4c49-bdac-b4b585f02499"), "junior", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("8a512db8-2280-4a96-a49b-ca545ed0724f"), "fresher", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("b621542f-f96f-4711-b523-d1d806c66a83"), "intern", null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
}
