package com.lawu.eshop.agent.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Leach
 * @date 2017/4/26
 */
@Component
public class AgentApiConfig {


    @Value(value="${image.url}")
    private String imageUrl;
    @Value(value="${video.url}")
    private String videoUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
