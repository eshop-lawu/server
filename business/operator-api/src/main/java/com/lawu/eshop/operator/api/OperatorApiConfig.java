package com.lawu.eshop.operator.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Leach
 * @date 2017/4/26
 */
@Component
public class OperatorApiConfig {


    @Value(value="${image.url}")
    private String imageUrl;
    @Value(value="${video.url}")
    private String videoUrl;

    @Value(value="${image.upload.url}")
    private String imageUploadUrl;

    @Value(value="${video.upload.url}")
    private String videoUploadUrl;
    
    @Value(value="${fastdfs.trackerServers}")
    private String trackerServers;
    
    @Value(value="${fastdfs.trackerHttpPort}")
    private int trackerHttpPort;
    
    @Value(value="${ffmpeg.url}")
    private String ffmpegUrl;
    
    @Value(value="${file.upload.url}")
    private String fileUploadUrl;

    public String getTrackerServers() {
		return trackerServers;
	}

	public int getTrackerHttpPort() {
		return trackerHttpPort;
	}

	public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getImageUploadUrl() {
        return imageUploadUrl;
    }

    public String getVideoUploadUrl() {
        return videoUploadUrl;
    }

	public String getFfmpegUrl() {
		return ffmpegUrl;
	}

	public String getFileUploadUrl() {
		return fileUploadUrl;
	}
    
}
