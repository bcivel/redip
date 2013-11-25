/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redip.servlet.nonconformity;

import com.redip.entity.QualityNonconformities;
import com.redip.entity.QualityNonconformitiesDoc;
import com.redip.factory.IFactoryQualityNonconformities;
import com.redip.service.IEmailService;
import com.redip.service.IQualityNonconformitiesDocService;
import com.redip.service.IQualityNonconformitiesImpactService;
import com.redip.service.IQualityNonconformitiesService;
import com.redip.util.DateUtil;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author bcivel
 */
public class NonConformityAdd extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String application = "";
        String problemTitle = "";
        String problemDescription = "";
        String startDate = "";
        String startTime = "";
        String endDate = "";
        String endTime = "";
        String severity = "";
        String reproductibility = "";
        String linkToDoc = "";
        String behaviorExpected = "";
        String detection = "";
        String screenshot = "";
        String impactOrCost = "";
        FileItem item = null;
        
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
                    item = (FileItem) iterator.next();

                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        if (name.equals("Application")) {
                            application = item.getString();
                            System.out.println(application);
                        }
                        if (name.equals("ProblemTitle")) {
                            problemTitle = item.getString();
                            System.out.println(problemTitle);
                        }
                        if (name.equals("ProblemDescription")) {
                            problemDescription = item.getString();
                            System.out.println(problemDescription);
                        }
                        if (name.equals("StartDate")) {
                            startDate = item.getString();
                            System.out.println(startDate);
                        }
                        if (name.equals("StartTime")) {
                            startDate = item.getString();
                            System.out.println(startDate);
                        }
                        if (name.equals("EndDate")) {
                            endDate = item.getString();
                            System.out.println(endDate);
                        }
                        if (name.equals("EndTime")) {
                            endTime = item.getString();
                            System.out.println(endTime);
                        }
                        if (name.equals("Severity")) {
                            severity = item.getString();
                            System.out.println(severity);
                        }
                        if (name.equals("Reproductibility")) {
                            reproductibility = item.getString();
                            System.out.println(reproductibility);
                        }
                        if (name.equals("BehaviorExpected")) {
                            behaviorExpected = item.getString();
                            System.out.println(behaviorExpected);
                        }
                        if (name.equals("Detection")) {
                            detection = item.getString();
                            System.out.println(detection);
                        }
                        if (name.equals("Screenshot")) {
                            screenshot = item.getString();
                            System.out.println(screenshot);
                            System.out.println(screenshot.length());
                        }
                    } 
                }
                
                
            ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
            IQualityNonconformitiesDocService nonconformitiesDocService = appContext.getBean(IQualityNonconformitiesDocService.class);
            IQualityNonconformitiesService nonconformitiesService = appContext.getBean(IQualityNonconformitiesService.class);
            IQualityNonconformitiesImpactService nonconformitiesImpactService = appContext.getBean(IQualityNonconformitiesImpactService.class);
            IEmailService emailService = appContext.getBean(IEmailService.class);
            IFactoryQualityNonconformities factoryQNC = appContext.getBean(IFactoryQualityNonconformities.class);
        
            String startD = DateUtil.getTodayFormat("yyyy-MM-dd");
            String startT = DateUtil.getTodayFormat("hh:mm");
        
            QualityNonconformities nonconformitiestoadd = factoryQNC.create(problemTitle, 
                problemDescription, severity, reproductibility, linkToDoc, behaviorExpected, detection,
                startDate==null?startD:startDate, startTime==null?startT:startTime, screenshot);
        
        String str = nonconformitiesService.addNonconformity(nonconformitiestoadd);

        QualityNonconformities qncCount = nonconformitiesService.getMaxId();
        int id = qncCount.getIdqualitynonconformities();
        nonconformitiestoadd.setIdqualitynonconformities(id);
        
        String str2 = nonconformitiesImpactService.addNonconformityImpact(id, application,
                startDate==null?startD:startDate, startTime==null?startT:startTime, endDate, endTime, impactOrCost);
        
        boolean isFile = false;
         while (iterator2.hasNext()) {
                    item = (FileItem) iterator2.next();

                    if (!item.isFormField()) {
                        fileName = item.getName();
                        System.out.print(item.getSize());
                    
                    String root = "D:\\CerberusDocuments\\redip\\";
                        File pathFile = new File(root + id);
                        if (!pathFile.exists()) {
                            pathFile.mkdirs();
                        }
                    
                        if (!fileName.isEmpty()){
                            
                    isFile = true; 
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
                }
         
             if (isFile){
            QualityNonconformitiesDoc ncDoc = new QualityNonconformitiesDoc();
            ncDoc.setIdQualityNonconformities(Integer.valueOf(id));
            ncDoc.setLinkToDoc(fileName);
            
            String addLinktodoc = nonconformitiesDocService.addNonconformityDoc(ncDoc);
            }  

        emailService.sendEmailEvent("addNonconformity", nonconformitiestoadd, null, null);
        
        response.sendRedirect("qualitynonconformities.jsp");
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
        
    }
}
