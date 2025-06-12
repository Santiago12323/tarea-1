package edu.escuelaing.arsw.ASE.app;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;

public class CountTest {

    @Test
    public void testCountPhysicalLines() throws IOException {
        FileProcessor processor = new FileProcessor("phy");
        long count = processor.processPath(Paths.get("src/test.java"));
        assertEquals(16, count);
    }

    @Test
    public void testCountLinesOfCode() throws IOException {
        FileProcessor processor = new FileProcessor("loc");
        long count = processor.processPath(Paths.get("src/testSinComentarios.java"));
        assertEquals(9, count);
    }

    @Test
    public void testCountLinesNoComments() throws IOException {
        FileProcessor processor = new FileProcessor("loc");
        long count = processor.processPath(Paths.get("src/test.java"));
        assertEquals(9, count);
    }
}
