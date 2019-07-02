package ua.kiev.prog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

@Controller
@RequestMapping("/")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class MyController {

    private long fileID = 0;

    @RequestMapping("/")
    public String onIndex() {
        return "index";
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);

        fos.write(file.getBytes());
        fos.close();

        return convFile;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public RedirectView onUploadFiles(Model model, @RequestParam(value = "file") MultipartFile[] files) {
        try {
            System.out.println("count uploaded files: " + files.length);

            // directory for save uploaded file
            String fullSavePath = "D:/ArchivizerFiles/upload";

            // create save directory if doesn't exists
            File fileSaveDir = new File(fullSavePath);
            if (!fileSaveDir.exists()) {
                System.out.println(fileSaveDir.mkdir());
            }

            String fileNames = "";

            for (MultipartFile file : files) {
                File tempFile = convertMultiPartToFile(file);
                fileNames += tempFile.getAbsolutePath() + "&";
                System.out.println(tempFile.getAbsolutePath());
            }

            fileID = fileID + 1; // increase file id
            Archivator.doArchive(fileNames.split("&"), fullSavePath + "/result_" + fileID + ".zip");

            //model.addAttribute("success_message", "Upload success");
        } catch (Exception e) {
            model.addAttribute("error_message", "Some error :(");
            return new RedirectView("/");
        }
        return new RedirectView("/download/" + fileID);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String onUploadFiles() {
        return "index";
    }

    @RequestMapping(value = "/download/{file_id}")
    public String onUploadFiles(Model model, HttpServletResponse response, @PathVariable("file_id") long fileID)
            throws IOException {
        String fullSavePath = "D:/ArchivizerFiles/upload";

        File file = new File(fullSavePath + "/result_" + fileID + ".zip");

        response.setContentType("application/csv");
        response.setHeader("Content-Disposition", "attachment; filename=file_" + fileID + ".zip");
        response.setContentLengthLong(file.length());
        ServletOutputStream out = response.getOutputStream();

        out.write(Files.readAllBytes(file.toPath()));

        out.flush();
        out.close();

        model.addAttribute("success_message", "Operation success");

        return "index";
    }
}