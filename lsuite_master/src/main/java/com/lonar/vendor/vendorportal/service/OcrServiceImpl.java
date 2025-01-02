package com.lonar.vendor.vendorportal.service;

import java.io.File;
import java.io.FileOutputStream;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OcrServiceImpl implements OcrService {

	@Override
	public String extractText(MultipartFile file) {
		// TODO Auto-generated method stub
		ITesseract tesseract = new Tesseract();
	    
	    tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
	    
	    // Get the original file name and extension
	    String originalFilename = file.getOriginalFilename();
	    
	    // Extract the file extension
	    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
	    
	    try {
	        // Create a temporary file with the original extension
	        File tempFile = File.createTempFile("temp", fileExtension);
	        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
	            fos.write(file.getBytes());
	        }

	        // Perform OCR
	        String text = tesseract.doOCR(tempFile);

	        // Delete temp file
	        tempFile.delete();

	        return text;
	    } catch (Exception e) {
	        return null;
	    }
	}

}
