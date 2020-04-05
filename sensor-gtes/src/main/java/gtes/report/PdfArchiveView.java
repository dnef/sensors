package gtes.report;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import gtes.entity.Archive;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


public class PdfArchiveView extends AbstractPdfView {
    @Override
    protected Document newDocument() {
        return new Document(PageSize.A4.rotate());
    }
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        @SuppressWarnings("unchecked")
        List<Archive> archiveList = (List<Archive>) model.get("archiveList");
//        document.setPageSize(PageSize.A4.rotate());
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"archive_sensor.pdf\"");
        //httpServletResponse.setCharacterEncoding("UTF-8");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        BaseFont baseFont = BaseFont.createFont("fonts/arial.ttf",BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
        Font font2 = new Font(baseFont, 16, Font.BOLD);
        Paragraph paragraph = new Paragraph("Архив перемещений датчиков",font2);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(25f);
        document.add(paragraph);
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{2, 3, 2, 2, 4});
        Font font = new Font(baseFont, 12, Font.NORMAL);
        PdfPCell header1 = new PdfPCell(new Phrase("Установка",font));
        PdfPCell header2 = new PdfPCell(new Phrase("Тип сенсора",font));
        PdfPCell header3 = new PdfPCell(new Phrase("Серийный номер",font));
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
        for (Archive archive:archiveList){
            PdfPCell loc = new PdfPCell(new Phrase(archive.getLocationByLocationId().getNameLoc(),font));
            PdfPCell sens = new PdfPCell(new Phrase(archive.getSensorBySensorId().getModelSensorByModelId().getTypesensByTypesensId().getNameType()+" "
                    + archive.getSensorBySensorId().getModelSensorByModelId().getModelName()+" "
                    +archive.getSensorBySensorId().getModelSensorByModelId().getModelVersion(),font));
            PdfPCell number = new PdfPCell(new Phrase(archive.getSensorBySensorId().getSensorNumb(),font));
            PdfPCell date = new PdfPCell(new Phrase(archive.getInstallDate().format(formatter),font));
            PdfPCell note = new PdfPCell(new Phrase(archive.getNote(),font));
            table.addCell(loc);
            table.addCell(sens);
            table.addCell(number);
            table.addCell(date);
            table.addCell(note);
        }
//срабатывает только со второй страницы
//        document.setPageSize(PageSize.A4.rotate());
        document.add(table);


    }
}
