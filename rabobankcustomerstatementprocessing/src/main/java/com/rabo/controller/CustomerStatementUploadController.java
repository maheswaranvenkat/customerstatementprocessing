package com.rabo.controller;

import com.rabo.business.CSVCustomerStatementProcess;
import com.rabo.business.XMLCustomerStatementProcess;
import com.rabo.exception.CSVProcessingException;
import com.rabo.exception.CustomerStatementProcessException;
import com.rabo.exception.XMLProcessingException;
import com.rabo.model.FileExtensionFilter;
import com.rabo.model.Record;
import com.rabo.service.CustomerStatementErrorReportService;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Upload Controller is responsible for routing the request to respective Process class and get the output.
 *
 * @author Maheswaran Venkatraman
 * @date 18 June 2018
 */

@RestController
public class CustomerStatementUploadController {

    @Autowired
    CSVCustomerStatementProcess csvCustomerStatementProcess;

    @Autowired
    XMLCustomerStatementProcess xmlCustomerStatementProcess;

    @Autowired
    CustomerStatementErrorReportService customerStatementErrorReportService;

    @Value("${csvFileName.path}")
    private String csvfileNamePath;

    @Value("${xmlFileName.path}")
    private String xmlfileNamePath;

    @Value("${errorFileName.path}")
    private String errorDataProcessingFilePath;

    @RequestMapping("/")
    public String invoke() throws CustomerStatementProcessException, CSVProcessingException, XMLProcessingException {
        FileExtensionFilter extensionType = FileExtensionFilter.fromString(FilenameUtils.getExtension(csvfileNamePath));
        switch (extensionType) {
            case CSV: {
                //Thought to have CustomerStatementProvider from where we can get the proper fileName.
                Pair<Set<Record>, List<Record>> csvProcessed = csvCustomerStatementProcess.execute(csvfileNamePath);
                customerStatementErrorReportService.writeToCSV(csvProcessed.getRight(), errorDataProcessingFilePath);
                return "CSV Processed Successfully!!! Please check the Report.csv file is there any errors."
                        + " You Can find the file in following location Upload location";
            }
            case XML: {
                Pair<Set<Record>, List<Record>> xmlProcessed = xmlCustomerStatementProcess.execute(xmlfileNamePath);
                customerStatementErrorReportService.writeToCSV(xmlProcessed.getRight(), errorDataProcessingFilePath);
                return "XML Processed Successfully!!! Please check the Report.csv file is there any errors. "
                        + "You Can find the file in following location Upload location";
            }
            default: {
                throw new CustomerStatementProcessException("Customer Doesn't select any related file");
            }
        }
    }
}
