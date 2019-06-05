package www.archivizer.com.ua;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import static www.archivizer.com.ua.IndexServlet.SAVE_DIRECTORY;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // get full file directory
        String appPath = request.getServletContext().getRealPath("");
        appPath = appPath.replace('\\', '/');

        String fullSavePath = null;
        if (appPath.endsWith("/")) {
            fullSavePath = appPath + SAVE_DIRECTORY;
        } else {
            fullSavePath = appPath + "/" + SAVE_DIRECTORY;
        }
        // get file object
        File resultFile = new File(fullSavePath + "/result.zip");

        OutputStream out = response.getOutputStream(); // output stream
        FileInputStream in = new FileInputStream(resultFile); // input stream

        byte[] buffer = new byte[4096];
        int length;

        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length); // send data
        }

        // close and clear
        in.close();
        out.flush();
    }
}
