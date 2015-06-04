package kr.tagnote.file;

import java.io.IOException;

import kr.tagnote.article.ArticleController;
import kr.tagnote.article.ArticleService;
import kr.tagnote.common.Value;
import kr.tagnote.user.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	@Autowired
	FileService fileService;
	
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Value<Long> upload(@ModelAttribute("file") MultipartFile file){
		Value<Long> response = new Value<Long>();
		TagFile tagFile = new TagFile();

		if(file == null || !file.getContentType().contains("image")){
			response.setValue((long)-1);
			return response;
		}
		
		tagFile.setName(file.getOriginalFilename());
		tagFile.setSize(file.getSize());
		tagFile.setType(file.getContentType());
		try {
			tagFile.setBytes(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
//		logger.info("imgFile : " + imgFile);
		tagFile = fileService.saveTagFile(tagFile);
		response.setValue(tagFile.getFileId());
		return response;
	}
	
	@RequestMapping(value = "/remove")
	@ResponseBody
	public Value<String> remove(@RequestParam("fileId") long fileId){
		Value<String> response = new Value<String>();
		
		TagFile tagFile = fileService.findById(fileId);

		if(tagFile != null){
			fileService.delete(tagFile.getFileId());
			fileService.deleteResource(tagFile.getPublicId());
			response.setValue("success");
		} else
			response.setValue("fail");
		return response;
	}
}
