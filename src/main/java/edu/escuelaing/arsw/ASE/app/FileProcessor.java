package edu.escuelaing.arsw.ASE.app;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;


public class FileProcessor {

    /** Modo de conteo: "phy" para líneas físicas, "loc" para líneas de código (Lines Of Code) */
    private final String mode;

    /**
     * Crea un nuevo FileProcessor con el modo especificado.
     *
     * @param mode Modo de conteo. Debe ser "phy" o "loc".
     */
    public FileProcessor(String mode) {
        if (!mode.equals("phy") && !mode.equals("loc")) {
            System.err.println("Invalid mode: " + mode);
        }
        this.mode = mode;
    }

    /**
     * Procesa un archivo o directorio de archivos Java y cuenta las líneas según el modo seleccionado.
     *
     * @param path Ruta del archivo o directorio a procesar.
     * @return El número total de líneas contadas.
     * @throws IOException Si ocurre un error al leer los archivos.
     */
    public long processPath(Path path) throws IOException {
        long totalCount = 0;

        if (Files.isDirectory(path)) {
            try (Stream<Path> files = Files.walk(path)) {
                totalCount = files
                        .filter(p -> p.toString().endsWith(".java"))
                        .mapToLong(this::safeProcessFile)
                        .sum();
            }
        } else {
            totalCount = safeProcessFile(path);
        }

        return totalCount;
    }

    /**
     * Procesa un archivo Java de forma segura, capturando errores de lectura.
     *
     * @param filePath Ruta del archivo a procesar.
     * @return Número de líneas contadas o 0 si ocurre un error.
     */
    private long safeProcessFile(Path filePath) {
        try {
            return processFile(filePath);
        } catch (IOException e) {
            System.err.println("Error reading file " + filePath + ": " + e.getMessage());
            return 0;
        }
    }

    /**
     * Procesa un archivo Java y cuenta sus líneas según el modo actual.
     *
     * @param filePath Ruta del archivo a procesar.
     * @return Número de líneas contadas.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    private long processFile(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath);
        long count = mode.equals("phy") ? countPhysical(lines) : countLOC(lines);
        System.out.println(filePath + ": " + count + " " + mode.toUpperCase() + " lines");
        return count;
    }

    /**
     * Cuenta todas las líneas físicas
     *
     * @param lines Lista de líneas leídas del archivo.
     * @return Número total de líneas.
     */
    long countPhysical(List<String> lines) {
        return lines.size();
    }

    /**
     * Cuenta solo las líneas de código efectivas, ignorando líneas vacías y comentarios.
     *
     * @param lines Lista de líneas leídas del archivo.
     * @return Número de líneas de código efectivas.
     */
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

            if (line.isEmpty() || line.startsWith("//")) {
                continue;
            }

            if (line.startsWith("/*")) {
                if (!line.contains("*/")) {
                    inBlockComment = true;
                }
                continue;
            }

            count++;
        }

        return count;
    }
}
