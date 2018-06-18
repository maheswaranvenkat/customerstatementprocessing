package com.rabo;

import com.rabo.business.CSVCustomerStatementProcess;
import com.rabo.exception.CSVProcessingException;
import com.rabo.exception.CustomerStatementProcessException;
import com.rabo.exception.XMLProcessingException;
import com.rabo.model.FileExtensionFilter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.rabo.controller.CustomerStatementUploadController;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.test.context.TestPropertySource;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import static org.powermock.api.support.membermodification.MemberMatcher.method;


@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(properties = {
        "xmlFileName.path=./upload/records.xml",
})
@PrepareForTest({FileExtensionFilter.class})
public class AppTest {

    private CustomerStatementUploadController customerStatementUploadController;

    @Mock
    private CSVCustomerStatementProcess csvCustomerStatementProcess;

    /*@Before
    public void setUp() {
        fileExtensionFilter = mock(FileExtensionFilter.class);
    }*/

    @Test
    public void testApp() throws CustomerStatementProcessException, CSVProcessingException, XMLProcessingException, Exception {

        //Mockito.when(FileExtensionFilter.fromString("./upload/records.xml")).thenReturn(FileExtensionFilter.XML);
        //whenNew(FileExtensionFilter.class).withNoArguments().thenReturn(fileExtensionFilter);
        //stub(method(FileExtensionFilter.class, "fromString()").toReturn(FileExtensionFilter.XML);
        //stub(method(FileExtensionFilter.class, "fromString")).toReturn(FileExtensionFilter.XML);


        customerStatementUploadController = Mockito.mock(CustomerStatementUploadController.class, Mockito.CALLS_REAL_METHODS);

        String result = customerStatementUploadController.invoke();
        assertEquals(result, "CSV Processed Successfully!!! Please check the Report.csv file is there any errors. "
                + "You Can find the file in following location Upload location");
    }
}
