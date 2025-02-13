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

public class InvoiceReportThread extends Thread {	

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy ");

    Long requestId = null;
    LtMastReportRequestRepository ltMastReportRequestRepository;
    List<String> colName = null;
    List<ReportField> reportFields = null;
    String saveDirectory;

    public InvoiceReportThread(Long requestId, List<String> colName, String saveDirectory,
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
                filepath = createExcel(colName, "InvoiceVsPaymentReport", saveDirectory, reportFields);
            }
            makeEntryForReport(requestId, filepath, "Completed", saveDirectory);
        } catch (Exception e) {
            System.out.println("in catch...");
            makeEntryForReport(requestId, filepath, "Failed", saveDirectory);
            e.printStackTrace();
        } finally {
            colName = null;  // Clean up resources
        }
    }

    private void makeEntryForReport(Long requestId, String filepath, String status, String saveDirectory) {
        LtMastReportRequest ltMastReportRequest = ltMastReportRequestRepository.findOne(requestId);
        ltMastReportRequest.setCompletedDate(new Date());
        ltMastReportRequest.setFilePath(saveDirectory + filepath);
        ltMastReportRequest.setStatus(status);
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
     // 1. Invoice Number
        Cell c = row.createCell(0);
        c.setCellStyle(style);
        if (reportField.getInvoiceNumber() != null) c.setCellValue(reportField.getInvoiceNumber());

        // 2. Internal Invoice Number
        c = row.createCell(1);
        c.setCellStyle(style);
        if (reportField.getInternalInvoiceNumber() != null) c.setCellValue(reportField.getInternalInvoiceNumber());

        // 3. Invoice Type
        c = row.createCell(2);
        c.setCellStyle(style);
        if (reportField.getInvoiceType() != null) c.setCellValue(reportField.getInvoiceType());
        
        // 2. Invoice date
        c = row.createCell(3);
        c.setCellStyle(style);
        if (reportField.getInvoiceDate() != null) c.setCellValue(reportField.getInvoiceDate());

        // 3. Invoice received date
        c = row.createCell(4);
        c.setCellStyle(style);
        if (reportField.getInvoiceReceivedDate() != null) c.setCellValue(reportField.getInvoiceReceivedDate());

        // 4. Description
        c = row.createCell(5);
        c.setCellStyle(style);
        if (reportField.getDescription() != null) c.setCellValue(reportField.getDescription());
        // 17. Status
        c = row.createCell(6);
        c.setCellStyle(style);
        if (reportField.getStatus() != null) c.setCellValue(reportField.getStatus());

        // 5. Initiator
        c = row.createCell(7);
        c.setCellStyle(style);
        if (reportField.getInitiator() != null) c.setCellValue(reportField.getInitiator());

        // 6. Buyer
        c = row.createCell(8);
        c.setCellStyle(style);
        if (reportField.getBuyer() != null) c.setCellValue(reportField.getBuyer());

        // 7. Vendor Name
        c = row.createCell(9);
        c.setCellStyle(style);
        if (reportField.getVendorName() != null) c.setCellValue(reportField.getVendorName());

        // 8. Address
        c = row.createCell(10);
        c.setCellStyle(style);
        if (reportField.getAddress() != null) c.setCellValue(reportField.getAddress());

        // 9. Division
        c = row.createCell(11);
        c.setCellStyle(style);
        if (reportField.getDivision() != null) c.setCellValue(reportField.getDivision());

        // 10. PO Number
        c = row.createCell(12);
        c.setCellStyle(style);
        if (reportField.getPoNumber() != null) c.setCellValue(reportField.getPoNumber());

        // 11. PO Amount
        c = row.createCell(13);
        c.setCellStyle(style);
        if (reportField.getPoAmount() != null) c.setCellValue(reportField.getPoAmount().toString());

        // 12. Billing Address
        c = row.createCell(14);
        c.setCellStyle(style);
        if (reportField.getBillingAddress() != null) c.setCellValue(reportField.getBillingAddress());

        // 13. Shipping Address
        c = row.createCell(15);
        c.setCellStyle(style);
        if (reportField.getShippingAddress() != null) c.setCellValue(reportField.getShippingAddress());

        // 14. Invoice Currency
        c = row.createCell(16);
        c.setCellStyle(style);
        if (reportField.getInvoiceCurrency() != null) c.setCellValue(reportField.getInvoiceCurrency());

        // 15. Exchange Rate
        c = row.createCell(17);
        c.setCellStyle(style);
        if (reportField.getExchangeRate() != null) c.setCellValue(reportField.getExchangeRate());

        // 16. Invoice Amount
        c = row.createCell(18);
        c.setCellStyle(style);
        if (reportField.getInvoiceAmount() != null) c.setCellValue(reportField.getInvoiceAmount().toString());

        // 18. Line Number
        c = row.createCell(19);
        c.setCellStyle(style);
        if (reportField.getLineNo() != null) c.setCellValue(reportField.getLineNo());

        // 19. Invoice Quantity
        c = row.createCell(20);
        c.setCellStyle(style);
        if (reportField.getInvoiceQty() != 0) c.setCellValue(reportField.getInvoiceQty());

        // 20. Invoice Rate
        c = row.createCell(21);
        c.setCellStyle(style);
        if (reportField.getInvoiceRate() != null) c.setCellValue(reportField.getInvoiceRate());

        // 21. Base Amount
        c = row.createCell(22);
        c.setCellStyle(style);
        if (reportField.getBaseAmount() != null) c.setCellValue(reportField.getBaseAmount().toString());

        // 22. Tax Amount
        c = row.createCell(23);
        c.setCellStyle(style);
        if (reportField.getTaxAmount() != null) c.setCellValue(reportField.getTaxAmount().toString());

        // 23. Total Amount
        c = row.createCell(24);
        c.setCellStyle(style);
        if (reportField.getTotalAmount() != null) c.setCellValue(reportField.getTotalAmount().toString());

        // 24. Terms
        c = row.createCell(25);
        c.setCellStyle(style);
        if (reportField.getTerms() != null) c.setCellValue(reportField.getTerms());
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
