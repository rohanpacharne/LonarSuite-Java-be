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

public class RentalAgreementExpiryThread extends Thread {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    Long requestId = null;
    LtMastReportRequestRepository ltMastReportRequestRepository;
    List<String> colName = null;
    List<ReportField> reportFields = null;
    String saveDirectory;

    public RentalAgreementExpiryThread(Long requestId, List<String> colName, String saveDirectory,
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
                filepath = createExcel(colName, "RentalAgreementExpiryReport", saveDirectory, reportFields);
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
        // Assuming you have a row object (row) and style object (style) already defined
        Cell c;

        // 1. Agreement Number
        c = row.createCell(0);
        c.setCellStyle(style);
        if (reportField.getAgreementNumber() != null) c.setCellValue(reportField.getAgreementNumber());

        // 2. Initiator
        c = row.createCell(1);
        c.setCellStyle(style);
        if (reportField.getInitiator() != null) c.setCellValue(reportField.getInitiator());

        // 3. Vendor Name
        c = row.createCell(2);
        c.setCellStyle(style);
        if (reportField.getVendorName() != null) c.setCellValue(reportField.getVendorName());

        // 4. Address
        c = row.createCell(3);
        c.setCellStyle(style);
        if (reportField.getAddress() != null) c.setCellValue(reportField.getAddress());

        // 5. Division Name
        c = row.createCell(4);
        c.setCellStyle(style);
        if (reportField.getDivisionName() != null) c.setCellValue(reportField.getDivisionName());

        // 6. Rental Type
        c = row.createCell(5);
        c.setCellStyle(style);
        if (reportField.getRentalType() != null) c.setCellValue(reportField.getRentalType());

        // 7. Rent Frequency
        c = row.createCell(6);
        c.setCellStyle(style);
        if (reportField.getRentFrequency() != null) c.setCellValue(reportField.getRentFrequency());

        // 8. Rent Frequency Value
        c = row.createCell(7);
        c.setCellStyle(style);
        if (reportField.getRentFrequencyValue() != null) c.setCellValue(reportField.getRentFrequencyValue());

        // 9. Rent Currency
        c = row.createCell(8);
        c.setCellStyle(style);
        if (reportField.getRentCurrency() != null) c.setCellValue(reportField.getRentCurrency());

        // 10. Base Rent
        c = row.createCell(9);
        c.setCellStyle(style);
        if (reportField.getBaseRent() != 0) c.setCellValue(reportField.getBaseRent());

        // 11. Property Title
        c = row.createCell(10);
        c.setCellStyle(style);
        if (reportField.getPropertyTitle() != null) c.setCellValue(reportField.getPropertyTitle());

        // 12. Property Type
        c = row.createCell(11);
        c.setCellStyle(style);
        if (reportField.getPropertyType() != null) c.setCellValue(reportField.getPropertyType());

        // 13. Property City
        c = row.createCell(12);
        c.setCellStyle(style);
        if (reportField.getPropertyCity() != null) c.setCellValue(reportField.getPropertyCity());

        // 14. Property Address
        c = row.createCell(13);
        c.setCellStyle(style);
        if (reportField.getPropertyAddress() != null) c.setCellValue(reportField.getPropertyAddress());

        // 15. State
        c = row.createCell(14);
        c.setCellStyle(style);
        if (reportField.getState() != null) c.setCellValue(reportField.getState());

        // 16. From Date
        c = row.createCell(15);
        c.setCellStyle(style);
        if (reportField.getFromDate() != null) c.setCellValue(reportField.getFromDate().toString());

        // 17. To Date
        c = row.createCell(16);
        c.setCellStyle(style);
        if (reportField.getToDate() != null) c.setCellValue(reportField.getToDate().toString());

        // 18. Location Code
        c = row.createCell(17);
        c.setCellStyle(style);
        if (reportField.getLocationCode() != null) c.setCellValue(reportField.getLocationCode());

        // 19. Billing Address
        c = row.createCell(18);
        c.setCellStyle(style);
        if (reportField.getBillingAddress() != null) c.setCellValue(reportField.getBillingAddress());

        // 20. Security Deposit
        c = row.createCell(19);
        c.setCellStyle(style);
        if (reportField.getSecurityDeposit() != 0) c.setCellValue(reportField.getSecurityDeposit());

        // 21. SD Payment Ref
        c = row.createCell(20);
        c.setCellStyle(style);
        if (reportField.getSdPaymentRef() != null) c.setCellValue(reportField.getSdPaymentRef());

        // 22. SD Payment Date
        c = row.createCell(21);
        c.setCellStyle(style);
        if (reportField.getSdPaymentDate() != null) c.setCellValue(reportField.getSdPaymentDate().toString());

        // 23. Notice Period
        c = row.createCell(22);
        c.setCellStyle(style);
        if (reportField.getNoticePeriod() != 0) c.setCellValue(reportField.getNoticePeriod());

        // 24. Rent Due Day
        c = row.createCell(23);
        c.setCellStyle(style);
        if (reportField.getRentDueDay() !=0) c.setCellValue(reportField.getRentDueDay());

        // 25. Lock-In Period
        c = row.createCell(24);
        c.setCellStyle(style);
        if (reportField.getLockInPeriod() != 0) c.setCellValue(reportField.getLockInPeriod());

        // 26. Termination Date
        c = row.createCell(25);
        c.setCellStyle(style);
        if (reportField.getTerminationDate() != null) c.setCellValue(reportField.getTerminationDate().toString());

        // 27. Status
        c = row.createCell(26);
        c.setCellStyle(style);
        if (reportField.getStatus() != null) c.setCellValue(reportField.getStatus());

        // 28. Line Number
        c = row.createCell(27);
        c.setCellStyle(style);
        if (reportField.getLineNo() != null) c.setCellValue(reportField.getLineNo());

        // 29. Line From Date
        c = row.createCell(28);
        c.setCellStyle(style);
        if (reportField.getLineFromDate() != null) c.setCellValue(reportField.getLineFromDate().toString());

        // 30. Line To Date
        c = row.createCell(29);
        c.setCellStyle(style);
        if (reportField.getLineToDate() != null) c.setCellValue(reportField.getLineToDate().toString());

        // 31. Line Base Rent
        c = row.createCell(30);
        c.setCellStyle(style);
        if (reportField.getLineBaseRent() != 0) c.setCellValue(reportField.getLineBaseRent());

        // 32. Rent Escalation Percentage
        c = row.createCell(31);
        c.setCellStyle(style);
        if (reportField.getRentEscalationPercentage() != null) c.setCellValue(reportField.getRentEscalationPercentage().toString());

        // 33. Rent Amount
        c = row.createCell(32);
        c.setCellStyle(style);
        if (reportField.getRentAmount() != 0) c.setCellValue(reportField.getRentAmount());

        // 34. Tax Amount
        c = row.createCell(33);
        c.setCellStyle(style);
        if (reportField.getTaxAmount() != null) c.setCellValue(reportField.getTaxAmount().toString());

        // 35. Total Amount
        c = row.createCell(34);
        c.setCellStyle(style);
        if (reportField.getTotalAmount() != null) c.setCellValue(reportField.getTotalAmount().toString());

        // 36. Remark
        c = row.createCell(35);
        c.setCellStyle(style);
        if (reportField.getRemark() != null) c.setCellValue(reportField.getRemark());
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
