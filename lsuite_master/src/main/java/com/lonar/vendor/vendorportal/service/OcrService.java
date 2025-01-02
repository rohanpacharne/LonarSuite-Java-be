package com.lonar.vendor.vendorportal.service;

import org.springframework.web.multipart.MultipartFile;

public interface OcrService {
	
	public String extractText(MultipartFile file);

}
