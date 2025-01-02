package com.lonar.vendor.vendorportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lonar.vendor.vendorportal.model.ExtractedData;
import com.lonar.vendor.vendorportal.service.OcrService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/API/OCR")
public class OcrController {
	
	@Autowired
	OcrService ocrService;
	
	@PostMapping("/extract-text")
	public ResponseEntity<ExtractedData> extractText(@RequestParam("file") MultipartFile file) {
		try {
	    String text = ocrService.extractText(file);
	    System.out.println("text = "+text);
	    ExtractedData data = parseExtractedText(text);

	        return ResponseEntity.ok(data);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExtractedData());
	    }
	}
	
//	public ExtractedData parseExtractedText(String text) {
//        ExtractedData data = new ExtractedData();
//
//        // Extract Bill Number
//        Pattern billNumberPattern = Pattern.compile("Bill No: (HE\\d+)");
//        Matcher billNumberMatcher = billNumberPattern.matcher(text);
//        if (billNumberMatcher.find()) {
//            data.setBillNumber(billNumberMatcher.group(1));
//        }
//
//        // Extract GST Number
//        Pattern gstPattern = Pattern.compile("GST No ([A-Z0-9]+)");
//        Matcher gstMatcher = gstPattern.matcher(text);
//        if (gstMatcher.find()) {
//            data.setGstNumber(gstMatcher.group(1));
//        }
//
//        // Extract Date
//        Pattern datePattern = Pattern.compile("(\\d{2}/\\d{2}/\\d{4})");
//        Matcher dateMatcher = datePattern.matcher(text);
//        if (dateMatcher.find()) {
//            data.setDate(dateMatcher.group(1));
//        }
//
//        // Extract Total Amount
//        Pattern amountPattern = Pattern.compile("Grand Total : (\\d+\\.\\d+)");
//        Matcher amountMatcher = amountPattern.matcher(text);
//        if (amountMatcher.find()) {
//            data.setTotalAmount(amountMatcher.group(1));
//        }
//
//        return data;
//    }
	
	public ExtractedData parseExtractedText(String text) {
	    ExtractedData data = new ExtractedData();
	    
	    // Combined date pattern to match multiple formats
	    Pattern datePattern = Pattern.compile(
	        "(\\d{1,2}/\\d{1,2}/\\d{4})|" +      // DD/MM/YYYY or D/M/YYYY
	        "(\\d{1,2}-\\d{1,2}-\\d{4})|" +      // DD-MM-YYYY or D-M-YYYY
	        "(\\d{4}/\\d{1,2}/\\d{1,2})|" +      // YYYY/MM/DD or YYYY/M/D
	        "(\\d{4}-\\d{1,2}-\\d{1,2})|" +      // YYYY-MM-DD or YYYY-M-D
	        "([A-Za-z]+ \\d{1,2}, \\d{4})|" +    // Month Day, Year
	        "(\\d{1,2} [A-Za-z]+ \\d{4})|" +     // DD Month YYYY
	        "(\\d{1,2}(st|nd|rd|th)? [A-Za-z]+ \\d{4})" // Day Month Year
	    );
	    
	    Matcher dateMatcher = datePattern.matcher(text);
	    if (dateMatcher.find()) {
	        data.setDate(dateMatcher.group(0)); // Get the matched date string
	    }
	    
	    // Combined amount pattern to match multiple formats
	    Pattern amountPattern = Pattern.compile(
//	        "(Grand\\s*Total\\s*:?[\\s₹]*\\d+\\.\\d{2})|" +       // Grand Total : ₹1234.56
//	        		 "(?<!\\bb)total\\s*[:\\-\\_\\u2212]\\s*\\d+(\\.\\d+)?" +      // Grand Total : 1234.56
	        		"Grand Total\\s*:\\s*\\d+(\\.\\d+)?"
//	        "(Total\\s*Amount\\s*:?[\\s₹]*\\d+\\.\\d{2})|" +      // Total Amount : ₹1234.56
//	        "(₹\\s*\\d+\\.\\d{2})|" +                            // ₹1234.56
//	        "(Rs\\.\\s*\\d+\\.\\d{2})|" +                        // Rs. 1234.56
//	        "(INR\\s*\\d+\\.\\d{2})|" +                          // INR 1234.56
//	        "(\\d+\\.\\d{2})"                                    // 1234.56
	    );

	    Matcher amountMatcher = amountPattern.matcher(text);
	   
	    if (amountMatcher.find()) {
	    	
	        data.setTotalAmount(amountMatcher.group(0)); // Get the matched amount string
	    }
	    
	 // Combined pattern to match various bill number formats
	    Pattern billNumberPattern = Pattern.compile(
	        "(Bill\\s*No\\s*[-:]?\\s*([A-Za-z0-9-/]+))|" +         // Standard alphanumeric, hyphens, or slashes
	        "(Invoice\\s*No\\s*[-:]?\\s*([A-Za-z0-9-/]+))|" +      // Invoice No format
	        "(Receipt\\s*No\\s*[-:]?\\s*([A-Za-z0-9-/]+))|" +      // Receipt No format
	        "(bill\\s*no\\s*[-:]?\\s*([A-Za-z0-9-/]+))"            // Case-insensitive match for bill no
	    );

	    Matcher billNumberMatcher = billNumberPattern.matcher(text);
	    if (billNumberMatcher.find()) {
	        data.setBillNumber(billNumberMatcher.group(0)); // Get the matched bill number string
	    }
	    
	 // Combined pattern to match various GST number formats
	    Pattern gstPattern = Pattern.compile(
	        "(GST\\s*(IN|No|Number)?\\s*[:\\-]?\\s*([0-9A-Z]{15}))|" +    // GSTIN, GST No, etc.
	        "(gst\\s*(in|no|number)?\\s*[:\\-]?\\s*([0-9A-Z]{15}))|" +    // Lowercase variations
	        "([0-9A-Z]{15})"                                               // Plain GST number without any label
	    );

	    Matcher gstMatcher = gstPattern.matcher(text);
	    if (gstMatcher.find()) {
	        data.setGstNumber(gstMatcher.group(0)); // Get the matched GST number string
	    }
	    System.out.println("data = "+data);
	    return data;
	}

	

}
