package gtes.report;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import gtes.entity.Disposal;
import org.springframework.web.servlet.view.document.AbstractPdfView;
//import sun.awt.windows.WFontConfiguration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


public class PdfDisposalView extends AbstractPdfView {
    @Override
    protected Document newDocument() {
        return new Document(PageSize.A4.rotate());
    }
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        @SuppressWarnings("unchecked")
        List<Disposal> disposalList = (List<Disposal>) model.get("disposalList");
//        document.setPageSize(PageSize.A4.rotate());
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"disposal_sensor.pdf\"");
        //httpServletResponse.setCharacterEncoding("UTF-8");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        BaseFont baseFont = BaseFont.createFont("fonts/arial.ttf",BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
        Font font2 = new Font(baseFont, 16, Font.BOLD);
        Paragraph paragraph = new Paragraph("Архив перемещений удаленных со склада датчиков",font2);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(25f);
        document.add(paragraph);
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{3, 2, 2, 2, 4});
        Font font = new Font(baseFont, 12, Font.NORMAL);
        PdfPCell header1 = new PdfPCell(new Phrase("Средство измерения",font));
        PdfPCell header2 = new PdfPCell(new Phrase("Зав.номер",font));
        PdfPCell header3 = new PdfPCell(new Phrase("Инв.номер",font));
        PdfPCell header4 = new PdfPCell(new Phrase("Дата перемещений",font));
        PdfPCell header5 = new PdfPCell(new Phrase("Примечание",font));
        header1.setHorizontalAlignment(Element.ALIGN_CENTER);
        header2.setHorizontalAlignment(Element.ALIGN_CENTER);
        header3.setHorizontalAlignment(Element.ALIGN_CENTER);
        header4.setHorizontalAlignment(Element.ALIGN_CENTER);
        header5.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);
        table.addCell(header4);
        table.addCell(header5);
        for (Disposal disposal:disposalList){
//            table.addCell(disposal.toString());
            PdfPCell ver = new PdfPCell(new Phrase(disposal.getTypeSensor()+"\n"+disposal.getModelSensor()+"\n"+disposal.getVersionSensor(),font));
            PdfPCell numb = new PdfPCell(new Phrase(disposal.getNumberSensor(),font));
            PdfPCell numvInv = new PdfPCell(new Phrase(disposal.getInventoryNumberSensor(),font));
            PdfPCell date = new PdfPCell(new Phrase(disposal.getDateDisposal().format(formatter),font));
            PdfPCell note = new PdfPCell(new Phrase(disposal.getNote(),font));
            table.addCell(ver);
            table.addCell(numb);
            table.addCell(numvInv);
            table.addCell(date);
            table.addCell(note);
        }
//срабатывает только со второй страницы
//        document.setPageSize(PageSize.A4.rotate());
        document.add(table);


    }
}
