package com.lonar.vendor.vendorportal.reports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.lonar.vendor.vendorportal.model.LtMastReportRequest;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.service.LtMastSysVariablesService;


public class TravelReportThread extends Thread {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy ");

    Long requestId = null;
    LtMastReportRequestRepository ltMastReportRequestRepository;
    List<String> colName = null;
    List<ReportField> reportFields = null;
    String saveDirectory;

    public TravelReportThread(Long requestId, List<String> colName, String saveDirectory,
            List<ReportField> reportFields, LtMastReportRequestRepository ltMastReportRequestRepository) {
        this.requestId = requestId;
        this.colName = colName;
        this.reportFields = reportFields;
        this.saveDirectory = saveDirectory;
        this.ltMastReportRequestRepository = ltMastReportRequestRepository;
    }

    public void run() {
        String filepath = null;
        try {
            if (reportFields != null && !reportFields.isEmpty()) {
            	System.out.println("in if...");
                filepath = createExcel(colName, "TravelReport", saveDirectory, reportFields);
            }
            makeEntryForReport(requestId, filepath, "Success","COMPLETED", saveDirectory);
        } catch (Exception e) {
        	System.out.println("in catch...");
            makeEntryForReport(requestId, filepath, "Errors","COMPLETED", saveDirectory);
            e.printStackTrace();
        } finally {
            colName = null;  // Clean up resources
        }
    }

    private void makeEntryForReport(Long requestId, String filepath, String status,String phase, String saveDirectory) {
        LtMastReportRequest ltMastReportRequest = ltMastReportRequestRepository.findOne(requestId);
        ltMastReportRequest.setCompletedDate(new Date());
        ltMastReportRequest.setFilePath(saveDirectory + filepath);
        ltMastReportRequest.setStatus(status);
        ltMastReportRequest.setPhase(phase);
        ltMastReportRequest.setFileName(filepath);
        ltMastReportRequest.setEndDate(new Date());
        ltMastReportRequestRepository.save(ltMastReportRequest);
    }

    private String createExcel(List<String> colName, String reportName, String saveDirectory, List<ReportField> reportFields)
            throws ServiceException, IOException {

        Workbook workbook = new SXSSFWorkbook();
        String fileName = null;
        Date cdate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateReportCreatedBy = formatter.format(cdate);
        Sheet sheet = workbook.createSheet(reportName);
        File files = new File(saveDirectory);
        if (!files.exists()) {
            files.mkdirs();
        }
        
        insertHeaderInfoForExcelReport(sheet, 0, workbook, colName);
        colName.clear();  // Clear colName after inserting headers
      
        if (reportFields != null && !reportFields.isEmpty()) {
      
            int rowIndex = 1;  // Start from row 1 since row 0 is for headers
            for (ReportField reportField : reportFields) {
            	
                insertDetailsInfoForReportField(sheet, rowIndex, reportField, workbook);
              
                rowIndex++;
            }
        }
       
        fileName = reportName + "_" + dateReportCreatedBy + ".xlsx";
        String filePath = saveDirectory + fileName;

        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
       
        return fileName;
        
    }
    private void insertDetailsInfoForReportField(Sheet sheet, int rowIndex, ReportField reportField, Workbook workbook) {
        Row row = sheet.createRow(rowIndex);
        CellStyle style = setCellStylesForData(workbook);
        // Expense Number
        Cell c = row.createCell(0);
        row.getCell(0).setCellStyle(style);
        if (reportField.getExpenseNumber() != null) c.setCellValue(reportField.getExpenseNumber());

        // Expense Date
        c = row.createCell(1);
        row.getCell(1).setCellStyle(style);
        if (reportField.getExpenseDate() != null) c.setCellValue(simpleDateFormat.format(reportField.getExpenseDate()));

     // Employee Name
        c = row.createCell(2);
        c.setCellStyle(style);
        if (reportField.getEmployeeName() != null) c.setCellValue(reportField.getEmployeeName());

        // Header Start Date
        c = row.createCell(3);
        c.setCellStyle(style);
        if (reportField.getHdrStartDate() != null) c.setCellValue(simpleDateFormat.format(reportField.getHdrStartDate()));

        // Header End Date
        c = row.createCell(4);
        c.setCellStyle(style);
        if (reportField.getHdrEndDate() != null) c.setCellValue(simpleDateFormat.format(reportField.getHdrEndDate()));

        // Division Name
        c = row.createCell(5);
        c.setCellStyle(style);
        if (reportField.getDivisionName() != null) c.setCellValue(reportField.getDivisionName());

        // Branch Name
        c = row.createCell(6);
        c.setCellStyle(style);
        if (reportField.getBranchName() != null) c.setCellValue(reportField.getBranchName());

        // Duration
        c = row.createCell(7);
        c.setCellStyle(style);
        if (reportField.getDuration() != null) c.setCellValue(reportField.getDuration());


        // line no
        c = row.createCell(8);
        c.setCellStyle(style);
        if (reportField.getLineNo() != null) c.setCellValue(reportField.getLineNo());
        
        // Line Start Date
        c = row.createCell(9);
        c.setCellStyle(style);
        if (reportField.getLineStartDate() != null) c.setCellValue(simpleDateFormat.format(reportField.getLineStartDate()));

        // Line End Date
        c = row.createCell(10);
        c.setCellStyle(style);
        if (reportField.getLineEndDate() != null) c.setCellValue(simpleDateFormat.format(reportField.getLineEndDate()));

        // Status
        c = row.createCell(11);
        c.setCellStyle(style);
        if (reportField.getStatus() != null) c.setCellValue(reportField.getStatus());

        // Expense Nature
        c = row.createCell(12);
        c.setCellStyle(style);
        if (reportField.getExpenseNature() != null) c.setCellValue(reportField.getExpenseNature());
    }


    private void insertHeaderInfoForExcelReport(Sheet sheet, int rowIndex, Workbook workbook, List<String> colName) {
        Row row = sheet.createRow(rowIndex);
        CellStyle style = setCellStylesForHeader(workbook);
        for (int i = 0; i < colName.size(); i++) {
            Cell c = row.createCell(i);
            row.getCell(i).setCellStyle(style);
            c.setCellValue(colName.get(i));
        }
    }

    private CellStyle setCellStylesForData(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }

    private CellStyle setCellStylesForHeader(Workbook wb) {
        Font bold = wb.createFont();
        bold.setBoldweight(Font.BOLDWEIGHT_BOLD);

        CellStyle style = wb.createCellStyle();
        style.setFont(bold);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);

        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }
}
