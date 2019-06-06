package www.archivizer.com.ua;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class IndexServlet extends HttpServlet {

    public static final String SAVE_DIRECTORY = "upload";
    private int fileId = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            // get app path
            String appPath = request.getServletContext().getRealPath("");
            appPath = appPath.replace('\\', '/');

            // directory for save uploaded file
            String fullSavePath = null;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath + SAVE_DIRECTORY;
            } else {
                fullSavePath = appPath + "/" + SAVE_DIRECTORY;
            }

            // create save directory if doesn't exists
            File fileSaveDir = new File(fullSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }

            String fileNames = "";

            // multifiles part collection
            for (Part part : request.getParts()) {
                if (part == null) break;
                String fileName = extractFileName(part);
                fileNames += fullSavePath + "/" + fileName + " ";
                if (fileName != null && fileName.length() > 0) {
                    String filePath = fullSavePath + File.separator + fileName;
                    //System.out.println("Write attachment to file: " + filePath);
                    // save data to file
                    part.write(filePath);
                }
            }
            fileId = fileId + 1; // increase file id
            HttpSession session = request.getSession(true);
            session.setAttribute("file_id", fileId);
            Archivator.doArchive(fileNames.split(" "), fullSavePath + "/result" + fileId + ".zip");
            // success
            response.sendRedirect("/download");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/uploadFile.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsps/uploadFile.jsp");

        dispatcher.forward(request, response);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");

        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');

                return clientFileName.substring(i + 1);
            }
        }

        return null;
    }
}
