/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet;

import com.redip.entity.QualityNonconformities;
import com.redip.entity.QualityNonconformitiesDoc;
import com.redip.service.IQualityNonconformitiesDocService;
import com.redip.service.IQualityNonconformitiesService;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author ip100003
 */
public class ImportFile extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        Date date=new Date();
//        DateFormat df = new SimpleDateFormat("yyyyMMdd_hhmm");
//        String d = df.format(date);
//        
//        String saveFile = "";
//        String contentType = request.getContentType();
//        if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
//            DataInputStream in = new DataInputStream(request.getInputStream());
//            int formDataLength = request.getContentLength();
//            byte dataBytes[] = new byte[formDataLength];
//            int byteRead = 0;
//            int totalBytesRead = 0;
//            while (totalBytesRead < formDataLength) {
//                byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
//                totalBytesRead += byteRead;
//            }
//            String file = new String(dataBytes);
//            saveFile = file.substring(file.indexOf("filename=\"") + 10);
//            saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
//            saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\""));
//            int lastIndex = contentType.lastIndexOf("=");
//            String boundary = contentType.substring(lastIndex + 1, contentType.length());
//            int pos;
//            pos = file.indexOf("filename=\"");
//            pos = file.indexOf("\n", pos) + 1;
//            pos = file.indexOf("\n", pos) + 1;
//            pos = file.indexOf("\n", pos) + 1;
//            int boundaryLocation = file.indexOf(boundary, pos) - 4;
//            int startPos = ((file.substring(0, pos)).getBytes()).length;
//            int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
//            
//            File dir = new File("D:/CerberusDocuments/redip/");
//            dir.mkdirs();
//            
//            saveFile = "D:/CerberusDocuments/redip/"+d+"_"+ saveFile;
//            File ff = new File(saveFile);
//            FileOutputStream fileOut = new FileOutputStream(ff);
//            fileOut.write(dataBytes, startPos, (endPos - startPos));
//            fileOut.flush();
//            fileOut.close();
//            
//            ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
//            IQualityNonconformitiesService nonconformitiesService = appContext.getBean(IQualityNonconformitiesService.class);
//        
//            nonconformitiesService.getOneNonconformities(id);
//
//        }
        
        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                String fileName = null;
                List items = upload.parseRequest(request);
                Iterator iterator = items.iterator();
                File uploadedFile = null;
                String idNC = "";
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();

                    if (!item.isFormField()) {
                        fileName = item.getName();

//                        String root = getServletContext().getRealPath("/");
                        
                    } else {
                        String name = item.getFieldName();
                        if (name.equals("idNC")) {
                            idNC = item.getString();
                            System.out.println(idNC);
                        } 
                    }
                    
                    String root = "D:/CerberusDocuments/redip/";
                        File pathFile = new File(root + idNC);
                        if (!pathFile.exists()) {
                            pathFile.mkdirs();
                        }

                        uploadedFile = new File(pathFile + "/" + fileName);
                        System.out.println(uploadedFile.getAbsolutePath());
                        item.write(uploadedFile);
                }
                
                ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
                IQualityNonconformitiesDocService nonconformitiesDocService = appContext.getBean(IQualityNonconformitiesDocService.class);
        
                
            QualityNonconformitiesDoc ncDoc = new QualityNonconformitiesDoc();
            ncDoc.setIdQualityNonconformities(Integer.valueOf(idNC));
            ncDoc.setLinkToDoc(fileName);
            
            String addLinktodoc = nonconformitiesDocService.addNonconformityDoc(ncDoc);
              
            response.sendRedirect("qualitynonconformitydetails.jsp?ncid="+idNC);
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    }
}