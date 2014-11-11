package com.softserve.controller;

//import java.awt.sfile.BufferedSFile;
import java.io.ByteArrayInputStream;
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
import com.softserve.entity.Topic;
import com.softserve.service.TopicService;



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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 *
 * @author hash
 */
@Controller
@RequestMapping
// @Import(PropertyPlaceholderConfig.class)
public class StudyDocumentController {

	private static final Logger log = LoggerFactory.getLogger(StudyDocumentController.class);

	@Autowired
	private TopicService topicService;
	@Autowired
	private StudyDocumentDao studyDocumentDao;

	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> list(@RequestParam(value = "topicId", required = false) Integer topicId) {
		log.debug("uploadGet called");
		System.out.println("uploadGet called " + topicId);

		List<StudyDocument> list = topicId != null ? studyDocumentDao.listByTopicId(topicId)
				: new ArrayList<StudyDocument>();

		for (StudyDocument studyDocument : list) {
			studyDocument.setUrl("file/" + studyDocument.getId());
			studyDocument.setDeleteUrl("delete/" + studyDocument.getId());
		}
		Map<String, Object> files = new HashMap<>();
		files.put("files", list);
		log.debug("Returning: {}", files);
		return files;
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		log.debug("uploadPost called");
		Iterator<String> itr = request.getFileNames();

		MultipartFile multipartFile;
		List<StudyDocument> list = new LinkedList<>();

		while (itr.hasNext()) {
			multipartFile = request.getFile(itr.next());
			log.debug("Uploading {}", multipartFile.getOriginalFilename());

			StudyDocument studyDocument = new StudyDocument();
			studyDocument.setName(multipartFile.getOriginalFilename());
			studyDocument.setNewFilename(UUID.randomUUID().toString());
			studyDocument.setContentType(multipartFile.getContentType());
			studyDocument.setSize(multipartFile.getSize());
			studyDocument.setTopic(topicService.getTopicById(Integer.parseInt(request.getParameter("topicId"))));
			try {
				studyDocument.setData(multipartFile.getBytes());
				studyDocument = studyDocumentDao.create(studyDocument);
				studyDocument.setUrl("file/" + studyDocument.getId());
				studyDocument.setDeleteUrl("delete/" + studyDocument.getId());
				list.add(studyDocument);
			} catch (IOException e) {
				log.error("Could not upload file " + multipartFile.getOriginalFilename(), e);
				e.printStackTrace();
			}

		}

		Map<String, Object> files = new HashMap<>();
		files.put("files", list);
		return files;
	}

	@RequestMapping(value = "file/{id}", method = RequestMethod.GET)
	public void file(HttpServletResponse response, @PathVariable Long id) {
		StudyDocument sfile = studyDocumentDao.get(id);
		// File sfileFile = new File(fileUploadDirectory + "/" +
		// sfile.getNewFilename());
		response.setContentType(sfile.getContentType());
		response.setContentLength(sfile.getSize().intValue());
		try {
			InputStream is = new ByteArrayInputStream(sfile.getData());
			IOUtils.copy(is, response.getOutputStream());
		} catch (IOException e) {
			log.error("Could not show file " + id, e);
		}
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody List delete(@PathVariable Long id) {
		studyDocumentDao.delete(id);
		List<Map<String, Object>> results = new ArrayList<>();
		Map<String, Object> success = new HashMap<>();
		success.put("success", true);
		results.add(success);
		return results;
	}
}
