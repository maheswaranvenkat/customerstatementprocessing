package com.rabo.business;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.stub;

import com.rabo.dataprovider.TestData;
import com.rabo.exception.CSVProcessingException;
import com.rabo.model.Record;
import com.rabo.utils.CommonUtils;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class CSVCustomerStatementProcessTest {

    @InjectMocks
    private CSVCustomerStatementProcess csvCustomerStatementProcess;
    private CommonUtils commonUtils;

    @Test
    public void testExecute() throws CSVProcessingException {
        Pair<Set<Record>, List<Record>> resultSet = TestData.getSetListPair();
        commonUtils = Mockito.mock(CommonUtils.class, Mockito.CALLS_REAL_METHODS);
        stub(method(CommonUtils.class, "resultSet")).toReturn(resultSet);
        assertEquals(resultSet, csvCustomerStatementProcess.execute("./upload/records-test.csv"));
     }

}
