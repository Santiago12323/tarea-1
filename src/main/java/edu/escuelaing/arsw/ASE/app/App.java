package edu.escuelaing.arsw.ASE.app;

import java.io.IOException;
import java.nio.file.*;

public class App {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("parametros invalidos");
            System.exit(1);
        }

        String mode = args[0];
        String path = args[1];

        try {
            FileProcessor processor = new FileProcessor(mode);
            processor.processPath(Paths.get(path));
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }
}

