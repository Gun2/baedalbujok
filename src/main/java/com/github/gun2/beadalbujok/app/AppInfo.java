package com.github.gun2.beadalbujok.app;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
@Getter
@Slf4j
public class AppInfo {

    private final String uploadDir;

    public AppInfo(Environment environment,
                   ResourceLoader resourceLoader) throws IOException {
        this.uploadDir = resourceLoader.getResource(environment.getProperty("app.upload.dir")).getFile().getAbsolutePath();
    }
}
