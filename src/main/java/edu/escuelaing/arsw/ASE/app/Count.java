package edu.escuelaing.arsw.ASE.app;

import java.io.IOException;
import java.nio.file.*;

import java.io.IOException;
import java.nio.file.Paths;

public class Count {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Parámetros inválidos. ");
            System.exit(1);
        }

        String mode = args[0];
        String path = args[1];

        try {
            FileProcessor processor = new FileProcessor(mode);
            processor.processPath(Paths.get(path));
        } catch (IOException e) {
            System.err.println("Error al leer archivos: " + e.getMessage());
        }
    }
}

