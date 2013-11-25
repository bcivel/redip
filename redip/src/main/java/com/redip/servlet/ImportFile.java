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
import java.io.OutputStream;
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
        
        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                String fileName = null;
                List items = upload.parseRequest(request);
                List items2 = items;
                Iterator iterator = items.iterator();
                Iterator iterator2 = items2.iterator();
                File uploadedFile = null;
                String idNC = "";
                
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();

                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        if (name.equals("idNC")) {
                            idNC = item.getString();
                            System.out.println(idNC);
                        } 
                    } 
                }
                
                
                while (iterator2.hasNext()) {
                    FileItem item = (FileItem) iterator2.next();

                    if (!item.isFormField()) {
                        fileName = item.getName();
                        System.out.print(item.getSize());
                    
                    String root = "D:\\CerberusDocuments\\redip\\";
                        File pathFile = new File(root + idNC);
                        if (!pathFile.exists()) {
                            pathFile.mkdirs();
                        }
                       
                    String fullPath = pathFile + "\\" + fileName;
                    OutputStream outputStream = new FileOutputStream(fullPath);
                    InputStream inputStream = item.getInputStream();
                                        
                    int readBytes = 0;
                    byte[] buffer = new byte[10000];
                    while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                         outputStream.write(buffer, 0, readBytes);
                    }
                    outputStream.close();
                    inputStream.close();
                    
                }
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