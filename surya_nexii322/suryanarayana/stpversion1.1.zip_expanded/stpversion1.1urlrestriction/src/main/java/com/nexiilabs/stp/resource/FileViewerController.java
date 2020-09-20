package com.nexiilabs.stp.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nexiilabs.stp.user.CreateUserModel;

@RestController
@RequestMapping("/fileViewer")
public class FileViewerController {
	@Autowired
	FileViewerService fileViewerService;

	@RequestMapping(path = "/download/{typeOfFile}/{fileId}", method = RequestMethod.GET)
	public ResponseEntity<?> getSteamingFile(@PathVariable("typeOfFile") String typeOfFile,
			@PathVariable("fileId") Integer fileId, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession userSession = request.getSession(false);
		FileViewerModel fileViewerModel = new FileViewerModel();
		int userId = 0;
		List<String> menus = null;
		menus = (List<String>) userSession.getAttribute("menuPermissions");
		try {
			if (userSession != null && menus.contains("Resources")||menus.contains("Accounts")) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				userId = userModel.getUserId();
				if (typeOfFile != null && fileId != 0) {
					if (typeOfFile.equals("po")) {
						fileViewerModel = fileViewerService.getPOfile(fileId, userId);
					} else if (typeOfFile.equals("sow")) {
						fileViewerModel = fileViewerService.getSOWfile(fileId, userId);
					}else if(typeOfFile.equals("agreement")){
						fileViewerModel = fileViewerService.getAgreementfile(fileId, userId);
					}
					System.out.println("fileViewerModel::" + fileViewerModel);
					if (fileViewerModel.getFilePath() != null) {
						File file = new File(fileViewerModel.getFilePath());
						InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
						if (file.exists()) {
							String mediaType = Files.probeContentType(file.toPath());
							System.out.println("mediaType: " + mediaType);
							return ResponseEntity.ok()
									.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + file.getName())
									.contentType(MediaType.parseMediaType(mediaType)).contentLength(file.length())
									.body(resource);
						} else {
							return new ResponseEntity<String>("File not found to download", HttpStatus.OK);
						}
					} else {
						return new ResponseEntity<String>("No files are found  to download", HttpStatus.OK);
					}
				} else {
					return new ResponseEntity<String>("All input fields are Mandatory", HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<String>("Login Required to access this service", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("", HttpStatus.ACCEPTED);
	}
}
