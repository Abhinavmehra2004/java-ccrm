package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;

public class RecursiveFileUtils {

    public static long getDirectorySize(Path path) throws IOException {
        AtomicLong size = new AtomicLong(0);
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                size.addAndGet(attrs.size());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                return FileVisitResult.CONTINUE;
            }
        });
        return size.get();
    }

    public static void listFilesByDepth(Path path, int depth) throws IOException {
        Files.walk(path, depth)
                .forEach(p -> {
                    for (int i = 0; i < p.getNameCount() - path.getNameCount(); i++) {
                        System.out.print("  ");
                    }
                    System.out.println(p.getFileName());
                });
    }
}
