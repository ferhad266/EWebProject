package hotel.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "UploadServlet", urlPatterns = "/us")
public class UploadServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; //3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; //10MB
    private static final int REQUEST_SIZE = 1024 * 1024 * 50; //50MB

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        Worker worker = new Worker();
        System.out.println("success");
        String filePath = "";
        String fileName = "";
        String newFileName = "";
        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().print("Does not support!");
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(REQUEST_SIZE);
        System.out.println(getServletContext().getRealPath(""));

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            List formItems = upload.parseRequest(request);
            Iterator iter = formItems.iterator();
            UUID uuid = UUID.randomUUID();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
                    fileName = new File(item.getName()).getName();
                    newFileName = fileName.replace(fileName.substring(0,fileName.lastIndexOf(".")),uuid.toString());
                    filePath = uploadPath + File.separator + newFileName;
                    System.out.println("filePath: " + filePath);
                    File storeFile = new File(filePath);

                    //save file on disk
                    item.write(storeFile);

                } else {
                    if (item.getFieldName().equalsIgnoreCase("workerName")) {
                        String fName = item.getString();
//                        worker.setName(fName);
                        System.out.println("fName: " + fName);
                    }
                }
            }
//            sekilde gorsenmesi ucun
            request.setAttribute("imagePath2", UPLOAD_DIRECTORY + File.separator + newFileName);
//            bazada gorsenmesi ucun
            request.setAttribute("imagePath", filePath);
            request.setAttribute("message", "Upload has been done successfully!");
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
            ex.printStackTrace();
        }
        request.getRequestDispatcher("/view.jsp").forward(request, response);
    }
}
