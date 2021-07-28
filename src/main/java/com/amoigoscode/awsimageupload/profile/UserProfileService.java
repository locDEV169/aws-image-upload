package com.amoigoscode.awsimageupload.profile;

import com.amoigoscode.awsimageupload.bucket.BucketName;
import com.amoigoscode.awsimageupload.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore){
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }

    void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        //1 Check if image is not empty
        if(file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file[" + file.getSize() + "]");
        }
        //2 if file is an image
        if(!Arrays.asList(IMAGE_JPEG, IMAGE_PNG, IMAGE_GIF ).contains(file.getContentType())){
            throw new IllegalStateException("file must be an image");
        }
        //3 the user exists in our database
        UserProfile user = userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
        //4 grad some metadata from file if any
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type",file.getContentType());
        metadata.put("Content-Type", String.valueOf(file.getSize()));
        //5 Store the image in s3 and update database(userProfileId) with s3 image link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getName(),UUID.randomUUID());
        try {
            fileStore.save(path,filename,Optional.of(metadata),file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
//    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
//        return userProfileDataAccessService
//                .getUserProfiles()
//                .stream()
//                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
//                .findFirst()
//                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
//    }
//    private void isImage(MultipartFile file) {
//        if (!Arrays.asList(
//                IMAGE_JPEG.getMimeType(),
//                IMAGE_PNG.getMimeType(),
//                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
//            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
//        }
//    }
//
//    private void isFileEmpty(MultipartFile file) {
//        if (file.isEmpty()) {
//            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
//        }
//    }
}
