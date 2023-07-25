package com.hiyongz.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubUtils {
    @Autowired
    private GitbubProperties gitbubProperties;

    public String getGithubUrl() {
        String GithubUrl = gitbubProperties.getUrl();
        return GithubUrl;
    }

}
