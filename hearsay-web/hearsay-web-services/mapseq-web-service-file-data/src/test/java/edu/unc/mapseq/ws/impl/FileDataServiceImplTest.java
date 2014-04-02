package edu.unc.mapseq.ws.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.xml.namespace.QName;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import edu.unc.mapseq.dao.model.FileData;
import edu.unc.mapseq.ws.FileDataService;

public class FileDataServiceImplTest {

    @Test
    public void testDownload() {

        QName serviceQName = new QName("http://ws.mapseq.unc.edu", "FileDataService");
        Service service = Service.create(serviceQName);
        QName portQName = new QName("http://ws.mapseq.unc.edu", "FileDataPort");
        service.addPort(portQName, SOAPBinding.SOAP11HTTP_BINDING,
                String.format("http://%s:%d/cxf/FileDataService", "localhost", 8181));
        FileDataService fileDataService = service.getPort(FileDataService.class);
        Binding binding = ((BindingProvider) service.getPort(portQName, FileDataService.class)).getBinding();
        ((SOAPBinding) binding).setMTOMEnabled(true);
        FileData fileData = fileDataService.findById(128738L);
        DataHandler handler = fileDataService.download(128738L);
        try {
            IOUtils.copyLarge(handler.getInputStream(),
                    new FileOutputStream(new File("/home/jdr0887/tmp", fileData.getName())));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
