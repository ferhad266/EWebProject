package hotel.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "DownloadServlet", urlPatterns = "/ds")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
//        Long id = 100;
//        Worker worker = workerSerivce.getWorkerById(id);
//        System.out.println(worker.getImageFullPath());
            String filePath = request.getParameter("fileName");
//        String filePath = worker.getImageFullPath();

            File downloadFile = new File(filePath);
            FileInputStream inputStream = new FileInputStream(downloadFile);


            ServletContext context = getServletContext();

            String mimeType = context.getMimeType(filePath);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            System.out.println("mimeType: " + mimeType);

//        modifies response
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());

            //forces download
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; fileName=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);

            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
