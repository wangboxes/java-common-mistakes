package org.geekbang.time.commonmistakes.apidesign._03_apiasyncsyncmode;

import lombok.Data;

@Data
public class SyncUploadRequest {
    private byte[] file;
}
