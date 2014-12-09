package com.softserve.controller;

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

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.softserve.dao.StudyDocumentDao;
import com.softserve.entity.StudyDocument;
import com.softserve.service.TopicService;

/**
 *
 * @author hash
 */
@Controller
@RequestMapping
public class StudyDocumentController {

	private static final Logger LOG = LoggerFactory
			.getLogger(StudyDocumentController.class);

	@Autowired
	private TopicService topicService;
	@Autowired
	private StudyDocumentDao studyDocumentDao;

	@RequestMapping(value = "upload", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(
			@RequestParam(value = "topicId", required = false) Integer topicId) {
		LOG.debug("uploadGet called");
		List<StudyDocument> list = topicId != null ? studyDocumentDao
				.listByTopicId(topicId) : new ArrayList<StudyDocument>();

		for (StudyDocument studyDocument : list) {
			studyDocument.setUrl("file/" + studyDocument.getId());
			studyDocument.setDeleteUrl("delete/" + studyDocument.getId());
		}
		Map<String, Object> files = new HashMap<>();
		files.put("files", list);
		LOG.debug("Returning: {}", files);
		files.remove("topic");
		return files;
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(MultipartHttpServletRequest request) {
		LOG.debug("uploadPost called");
		Iterator<String> itr = request.getFileNames();

		MultipartFile multipartFile;
		List<StudyDocument> list = new LinkedList<>();

		while (itr.hasNext()) {
			multipartFile = request.getFile(itr.next());
			LOG.debug("Uploading {}", multipartFile.getOriginalFilename());

			StudyDocument studyDocument = new StudyDocument();
			studyDocument.setName(multipartFile.getOriginalFilename());
			studyDocument.setNewFilename(UUID.randomUUID().toString());
			studyDocument.setContentType(multipartFile.getContentType());
			studyDocument.setSize(multipartFile.getSize());
			studyDocument.setTopic(topicService.getTopicById(Integer
					.parseInt(request.getParameter("topicId"))));
			try {
				studyDocument.setData(multipartFile.getBytes());
				studyDocument = studyDocumentDao.create(studyDocument);
				studyDocument.setUrl("file/" + studyDocument.getId());
				studyDocument.setDeleteUrl("delete/" + studyDocument.getId());
				list.add(studyDocument);
			} catch (IOException e) {
				LOG.error(
						"Could not upload file "
								+ multipartFile.getOriginalFilename(), e);
			}

		}

		Map<String, Object> files = new HashMap<>();
		files.put("files", list);
		files.remove("topic");
		return files;
	}

	@RequestMapping(value = "file/{id}", method = RequestMethod.GET)
	public void file(HttpServletResponse response, @PathVariable Long id) {
		StudyDocument sfile = studyDocumentDao.get(id);
		response.setContentType(sfile.getContentType());
		response.setContentLength(sfile.getSize().intValue());
		try {
			InputStream is = new ByteArrayInputStream(sfile.getData());
			IOUtils.copy(is, response.getOutputStream());
		} catch (IOException e) {
			LOG.error("Could not show file " + id, e);
		}
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public List delete(@PathVariable Long id) {
		studyDocumentDao.delete(id);
		List<Map<String, Object>> results = new ArrayList<>();
		Map<String, Object> success = new HashMap<>();
		success.put("success", true);
		results.add(success);
		return results;
	}
}
