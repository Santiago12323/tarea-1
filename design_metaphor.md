
# Design Metaphor - Line Counter

**Author**: Santiago Coronado Pinzon

**Date**: 2025-06-12

---

## Class Diagram

```plaintext
+------------------------------+
|     FileProcessor            |
+------------------------------+
| - mode: String               |
+------------------------------+
| +processPath(Path): long     |
| +countPhysical(List): long   |
| +countLOC(List): long        |
| -safeProcessFile(Path): long |
| -processFile(Path): long     |
+------------------------------+

             ▲
             |
+----------------------+
|         App          |
+----------------------+
| +main(String[]): void|
+----------------------+
