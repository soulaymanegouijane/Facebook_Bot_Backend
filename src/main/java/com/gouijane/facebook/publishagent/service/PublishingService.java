package com.gouijane.facebook.publishagent.service;


import com.gouijane.facebook.publishagent.exception.FileNotPresentOrEmptyException;
import com.gouijane.facebook.publishagent.exception.FileUnzippingException;
import org.springframework.web.multipart.MultipartFile;

public interface PublishingService {
    void schedulePostsToFacebook(MultipartFile images, MultipartFile params) throws FileNotPresentOrEmptyException, FileUnzippingException;
}
