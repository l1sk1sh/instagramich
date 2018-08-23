package com.multiheaded.webapp.iapi.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class NetUtils {
    public static ServletRequestAttributes attr =
            (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
}
