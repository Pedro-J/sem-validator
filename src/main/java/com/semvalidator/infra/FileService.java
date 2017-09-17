package com.semvalidator.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Component
public class FileService {

	@Autowired
	private HttpServletRequest request;

	public String write(String baseFolder, String baseName, MultipartFile file) {
		try {
			String realPath = request.getServletContext().getRealPath("/"+baseFolder);

			String filePath = realPath +"/";
			String fileName = null;

			if( baseName == null || baseName.equals("")) {
				fileName = "/" + file.getOriginalFilename();
			}else{
				fileName = "/" + baseName + file.getOriginalFilename();
			}

			File dirUploadedFile = new File(realPath);
			if( !dirUploadedFile.exists() ){
				dirUploadedFile.mkdirs();
			}

			File uploadedFile = new File((filePath + fileName));
			file.transferTo(uploadedFile);
			
			return baseFolder + fileName;
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteOnExist(String dirAndFileName){

		String realPathName = request.getServletContext().getRealPath("/"+ dirAndFileName);

		File uploadedFile = new File(realPathName);

		uploadedFile.deleteOnExit();
	}
	
}









