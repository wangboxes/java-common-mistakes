package org.geekbang.time.commonmistakes.apidesign._03_apiasyncsyncmode;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SyncQueryUploadTaskRequest {
    private final String taskId;
}
