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

public class PurchaseReportThread extends Thread {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy ");

    Long requestId = null;
    LtMastReportRequestRepository ltMastReportRequestRepository;
    List<String> colName = null;
    List<ReportField> reportFields = null;
    String saveDirectory;

    public PurchaseReportThread(Long requestId, List<String> colName, String saveDirectory,
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
                filepath = createExcel(colName, "PurchaseReport", saveDirectory, reportFields);
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
            // PO Number
            Cell c = row.createCell(0);
            c.setCellStyle(style);
            if (reportField.getPoNumber() != null) c.setCellValue(reportField.getPoNumber());

            // PO Type
            c = row.createCell(1);
            c.setCellStyle(style);
            if (reportField.getPoType() != null) c.setCellValue(reportField.getPoType());
            // PO Date
            c = row.createCell(2);
            c.setCellStyle(style);
            if (reportField.getPoDate() != null) c.setCellValue(simpleDateFormat.format(reportField.getPoDate()));


            // Revision Number
            c = row.createCell(3);
            c.setCellStyle(style);
            if (reportField.getRevisionNum() != null) c.setCellValue(reportField.getRevisionNum());

           
            // Revision Date
            c = row.createCell(4);
            c.setCellStyle(style);
            if (reportField.getRevisionDate() != null) c.setCellValue(simpleDateFormat.format(reportField.getRevisionDate()));

            // Description
            c = row.createCell(5);
            c.setCellStyle(style);
            if (reportField.getDescription() != null) c.setCellValue(reportField.getDescription());

            // Status
            c = row.createCell(6);
            c.setCellStyle(style);
            if (reportField.getStatus() != null) c.setCellValue(reportField.getStatus());

            // Note to Approver
            c = row.createCell(7);
            c.setCellStyle(style);
            if (reportField.getNoteToApprover() != null) c.setCellValue(reportField.getNoteToApprover());

            // Vendor Name
            c = row.createCell(8);
            c.setCellStyle(style);
            if (reportField.getVendorName() != null) c.setCellValue(reportField.getVendorName());

            // Vendor Address
            c = row.createCell(9);
            c.setCellStyle(style);
            if (reportField.getVendorAddress() != null) c.setCellValue(reportField.getVendorAddress());

            // Vendor Contact
            c = row.createCell(10);
            c.setCellStyle(style);
            if (reportField.getVendorContact() != null) c.setCellValue(reportField.getVendorContact());

            // Buyer Name
            c = row.createCell(11);
            c.setCellStyle(style);
            if (reportField.getBuyerName() != null) c.setCellValue(reportField.getBuyerName());

            // Billing Address
            c = row.createCell(12);
            c.setCellStyle(style);
            if (reportField.getBillingAddress() != null) c.setCellValue(reportField.getBillingAddress());

            // PO Currency
            c = row.createCell(13);
            c.setCellStyle(style);
            if (reportField.getPoCurrency() != null) c.setCellValue(reportField.getPoCurrency());

            // Line Number
            c = row.createCell(14);
            c.setCellStyle(style);
            if (reportField.getLineNo() != null) c.setCellValue(reportField.getLineNo());

            // Line Type
            c = row.createCell(15);
            c.setCellStyle(style);
            if (reportField.getLineType() != null) c.setCellValue(reportField.getLineType());

            // Product Code
            c = row.createCell(16);
            c.setCellStyle(style);
            if (reportField.getProductCode() != null) c.setCellValue(reportField.getProductCode());

            // Product Name
            c = row.createCell(17);
            c.setCellStyle(style);
            if (reportField.getProductName() != null) c.setCellValue(reportField.getProductName());

            // Quantity
            c = row.createCell(18);
            c.setCellStyle(style);
            if (reportField.getQuantity() != null) c.setCellValue(reportField.getQuantity());

            // Quantity
            c = row.createCell(19);
            c.setCellStyle(style);
            if (reportField.getUnitPrice() != 0) c.setCellValue(reportField.getUnitPrice());

            // Tax Name
            c = row.createCell(20);
            c.setCellStyle(style);
            if (reportField.getTaxName() != null) c.setCellValue(reportField.getTaxName());

            // Line Amount
            c = row.createCell(21);
            c.setCellStyle(style);
            if (reportField.getLineAmount() != null) c.setCellValue(reportField.getLineAmount());

            // Tax Amount
            c = row.createCell(22);
            c.setCellStyle(style);
            if (reportField.getTaxAmount() != null) c.setCellValue(reportField.getTaxAmount());

            // Total Amount
            c = row.createCell(23);
            c.setCellStyle(style);
            if (reportField.getTotalAmount() != null) c.setCellValue(reportField.getTotalAmount());

            // Term Name
            c = row.createCell(24);
            c.setCellStyle(style);
            if (reportField.getTermName() != null) c.setCellValue(reportField.getTermName());
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
