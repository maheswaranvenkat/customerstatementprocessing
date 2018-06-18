package com.rabo.business;

import static java.util.stream.Collectors.toList;

import com.rabo.exception.CSVProcessingException;
import com.rabo.model.Record;
import com.rabo.utils.CommonUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

/**
 * CSVCustomer Statement Process is responsible for processing the data from CSV file.
 *
 * @author Maheswaran Venkatraman
 * @date 18 June 2018
 */

@Component
public class CSVCustomerStatementProcess {

    public Pair<Set<Record>, List<Record>> execute(String csvFile) throws CSVProcessingException{
        List<Record> customerStatementRecords = new ArrayList<>();
        try (final InputStream is = new FileInputStream(new File(csvFile));
                final BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            customerStatementRecords = br.lines().skip(1)
                    .map(mapToCustomerStatement)
                    .collect(toList());
        } catch (IOException e) {
            throw new CSVProcessingException("CSV Processing Exception" + e.getMessage());
        }
        return CommonUtils.resultSet(customerStatementRecords);
    }

    private static Function<String, Record> mapToCustomerStatement = (line) -> {
        Record record = new Record();
        String[] customerStatementFunction = line.split(CommonUtils.CSV_SEPARATOR);
        record.setReference(customerStatementFunction[0]);
        record.setAccountNumber(customerStatementFunction[1]);
        record.setDescription(customerStatementFunction[2]);
        record.setStartBalance(customerStatementFunction[3]);
        record.setMutation(customerStatementFunction[4]);
        record.setEndBalance(customerStatementFunction[5]);
        return record;
    };
}
