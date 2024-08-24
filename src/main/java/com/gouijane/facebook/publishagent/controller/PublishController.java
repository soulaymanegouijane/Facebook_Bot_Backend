package com.gouijane.facebook.publishagent.controller;



import com.gouijane.facebook.publishagent.exception.FileNotPresentOrEmptyException;
import com.gouijane.facebook.publishagent.exception.FileUnzippingException;
import com.gouijane.facebook.publishagent.service.PublishingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/publish")
@AllArgsConstructor
public class PublishController {

    private final PublishingService publishService;
    @PostMapping
    public void schedulePostsToFacebook(@RequestParam("images") MultipartFile imagesZipFile,
                                        @RequestParam("params") MultipartFile facebookPostParams) throws FileNotPresentOrEmptyException, FileUnzippingException {
        publishService.schedulePostsToFacebook(imagesZipFile, facebookPostParams);
    }
}
