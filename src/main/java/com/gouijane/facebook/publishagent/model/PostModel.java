package com.gouijane.facebook.publishagent.model;

import java.io.File;
import java.time.LocalDateTime;

public record PostModel(
        String id,
        String caption,
        String firstComment,
        File image,
        LocalDateTime publishTime
) {}
