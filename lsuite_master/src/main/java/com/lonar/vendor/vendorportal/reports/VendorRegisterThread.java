
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

public class VendorRegisterThread extends Thread {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy ");

    Long requestId = null;
    LtMastReportRequestRepository ltMastReportRequestRepository;
    List<String> colName = null;
    List<ReportField> reportFields = null;
    String saveDirectory;

    public VendorRegisterThread(Long requestId, List<String> colName, String saveDirectory,
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
                filepath = createExcel(colName, "VendorRegisterReport", saveDirectory, reportFields);
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
        colName.clear();  
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
     // Vendor Code
        Cell c = row.createCell(0);
        c.setCellStyle(style);
        if (reportField.getVendorCode() != null) c.setCellValue(reportField.getVendorCode());

        // Vendor Name
        c = row.createCell(1);
        c.setCellStyle(style);
        if (reportField.getVendorName() != null) c.setCellValue(reportField.getVendorName());

        // Vendor Type
        c = row.createCell(2);
        c.setCellStyle(style);
        if (reportField.getVendorType() != null) c.setCellValue(reportField.getVendorType());

        // Vendor Location Type
        c = row.createCell(3);
        c.setCellStyle(style);
        if (reportField.getVendorLocationType() != null) c.setCellValue(reportField.getVendorLocationType());

        // PAN Number
        c = row.createCell(4);
        c.setCellStyle(style);
        if (reportField.getPanNo() != null) c.setCellValue(reportField.getPanNo());

        // Status
        c = row.createCell(5);
        c.setCellStyle(style);
        if (reportField.getStatus() != null) c.setCellValue(reportField.getStatus());

        // Proprietor Name
        c = row.createCell(6);
        c.setCellStyle(style);
        if (reportField.getProprietorName() != null) c.setCellValue(reportField.getProprietorName());

        // Registration Email
        c = row.createCell(7);
        c.setCellStyle(style);
        if (reportField.getRegistrationEmail() != null) c.setCellValue(reportField.getRegistrationEmail());

        // Transaction Email
        c = row.createCell(8);
        c.setCellStyle(style);
        if (reportField.getTransactionEmail() != null) c.setCellValue(reportField.getTransactionEmail());

        // Start Date
        c = row.createCell(9);
        c.setCellStyle(style);
        if (reportField.getStartDate() != null) c.setCellValue(reportField.getStartDate().toString());

        // End Date
        c = row.createCell(10);
        c.setCellStyle(style);
        if (reportField.getEndDate() != null) c.setCellValue(reportField.getEndDate().toString());

        // MSM Supplier
        c = row.createCell(11);
        c.setCellStyle(style);
        if (reportField.getMsmSupplier() != null) c.setCellValue(reportField.getMsmSupplier());

        // MSME Category
        c = row.createCell(12);
        c.setCellStyle(style);
        if (reportField.getMsmeCategory() != null) c.setCellValue(reportField.getMsmeCategory());

        // MSME Registration No
        c = row.createCell(13);
        c.setCellStyle(style);
        if (reportField.getMsmeRegistrationNo() != null) c.setCellValue(reportField.getMsmeRegistrationNo());

        // Company Category
        c = row.createCell(14);
        c.setCellStyle(style);
        if (reportField.getCompanyCategory() != null) c.setCellValue(reportField.getCompanyCategory());

        // Business Nature
        c = row.createCell(15);
        c.setCellStyle(style);
        if (reportField.getBusinessNature() != null) c.setCellValue(reportField.getBusinessNature());

        // Initiator
        c = row.createCell(16);
        c.setCellStyle(style);
        if (reportField.getInitiator() != null) c.setCellValue(reportField.getInitiator());

        // Division Name
        c = row.createCell(17);
        c.setCellStyle(style);
        if (reportField.getDivisionName() != null) c.setCellValue(reportField.getDivisionName());

        // Remark
        c = row.createCell(18);
        c.setCellStyle(style);
        if (reportField.getRemark() != null) c.setCellValue(reportField.getRemark());

        // Address Code
        c = row.createCell(19);
        c.setCellStyle(style);
        if (reportField.getAddressCode() != null) c.setCellValue(reportField.getAddressCode());

        // Address Line
        c = row.createCell(20);
        c.setCellStyle(style);
        if (reportField.getAddressLine() != null) c.setCellValue(reportField.getAddressLine());

        // City
        c = row.createCell(21);
        c.setCellStyle(style);
        if (reportField.getCity() != null) c.setCellValue(reportField.getCity());

        // State Name
        c = row.createCell(22);
        c.setCellStyle(style);
        if (reportField.getStateName() != null) c.setCellValue(reportField.getStateName());

        // Primary Address
        c = row.createCell(23);
        c.setCellStyle(style);
        if (reportField.getPrimaryAddress() != null) c.setCellValue(reportField.getPrimaryAddress());

        // State Code
        c = row.createCell(24);
        c.setCellStyle(style);
        if (reportField.getStateCode() != null) c.setCellValue(reportField.getStateCode());

        // Country
        c = row.createCell(25);
        c.setCellStyle(style);
        if (reportField.getCountry() != null) c.setCellValue(reportField.getCountry());

        // VA Start Date
        c = row.createCell(26);
        c.setCellStyle(style);
        if (reportField.getVaStartDate() != null) c.setCellValue(reportField.getVaStartDate().toString());

        // VA End Date
        c = row.createCell(27);
        c.setCellStyle(style);
        if (reportField.getVaEndDate() != null) c.setCellValue(reportField.getVaEndDate().toString());
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
