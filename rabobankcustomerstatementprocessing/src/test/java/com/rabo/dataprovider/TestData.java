package com.rabo.dataprovider;

import com.rabo.model.Record;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class TestData {

    public static Pair<Set<Record>, List<Record>> getSetListPair() {
        List<Record> customerStatementRecords = new ArrayList<>();
        Record recordOne = new Record();
        recordOne.setReference("1");
        recordOne.setAccountNumber("AC1");
        recordOne.setDescription("Desc1");
        recordOne.setStartBalance("20");
        recordOne.setMutation("15");
        recordOne.setEndBalance("5");

        Record recordTwo = new Record();
        recordTwo.setReference("2");
        recordTwo.setAccountNumber("AC2");
        recordTwo.setDescription("Desc2");
        recordTwo.setStartBalance("25");
        recordTwo.setMutation("15");
        recordTwo.setEndBalance("10");

        Record recordThree = new Record();
        recordThree.setReference("2");
        recordThree.setAccountNumber("AC3");
        recordThree.setDescription("Desc3");
        recordThree.setStartBalance("29");
        recordThree.setMutation("13");
        recordThree.setEndBalance("16");

        customerStatementRecords.add(recordOne);
        customerStatementRecords.add(recordTwo);
        customerStatementRecords.add(recordThree);

        Set<Record> recordSet = new HashSet<>();
        recordSet.addAll(customerStatementRecords);

        List<Record> duplicateList = new ArrayList<>();
        duplicateList.add(recordTwo);

        return Pair.of(recordSet,duplicateList);
    }
}
