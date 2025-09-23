package edu.ccrm.io;

import edu.ccrm.config.AppConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupService {

    private final Path backupDir = Paths.get(AppConfig.getInstance().getProperty("backup.folder"));

    public void backupData() throws IOException {
        if (!Files.exists(backupDir)) {
            Files.createDirectories(backupDir);
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        Path timestampedBackupDir = backupDir.resolve(timestamp);
        Files.createDirectory(timestampedBackupDir);

        Path dataDir = Paths.get(AppConfig.getInstance().getProperty("data.folder"));
        if (!Files.exists(dataDir)) return;

        Files.walk(dataDir)
                .filter(Files::isRegularFile)
                .forEach(source -> {
                    Path destination = timestampedBackupDir.resolve(dataDir.relativize(source));
                    try {
                        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
