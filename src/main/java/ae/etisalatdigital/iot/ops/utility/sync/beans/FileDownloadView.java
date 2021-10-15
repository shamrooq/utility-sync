/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.swing.text.Document;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author appadmin
 */
@Named
@RequestScoped
public class FileDownloadView {
    private StreamedContent file;

    public FileDownloadView() {
        /*file = DefaultStreamedContent.builder()
                .name("downloaded_boromir.jpg")
                .contentType("image/jpg")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/demo/images/boromir.jpg"))
                .build();
        */
    }

    public StreamedContent getFile() {
        return file;
    }
    
    public StreamedContent getFileEx() {
        try {
             ByteArrayOutputStream os = new ByteArrayOutputStream();
             
             /*
             Document document = new Document(PageSize.LETTER);
             PdfWriter pdfWriter = PdfWriter.getInstance(document, os);
             document.open();
             document.addAuthor("BRASPRESS TRANSPORTES URGENTES");
             document.addCreator("BRASPRESS TRANSPORTES URGENTES");
             document.addSubject("Texto Proposta");
             document.addCreationDate();
             document.addTitle(bean.getNome());

             HTMLWorker htmlWorker = new HTMLWorker(document);
             String str = "<html><head></head><body>"+ bean.getTexto() +"</body></html>";
             htmlWorker.parse(new StringReader(str));
             document.close();

             InputStream is = new ByteArrayInputStream(os.toByteArray())
             */
             //file = new DefaultStreamedContent(is, "application/pdf", "ohyeah.pdf");
             return file;
          }
          catch (Exception e) {
            return null;
          }
    }
}
