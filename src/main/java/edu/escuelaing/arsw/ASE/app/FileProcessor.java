package edu.escuelaing.arsw.ASE.app;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class FileProcessor {
    private final String mode;

    public FileProcessor(String mode) {
        if (!mode.equals("phy") && !mode.equals("loc")) {
            throw new IllegalArgumentException("Invalid mode: " + mode);
        }
        this.mode = mode;
    }

    public void processPath(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (Stream<Path> files = Files.walk(path)) {
                files.filter(p -> p.toString().endsWith(".java"))
                        .forEach(this::processFile);
            }
        } else {
            processFile(path);
        }
    }

    private void processFile(Path filePath) {
        try {
            List<String> lines = Files.readAllLines(filePath);
            long count = mode.equals("phy") ? countPhysical(lines) : countLOC(lines);
            System.out.println(filePath + ": " + count + " " + mode.toUpperCase() + " lines");
        } catch (IOException e) {
            System.err.println("Error reading file " + filePath + ": " + e.getMessage());
        }
    }

    long countPhysical(List<String> lines) {
        return lines.size();
    }

    long countLOC(List<String> lines) {
        boolean inBlockComment = false;
        long count = 0;

        for (String line : lines) {
            line = line.trim();
            if (inBlockComment) {
                if (line.contains("*/")) {
                    inBlockComment = false;
                }
                continue;
            }
            if (line.isEmpty() || line.startsWith("//")) continue;
            if (line.startsWith("/*")) {
                inBlockComment = true;
                continue;
            }
            count++;
        }
        return count;
    }
}