package www.archivizer.com.ua;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Archivator {
    public static boolean doArchive(String[] files, String outName) {
        try {
            byte[] buffer = new byte[4096];
            int bytesRead;

            ZipOutputStream outstream = new ZipOutputStream(new FileOutputStream(outName));
            System.out.println("Count files: " + files.length);

            for (String file : files) {
                File currentFile = new File(file);
                System.out.println(file);

                if (currentFile.exists() && !file.trim().endsWith("/")) {
                    FileInputStream fis = new FileInputStream(currentFile);
                    ZipEntry entry = new ZipEntry(currentFile.getName());
                    outstream.putNextEntry(entry);

                    while ((bytesRead = fis.read(buffer)) != -1) {
                        outstream.write(buffer, 0, bytesRead);
                    }

                    fis.close();
                }
            }

            outstream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
