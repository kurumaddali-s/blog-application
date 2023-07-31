package com.codewithdurgesh.sk.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.codewithdurgesh.sk.services.FileService;

public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		//FileName
		String name = file.getOriginalFilename();
		
		//random name generate file
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		//fullpath
		String filePath = path + File.separator + fileName1;
		
		//create folder if not created 
		File f = new File(path);
		if(!f.exists())
		{
			f.mkdir();
		}
		
		//File copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		
		return is;
	}

}
