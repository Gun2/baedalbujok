package com.github.gun2.beadalbujok.app;

import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
@Getter
public class AppInfo {

    private final String uploadDir;

    public AppInfo(Environment environment,
                   ResourceLoader resourceLoader) throws IOException {
        this.uploadDir = Path.of(resourceLoader.getResource("classpath:").getURI())
                .getParent()
                .getParent()
                .getParent()
                .getParent()
                .toFile()
                .getAbsolutePath() + "/" + environment.getProperty("app.upload.dir");

    }
}
