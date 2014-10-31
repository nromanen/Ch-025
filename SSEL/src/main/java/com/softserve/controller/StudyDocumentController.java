package com.softserve.controller;

//import java.awt.sfile.BufferedSFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
//import javax.sfileio.SFileIO;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
//import org.davidmendoza.fileUpload.config.PropertyPlaceholderConfig;
import com.softserve.dao.StudyDocumentDao;
import com.softserve.entity.StudyDocument;
//import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 *
 * @author hash
 */
@Controller
@RequestMapping
//@Import(PropertyPlaceholderConfig.class)
public class StudyDocumentController {
    
    private static final Logger log = LoggerFactory.getLogger(StudyDocumentController.class);
    
    @Autowired
    private StudyDocumentDao studyDocumentDao;
    @Value("tmp")
    private String fileUploadDirectory;

    @RequestMapping(value = "/fu", method = RequestMethod.GET)
    public String index() {
        log.debug("StudyDocumentController home");
        System.out.println("uhome");
        return "fu";
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public @ResponseBody Map list() {
        log.debug("uploadGet called");
        System.out.println("uploadGet called");
        List<StudyDocument> list = studyDocumentDao.list();
/*        for(StudyDocument studyDocument : list) {
            studyDocument.setUrl("/file/"+studyDocument.getId());
            studyDocument.setDeleteUrl("/delete/"+studyDocument.getId());
            studyDocument.setDeleteType("DELETE");
        }*/
        Map<String, Object> files = new HashMap<>();
        files.put("files", list);
        log.debug("Returning: {}", files);
        return files;
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody Map upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        log.debug("uploadPost called");
        System.out.println("uploadPost called");
        new File("tmp").mkdir();
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf;
        List<StudyDocument> list = new LinkedList<>();
        
        while (itr.hasNext()) {
            mpf = request.getFile(itr.next());
            log.debug("Uploading {}", mpf.getOriginalFilename());
            
            String newFilenameBase = UUID.randomUUID().toString();
            String originalFileExtension = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf("."));
            String newFilename = newFilenameBase + originalFileExtension;
            String storageDirectory = fileUploadDirectory;
            String contentType = mpf.getContentType();
            
            File newFile = new File(storageDirectory + "/" + newFilename);
            try {
                mpf.transferTo(newFile);
                
                StudyDocument studyDocument = new StudyDocument();
                studyDocument.setName(mpf.getOriginalFilename());
                
                studyDocument.setNewFilename(newFilename);
                studyDocument.setContentType(contentType);
                studyDocument.setSize(mpf.getSize());
                
                
                byte[] fileData = new byte[(int) newFile.length()];
                
                try {
                    FileInputStream fileInputStream = new FileInputStream(newFile);
                    fileInputStream.read(fileData);
                    fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                 
                
                
                
                studyDocument.setData(fileData);
                
                studyDocument = studyDocumentDao.create(studyDocument);
                
/*                studyDocument.setUrl("/file/"+studyDocument.getId());
                
                studyDocument.setDeleteUrl("/delete/"+studyDocument.getId());
                studyDocument.setDeleteType("DELETE");*/
                
                list.add(studyDocument);
                
            } catch(IOException e) {
                log.error("Could not upload file "+mpf.getOriginalFilename(), e);
            }
            
        }
        
        Map<String, Object> files = new HashMap<>();
        files.put("files", list);
        return files;
    }
    
    @RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
    public void file(HttpServletResponse response, @PathVariable Long id) {
        StudyDocument sfile = studyDocumentDao.get(id);
        File sfileFile = new File(fileUploadDirectory+"/"+sfile.getNewFilename());
        response.setContentType(sfile.getContentType());
        response.setContentLength(sfile.getSize().intValue());
        try {
            InputStream is = new FileInputStream(sfileFile);
            IOUtils.copy(is, response.getOutputStream());
        } catch(IOException e) {
            log.error("Could not show file "+id, e);
        }
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody List delete(@PathVariable Long id) {
        StudyDocument sfile = studyDocumentDao.get(id);
        File sfileFile = new File(fileUploadDirectory+"/"+sfile.getNewFilename());
        sfileFile.delete();
        
        
        studyDocumentDao.delete(sfile);
        List<Map<String, Object>> results = new ArrayList<>();
        Map<String, Object> success = new HashMap<>();
        success.put("success", true);
        results.add(success);
        return results;
    }
}
