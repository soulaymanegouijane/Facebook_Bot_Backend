package com.gouijane.facebook.publishagent.service.implementation;

import com.gouijane.facebook.publishagent.exception.FileNotPresentOrEmptyException;
import com.gouijane.facebook.publishagent.exception.FileUnzippingException;
import com.gouijane.facebook.publishagent.service.PublishingService;
import com.gouijane.facebook.publishagent.utils.FileUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.gouijane.facebook.publishagent.utils.contants.ExceptionMessages.FILE_NOT_FOUND_OR_EMPTY;

@Service
public class PublishingServiceImpl implements PublishingService {
    private final FileUtils fileUtils = new FileUtils();
    @Override
    public void schedulePostsToFacebook(MultipartFile images, MultipartFile params) throws FileNotPresentOrEmptyException, FileUnzippingException {
        validateFiles(images, params);
        // extract All Images and transfer them to a hashmap
        fileUtils.unzip(images);
        // extract posts and transfer them to a hashmap

    }

    /**
     * checks if the files are valid.
     * @param images images zip file
     * @param params csv that contains the different params of the post
     * @throws FileNotPresentOrEmptyException an exception if the files are not valid
     */
    private void validateFiles(MultipartFile images, MultipartFile params) throws FileNotPresentOrEmptyException {
        if (images.isEmpty() || params.isEmpty()) throw new FileNotPresentOrEmptyException(FILE_NOT_FOUND_OR_EMPTY);
    }
}
