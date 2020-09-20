package com.nexii.MultipleFileUpload.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.nexii.MultipleFileUpload.bean.Product;

@Controller
public class ProductController implements ServletContextAware {

	private ServletContext servletContext;
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
		
	}
	
	//private static String UPLOAD_FOLDER = "//home//nexii//surya_uploadfiles//";
	@RequestMapping("/")
	public String index(ModelMap modelMap) {
		modelMap.put("product", new Product());
		return "index";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("product") Product product, BindingResult result,
			@RequestParam("photos") MultipartFile files[], ModelMap modelMap) 
     {
		List<String> photos=new ArrayList<String>();
		for(MultipartFile file :files) {
			String filename=saveImage(file);
			photos.add(filename);
		}
		product.setPhotos(photos);
		modelMap.put("product",product);
		return "success";
	}

	public String saveImage(MultipartFile multipartFile) {
		try {
			byte[] bytes=multipartFile.getBytes();
			//Path path = Paths.get(UPLOAD_FOLDER + multipartFile.getOriginalFilename());
			Path path=Paths.get(servletContext.getRealPath("/resources/images/"+multipartFile.getOriginalFilename()));
			Files.write(path,bytes);
			return multipartFile.getOriginalFilename();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	

}
