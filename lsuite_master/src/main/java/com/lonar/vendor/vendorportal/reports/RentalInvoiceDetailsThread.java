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

public class RentalInvoiceDetailsThread extends Thread {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    Long requestId = null;
    LtMastReportRequestRepository ltMastReportRequestRepository;
    List<String> colName = null;
    List<ReportField> reportFields = null;
    String saveDirectory;

    public RentalInvoiceDetailsThread(Long requestId, List<String> colName, String saveDirectory,
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
                filepath = createExcel(colName, "RentalInvoiceDetailsReport", saveDirectory, reportFields);
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
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
         // Start row number
           // Create cells sequentially with unique column indices
            Cell c = row.createCell(0);  // Column 0 (Invoice Number)
            c.setCellStyle(style);
            c.setCellValue(reportField.getInvoiceNumber());

            c = row.createCell(1);  // Column 1 (Internal Invoice Number)
            c.setCellStyle(style);
            c.setCellValue(reportField.getInternalInvoiceNumber());

            c = row.createCell(2);  // Column 2 (Invoice Type)
            c.setCellStyle(style);
            c.setCellValue(reportField.getInvoiceType());

            c = row.createCell(3);  // Column 3 (Invoice Date)
            c.setCellStyle(style);
            c.setCellValue(reportField.getInvoiceDate());

            c = row.createCell(4);  // Column 4 (Description)
            c.setCellStyle(style);
            c.setCellValue(reportField.getDescription());

            c = row.createCell(5);  // Column 5 (Status)
            c.setCellStyle(style);
            c.setCellValue(reportField.getStatus());

            c = row.createCell(6);  // Column 6 (Initiator)
            c.setCellStyle(style);
            c.setCellValue(reportField.getInitiator());

            c = row.createCell(7);  // Column 7 (Vendor Name)
            c.setCellStyle(style);
            c.setCellValue(reportField.getVendorName());

            c = row.createCell(8);  // Column 8 (Address)
            c.setCellStyle(style);
            c.setCellValue(reportField.getAddress());

            c = row.createCell(9);  // Column 9 (Division)
            c.setCellStyle(style);
            c.setCellValue(reportField.getDivision());

            c = row.createCell(10); // Column 10 (Agreement Number)
            c.setCellStyle(style);
            c.setCellValue(reportField.getAgreementNumber());

            c = row.createCell(11); // Column 11 (Source)
            c.setCellStyle(style);
            c.setCellValue(reportField.getSource());

            c = row.createCell(12); // Column 12 (Source Ref Number)
            c.setCellStyle(style);
            c.setCellValue(reportField.getSourceRefNumber());

            c = row.createCell(13); // Column 13 (Billing Address)
            c.setCellStyle(style);
            c.setCellValue(reportField.getBillingAddress());

            c = row.createCell(14); // Column 14 (Shipping Address)
            c.setCellStyle(style);
            c.setCellValue(reportField.getShippingAddress());

            c = row.createCell(15); // Column 15 (Invoice Currency)
            c.setCellStyle(style);
            c.setCellValue(reportField.getInvoiceCurrency());

            c = row.createCell(16); // Column 16 (Exchange Rate)
            c.setCellStyle(style);
            c.setCellValue(reportField.getExchangeRate());


            // 16. Invoice Amount
            c = row.createCell(17);
            c.setCellStyle(style);
            if (reportField.getInvoiceAmount() != null) c.setCellValue(reportField.getInvoiceAmount().toString());

            c = row.createCell(18); // Column 18 (Line Number)
            c.setCellStyle(style);
            c.setCellValue(reportField.getLineNo());

            c = row.createCell(19); // Column 19 (Invoice Quantity)
            c.setCellStyle(style);
            c.setCellValue(reportField.getInvoiceQty());

            c = row.createCell(20); // Column 20 (Invoice Rate)
            c.setCellStyle(style);
            c.setCellValue(reportField.getInvoiceRate());


            // 16. Invoice Amount
            c = row.createCell(21);
            c.setCellStyle(style);
            if (reportField.getBaseAmount() != null) c.setCellValue(reportField.getBaseAmount().toString());   
            
            c = row.createCell(22); // Column 22 (Tax Amount)
            c.setCellStyle(style);
            c.setCellValue(reportField.getTaxAmount());

            c = row.createCell(23); // Column 23 (Total Amount)
            c.setCellStyle(style);
            c.setCellValue(reportField.getTotalAmount());

            c = row.createCell(24); // Column 24 (Terms)
            c.setCellStyle(style);
            c.setCellValue(reportField.getTerms());
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
