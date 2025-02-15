package com.lonar.vendor.vendorportal.requests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.model.LtMastReportRequest;
import com.lonar.vendor.vendorportal.model.ServiceException;

public class VendorThread extends Thread {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");

    private Long requestId;
    private LtMastReportRequestRepo ltMastReportRequestRepository;
    private List<String> colName;
    private List<RequestField> requestData;
    private String saveDirectory;

    public VendorThread(Long requestId, String saveDirectory,
                        List<RequestField> requestData, LtMastReportRequestRepo ltMastReportRequestRepository) {
        this.requestId = requestId;
        this.colName = colName;
        this.requestData = requestData;
        this.saveDirectory = saveDirectory.endsWith("/") ? saveDirectory : saveDirectory + "/";
        this.ltMastReportRequestRepository = ltMastReportRequestRepository;
    }

    @Override
    public void run() {
        String filepath = null;
        try {
            if (requestData != null && !requestData.isEmpty()) {
                filepath = createCSV("PozSuppliersInt", saveDirectory);
            }
            makeEntryForReport(requestId, filepath, "Success", "COMPLETED");
        } catch (Exception e) {
            makeEntryForReport(requestId, filepath, "Error", "COMPLETED");
            e.printStackTrace();
        }
    }

    private void makeEntryForReport(Long requestId, String filepath, String status, String phase) {
        LtMastReportRequest ltMastReportRequest = ltMastReportRequestRepository.findOne(requestId);
        if (ltMastReportRequest != null) {
            ltMastReportRequest.setCompletedDate(new Date());
            ltMastReportRequest.setFilePath(filepath != null ? saveDirectory + filepath : null);
            ltMastReportRequest.setStatus(status);
            ltMastReportRequest.setPhase(phase);
            ltMastReportRequest.setFileName(filepath);
            ltMastReportRequest.setEndDate(new Date());
            ltMastReportRequestRepository.save(ltMastReportRequest);
        }
    }

    private String createCSV(String reportName, String saveDirectory) throws ServiceException, IOException {
        String fileName = reportName + "_" + TIMESTAMP_FORMAT.format(new Date()) + ".csv";
        String filePath = saveDirectory + fileName;
        File directory = new File(saveDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Writing only data rows (headers are not included)
            for (RequestField requestField : requestData) {
                writer.println(formatCSVRow(requestField));
            }
        }
        return fileName;
    }

