package com.multiheaded.webapp.iapi.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class InstagramUserRequest extends AbstractSignedRequest {
    @NotBlank
    private String handle;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }
}
