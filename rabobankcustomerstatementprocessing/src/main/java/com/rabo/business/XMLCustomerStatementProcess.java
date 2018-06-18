package com.rabo.business;

import com.rabo.exception.XMLProcessingException;
import com.rabo.model.Record;
import com.rabo.model.Records;
import com.rabo.utils.CommonUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

/**
 * XMLCustomer Statement Process is responsible for processing the data from XML file.
 *
 * @author Maheswaran Venkatraman
 * @date 18 June 2018
 */

@Component
public class XMLCustomerStatementProcess {

    public Pair<Set<Record>, List<Record>> execute(String xmlFile) throws XMLProcessingException {
        Records customerStatementRecords = new Records();
        try(final InputStream is = new FileInputStream(new File(xmlFile))) {
            JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            customerStatementRecords = (Records) jaxbUnmarshaller.unmarshal(is);
        } catch (JAXBException | IOException e) {
            throw new XMLProcessingException("CSV Processing Exception" + e.getMessage());
        }
        return CommonUtils.resultSet(customerStatementRecords.getRecords());
    }
}