    private String formatCSVRow(RequestField requestField) {
        return String.join(",",
        		wrap(requestField.getImportAction()),
        		wrap(requestField.getSupplierName()),
        		wrap(requestField.getSupplierNewName()),
        		wrap(requestField.getSupplierNumber()),
        		wrap(requestField.getAlternateName()),
        		wrap(requestField.getTaxOrganizationType()),
        		wrap(requestField.getSupplierType()),
        		wrap(formatDate(requestField.getInactiveDate())),
        		wrap(requestField.getBusinessRelationship()),
        		wrap(requestField.getParentSupplier()),
        		wrap(requestField.getAlias()),
        		wrap(requestField.getDunsNumber()),
        		wrap(requestField.getOneTimeSupplier()),
        		wrap(requestField.getCustomerNumber()),
        		wrap(requestField.getSic()),
        		wrap(requestField.getNationalInsuranceNumber()),
        		wrap(requestField.getCorporateWebSite()),
        		wrap(requestField.getChiefExecutiveTitle()),
        		wrap(requestField.getChiefExecutiveName()),
        		wrap(requestField.getBusinessClassificationsNotApplicable()),
        		wrap(requestField.getTaxpayerCountry()),
        		wrap(requestField.getTaxpayerId()),
        		wrap(requestField.getFederalReportable()),
        		wrap(requestField.getFederalIncomeTaxType()),
        		wrap(requestField.getStateReportable()),
        		wrap(requestField.getTaxReportingName()),
        		wrap(requestField.getNameControl()),
        		wrap(formatDate(requestField.getTaxVerificationDate())),
        		wrap(requestField.getUseWithholdingTax()),
        		wrap(requestField.getWithholdingTaxGroup()),
        		wrap(requestField.getVatCode()),
        		wrap(requestField.getTaxRegistrationNumber()),
        		wrap(requestField.getAutoTaxCalcOverride()),
        		wrap(requestField.getPaymentMethod()),
        		wrap(requestField.getDeliveryChannel()),
        		wrap(requestField.getBankInstruction1()),
        		wrap(requestField.getBankInstruction2()),
        		wrap(requestField.getBankInstruction()),
        		wrap(requestField.getSettlementPriority()),
        		wrap(requestField.getPaymentTextMessage1()),
        		wrap(requestField.getPaymentTextMessage2()),
        		wrap(requestField.getPaymentTextMessage3()),
        		wrap(requestField.getBankChargeBearer()),
        		wrap(requestField.getPaymentReason()),
        		wrap(requestField.getPaymentReasonComments()),
        		wrap(requestField.getPaymentFormat()),
        		wrap(requestField.getAttributeCategory()),
        		wrap(requestField.getAttribute1()),
        		wrap(requestField.getAttribute2()),
        		wrap(requestField.getAttribute3()),
        		wrap(requestField.getAttribute4()),
        		wrap(requestField.getAttribute5()),
        		wrap(requestField.getAttribute6()),
        		wrap(requestField.getAttribute7()),
        		wrap(requestField.getAttribute8()),
        		wrap(requestField.getAttribute9()),
        		wrap(requestField.getAttribute10()),
        		wrap(requestField.getAttribute11()),
        		wrap(requestField.getAttribute12()),
        		wrap(requestField.getAttribute13()),
        		wrap(requestField.getAttribute14()),
        		wrap(requestField.getAttribute15()),
        		wrap(requestField.getAttribute16()),
        		wrap(requestField.getAttribute17()),
        		wrap(requestField.getAttribute18()),
        		wrap(requestField.getAttribute19()),
        		wrap(requestField.getAttribute20()),
        		wrap(formatDate(requestField.getAttributeDate1())),
        		wrap(formatDate(requestField.getAttributeDate2())),
        		wrap(formatDate(requestField.getAttributeDate3())),
        		wrap(formatDate(requestField.getAttributeDate4())),
        		wrap(formatDate(requestField.getAttributeDate5())),
        		wrap(formatDate(requestField.getAttributeDate6())),
        		wrap(formatDate(requestField.getAttributeDate7())),
        		wrap(formatDate(requestField.getAttributeDate8())),
        		wrap(formatDate(requestField.getAttributeDate9())),
        		wrap(formatDate(requestField.getAttributeDate10())),
        		wrap(requestField.getAttributeTimestamp1()),
        		wrap(requestField.getAttributeTimestamp2()),
        		wrap(requestField.getAttributeTimestamp3()),
        		wrap(requestField.getAttributeTimestamp4()),
        		wrap(requestField.getAttributeTimestamp5()),
        		wrap(requestField.getAttributeTimestamp6()),
        		wrap(requestField.getAttributeTimestamp7()),
        		wrap(requestField.getAttributeTimestamp8()),
        		wrap(requestField.getAttributeTimestamp9()),
        		wrap(requestField.getAttributeTimestamp10()),
        		wrap(requestField.getAttributeNumber1()),
        		wrap(requestField.getAttributeNumber2()),
        		wrap(requestField.getAttributeNumber3()),
        		wrap(requestField.getAttributeNumber4()),
        		wrap(requestField.getAttributeNumber5()),
        		wrap(requestField.getAttributeNumber6()),
        		wrap(requestField.getAttributeNumber7()),
        		wrap(requestField.getAttributeNumber8()),
        		wrap(requestField.getAttributeNumber9()),
        		wrap(requestField.getAttributeNumber10()),
        		wrap(requestField.getGlobalAttributeCategory()),
        		wrap(requestField.getGlobalAttribute1()),
        		wrap(requestField.getGlobalAttribute2()),
        		wrap(requestField.getGlobalAttribute3()),
        		wrap(requestField.getGlobalAttribute4()),
        		wrap(requestField.getGlobalAttribute5()),
        		wrap(requestField.getGlobalAttribute6()),
        		wrap(requestField.getGlobalAttribute7()),
        		wrap(requestField.getGlobalAttribute8()),
        		wrap(requestField.getGlobalAttribute9()),
        		wrap(requestField.getGlobalAttribute10()),
        		wrap(requestField.getGlobalAttribute11()),
        		wrap(requestField.getGlobalAttribute12()),
        		wrap(requestField.getGlobalAttribute13()),
        		wrap(requestField.getGlobalAttribute14()),
        		wrap(requestField.getGlobalAttribute15()),
        		wrap(requestField.getGlobalAttribute16()),
        		wrap(requestField.getGlobalAttribute17()),
        		wrap(requestField.getGlobalAttribute18()),
        		wrap(requestField.getGlobalAttribute19()),
        		wrap(requestField.getGlobalAttribute20()),
        		wrap(formatDate(requestField.getGlobalAttributeDate1())),
        		wrap(formatDate(requestField.getGlobalAttributeDate2())),
        		wrap(formatDate(requestField.getGlobalAttributeDate3())),
        		wrap(formatDate(requestField.getGlobalAttributeDate4())),
        		wrap(formatDate(requestField.getGlobalAttributeDate5())),
        		wrap(formatDate(requestField.getGlobalAttributeDate6())),
        		wrap(formatDate(requestField.getGlobalAttributeDate7())),
        		wrap(formatDate(requestField.getGlobalAttributeDate8())),
        		wrap(formatDate(requestField.getGlobalAttributeDate9())),
        		wrap(formatDate(requestField.getGlobalAttributeDate10())),
        		wrap(requestField.getGlobalAttributeTimestamp1()),
        		wrap(requestField.getGlobalAttributeTimestamp2()),
        		wrap(requestField.getGlobalAttributeTimestamp3()),
        		wrap(requestField.getGlobalAttributeTimestamp4()),
        		wrap(requestField.getGlobalAttributeTimestamp5()),
        		wrap(requestField.getGlobalAttributeTimestamp6()),
        		wrap(requestField.getGlobalAttributeTimestamp7()),
        		wrap(requestField.getGlobalAttributeTimestamp8()),
        		wrap(requestField.getGlobalAttributeTimestamp9()),
        		wrap(requestField.getGlobalAttributeTimestamp10()),
        		wrap(requestField.getGlobalAttributeNumber1()),
        		wrap(requestField.getGlobalAttributeNumber2()),
        		wrap(requestField.getGlobalAttributeNumber3()),
        		wrap(requestField.getGlobalAttributeNumber4()),
        		wrap(requestField.getGlobalAttributeNumber5()),
        		wrap(requestField.getGlobalAttributeNumber6()),
        		wrap(requestField.getGlobalAttributeNumber7()),
        		wrap(requestField.getGlobalAttributeNumber8()),
        		wrap(requestField.getGlobalAttributeNumber9()),
        		wrap(requestField.getGlobalAttributeNumber10()),
        		wrap(requestField.getBatchId()),
        		wrap(requestField.getRegistryId()),
        		
        		wrap(requestField.getPayeeServiceLevel()),
        		wrap(requestField.getPayEachDocumentAlone()),
        		wrap(requestField.getDeliveryMethod()),
        		wrap(requestField.getRemittanceEmail()),
        		wrap(requestField.getRemittanceFax()),
        		wrap(requestField.getDataFoxId()),
        		wrap(requestField.getEndMarker())
);
    }

    private String wrap(String value) {
        return value == null || value.isEmpty() ? "" : "\"" + value.replace("\"", "\"\"") + "\"";
    }

    private String formatDate(Date date) {
        return date == null ? "" : DATE_FORMAT.format(date);
    }
}
