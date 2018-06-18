package com.rabo.service;

import static com.rabo.utils.CommonUtils.CSV_SEPARATOR;

import com.rabo.model.Record;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Customer Statement Error Report Service is responsible for writing the error data to CSV file.
 *
 * @author Maheswaran Venkatraman
 * @date 18 June 2018
 */

@Service
public class CustomerStatementErrorReportService {

    public void writeToCSV(List<Record> recordList, String errorDataProcessingFilePath) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(errorDataProcessingFilePath)))) {
            StringBuilder headings = new StringBuilder();
            headings.append("Reference");
            headings.append(CSV_SEPARATOR);
            headings.append("Description");
            bw.write(headings.toString());
            bw.newLine();
            for (Record record : recordList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(record.getReference());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(record.getDescription());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
