package org.geekbang.time.commonmistakes.apidesign._03_apiasyncsyncmode;

import lombok.Data;

@Data
public class UploadResponse {
    private String downloadUrl;
    private String thumbnailDownloadUrl;
}
