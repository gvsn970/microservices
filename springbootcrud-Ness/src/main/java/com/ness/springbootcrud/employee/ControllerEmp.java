package com.ness.springbootcrud.employee;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author nexii
 *
 */
@RestController
@RequestMapping("/emp")
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class ControllerEmp {

	@Autowired
	private ServiceEmp serviceEmp;
	@Autowired
	Environment environment;

	/**
	 * 
	 * @param uploadDp
	 * @param name
	 * @param email
	 * @param phoneNumber
	 * @return
	 */
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	@PostMapping(value = "/add")
	public ResponseEmp addEmp(@RequestParam("image") MultipartFile uploadDp,
			@RequestParam("files") MultipartFile[] files, @RequestParam String name, @RequestParam String email,
			@RequestParam String phoneNumber) {
		ResponseEmp res = new ResponseEmp();
		ResumeUploadModel resumeModel = null;
		if (name.equals("")) {
			res.setStatusCode(0);
			res.setStatusMessage("Name Required...");
			return res;
		}

		if (name.length() < 3) {
			res.setStatusCode(0);
			res.setStatusMessage("Emplyoee Name Should be at least 3 characters Required...");
			return res;
		}
		if (email.equals("")) {
			res.setStatusCode(0);
			res.setStatusMessage("Emplyoee email Required...");
			return res;
		}
		boolean emailvalid = serviceEmp.isValidEmail(email);
		if (!emailvalid) {
			res.setStatusCode(0);
			res.setStatusMessage("Invalid Email Id ");
			return res;
		}
		
		if (phoneNumber.equals("")) {
			res.setStatusCode(0);
			res.setStatusMessage("Emplyoee Phone Number Required...");
			return res;
		}
		boolean number = serviceEmp.isValidNumber(phoneNumber);
		if (!number) {
			res.setStatusCode(0);
			res.setStatusMessage("Invalid phone number ");
			return res;
		}
		boolean userExist=serviceEmp.isUserExist(email,phoneNumber);
		if(!userExist) {
			res.setStatusCode(0);
			res.setStatusMessage("Employee Already Exist");
			return res;
		}
		if (files == null) {
			res.setStatusCode(0);
			res.setStatusMessage("Emplyoee file Required...");
			return res;
		}
		try {
			EmpModel model = new EmpModel();
			model.setName(name);
			model.setEmail(email);
			model.setPhonenumber(phoneNumber);
			res = serviceEmp.addEmp(model);
			if (res.getEmpId() != 0) {
				if (uploadDp != null) {
					String UPLOADED_FOLDER = null;
					ResponseDTO responseFromCreateDir = new ResponseDTO();
					String USER_IMAGE_FOLDER = environment.getProperty("app.userimageuploaddir");
					String fileName = null;
					String filePath = null;
					USER_IMAGE_FOLDER = USER_IMAGE_FOLDER + "user";
					MultipartFile file = (MultipartFile) uploadDp;
					BufferedImage image = ImageIO.read(file.getInputStream());
					if ((file.getContentType().toLowerCase().equals("image/png"))
							|| file.getContentType().toLowerCase().equals("image/jpeg")
							|| file.getContentType().toLowerCase().equals("image/jpg")) {
						if (!(file.isEmpty() || file.getSize() == 0)) {
							if (image.getWidth() <= 600 && image.getHeight() <= 600) {
								responseFromCreateDir = serviceEmp.createDirectories(USER_IMAGE_FOLDER);
								UPLOADED_FOLDER = responseFromCreateDir.getUploadPath();
								MultipartFile file_object = uploadDp;
								if (!file_object.isEmpty()) {
									fileName = file_object.getOriginalFilename();
									Date date = new Date();
									int lastDot = fileName.lastIndexOf('.');
									fileName = fileName.substring(0, lastDot) + "_" + date.getTime()
											+ fileName.substring(lastDot);
									filePath = Paths.get(UPLOADED_FOLDER, fileName).toString();
									UserImageUploadModel uploadModel = null;

									uploadModel = new UserImageUploadModel();
									uploadModel.setFileName(fileName);
									uploadModel.setFileLocation(filePath);
									uploadModel.setCreatedBy(1);
									uploadModel.setEmpId(res.getEmpId());
									uploadModel.setFileType(FilenameUtils.getExtension(fileName));
									uploadModel.setFileSize(file.getSize() + " byte");
									if (serviceEmp.saveFileToDisk(file_object, UPLOADED_FOLDER, fileName, filePath)) {
										res = serviceEmp.addEmpDp(uploadModel);

									} else {

										res.setStatusCode(0);
										res.setStatusMessage("User image Upload Failure");
									}
								}
							} else {
								res.setStatusCode(0);
								res.setStatusMessage("User Image should be 2x2 inches");
							}
						} else {
							res.setStatusCode(0);
							res.setStatusMessage("Image Required");
						}

					} else {
						res.setStatusCode(0);
						res.setStatusMessage("Image Should be png or jpeg or jpg");
					}
				}
				if (files != null) {
					String UPLOADED_FOLDER = null;
					String RESUME_FOLDER = environment.getProperty("app.userresumeuploaddir");
					String resumeFileName = null;
					String resumeFilePath = null;
					ResponseDTO responseFromCreateDir = new ResponseDTO();
					responseFromCreateDir = serviceEmp.createDirectories(RESUME_FOLDER);
					UPLOADED_FOLDER = responseFromCreateDir.getUploadPath();
					if (files.length > 1) {
						for (MultipartFile file_object : Arrays.asList(files)) {
							resumeFileName = file_object.getOriginalFilename();
							Date date = new Date();
							int lastDot = resumeFileName.lastIndexOf('.');
							resumeFileName = resumeFileName.substring(0, lastDot) + "_" + date.getTime()
									+ resumeFileName.substring(lastDot);
							resumeFilePath = Paths.get(UPLOADED_FOLDER, resumeFileName).toString();
						 System.err.println("filePath::::" + resumeFilePath);

							resumeModel = new ResumeUploadModel();
							resumeModel.setFileName(resumeFileName);
							resumeModel.setFileLocation(resumeFilePath);
							resumeModel.setEmpId(res.getEmpId());
							resumeModel.setFileType(FilenameUtils.getExtension(resumeFileName));
							resumeModel.setFileSize(file_object.getSize() + " bytes");
							if (serviceEmp.saveFileToDisk(file_object, UPLOADED_FOLDER, resumeFileName,
									resumeFilePath)) {
								res = serviceEmp.addEmpFiles(resumeModel);
							} else {
								res.setStatusCode(0);
								res.setStatusMessage("emplyoee file  Upload Failure");
							}
						}
					} else {
						if (files.length == 1) {
							MultipartFile file_object = files[0];
							if (!file_object.isEmpty()) {
								resumeFileName = file_object.getOriginalFilename();
								Date date = new Date();
								int lastDot = resumeFileName.lastIndexOf('.');
								resumeFileName = resumeFileName.substring(0, lastDot) + "_" + date.getTime()
										+ resumeFileName.substring(lastDot);
								resumeFilePath = Paths.get(UPLOADED_FOLDER, resumeFileName).toString();
								resumeModel = new ResumeUploadModel();
								resumeModel.setFileName(resumeFileName);
								resumeModel.setFileLocation(resumeFilePath);
								resumeModel.setEmpId(res.getEmpId());
								resumeModel.setFileType(FilenameUtils.getExtension(resumeFileName));
								resumeModel.setFileSize(file_object.getSize() + " bytes");
								// System.err.println("filePath::::"+filePath);
								// System.err.println("UPLOADED_FOLDER::"+UPLOADED_FOLDER);
								if (serviceEmp.saveFileToDisk(file_object, UPLOADED_FOLDER, resumeFileName,
										resumeFilePath)) {
									res = serviceEmp.addEmpFiles(resumeModel);

								} else {
									res.setStatusCode(0);
									res.setStatusMessage("emplyoee file  Upload Failure");
								}
							}
						}
					}
					if(res.getStatusCode() ==1) {
						res.setStatusCode(1);
						res.setStatusMessage("emplyoee added successfully");
						res.setEmpId(res.getEmpId());
					}else {
						res.setStatusCode(0);
						res.setStatusMessage("emplyoee added Faild");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	@PutMapping(value = "/update")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public ResponseEmp updateEmp(@RequestParam int id, @RequestParam String name, @RequestParam String email,
			@RequestParam String phoneNumber) {

		ResponseEmp res = new ResponseEmp();
		EmpModel model = new EmpModel();
		model.setEmpId(id);
		model.setName(name);
		model.setEmail(email);
		model.setPhonenumber(phoneNumber);
		res = serviceEmp.updateEmp(model);
		return res;
	}

	@DeleteMapping(value = "/delete")
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	public ResponseEmp deleteEmp(@RequestParam int id) {
		ResponseEmp res = new ResponseEmp();
		res = serviceEmp.deleteEmp(id);
		return res;
	}

	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	@GetMapping(value = "/getallemp")
	public List<EmpDTO> getEmpDtailes() {
		List<EmpDTO> list = new ArrayList<EmpDTO>();
		list = serviceEmp.getEmpData();
		return list;
	}

	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	@GetMapping(value = "/getemp")
	public EmpDTO getEmpDtailesById(@RequestParam int id) {
		EmpDTO list = new EmpDTO();
		list = serviceEmp.getEmpDataById(id);
		return list;
	}
	
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
	@GetMapping("/userimage/view")
	public ResponseEntity<?> getUserImageView(@RequestParam(value = "imageId", defaultValue = "0") int imageId,
			@RequestParam String token) throws IOException {
		try {
			
				if (imageId == 0) {
					return new ResponseEntity<String>("ImageId is mandatory", HttpStatus.OK);
				}
				UserImageUploadModel list = serviceEmp.getUserImageByImageId(imageId);
				String imagefilepath = null;
				if (list != null) {
					imagefilepath = list.getFileLocation();

					if (!(imagefilepath.isEmpty())) {
						imagefilepath = list.getFileLocation();
						System.out.println("filepath: " + imagefilepath);
						File file = new File(imagefilepath);
						if (file.exists()) {

							InputStreamResource resource = new InputStreamResource(new FileInputStream(imagefilepath));
							return ResponseEntity.ok()
									.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + imagefilepath)
									.contentType(MediaType.IMAGE_JPEG).contentType(MediaType.IMAGE_PNG).body(resource);
						} else {
							return new ResponseEntity<String>("User Image File Not Found", HttpStatus.OK);
						}
					} else {
						return new ResponseEntity<String>("User Image File Not Found", HttpStatus.OK);
					}
				} else {
					return new ResponseEntity<String>("User Image File Not Found", HttpStatus.OK);
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("User Image File Not Found", HttpStatus.OK);
	}
}
