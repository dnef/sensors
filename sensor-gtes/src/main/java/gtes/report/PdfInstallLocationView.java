package gtes.report;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import gtes.entity.Sensor;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


public class PdfInstallLocationView extends AbstractPdfView {
    @Override
    protected Document newDocument() {
        return new Document(PageSize.A4.rotate());
    }
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        @SuppressWarnings("unchecked")
        List<Sensor> installList = (List<Sensor>) model.get("installList");
//        document.setPageSize(PageSize.A4.rotate());
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"location_sensor.pdf\"");
        //httpServletResponse.setCharacterEncoding("UTF-8");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        BaseFont baseFont = BaseFont.createFont("fonts/arial.ttf",BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
        Font font2 = new Font(baseFont, 16, Font.BOLD);
        Paragraph paragraph = new Paragraph("Список средств измерений на установке: "+installList.get(0).getLocationByLocationId().getNameLoc(),font2);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(25f);
        document.add(paragraph);
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{2, 4, 2, 2, 1, 2});
        Font font = new Font(baseFont, 12, Font.NORMAL);
        PdfPCell header1 = new PdfPCell(new Phrase("Установка",font));
        PdfPCell header2 = new PdfPCell(new Phrase("Средство измерения",font));
        PdfPCell header3 = new PdfPCell(new Phrase("Зав.номер",font));
        PdfPCell header4 = new PdfPCell(new Phrase("Метрология",font));
        PdfPCell header5 = new PdfPCell(new Phrase("МПИ",font));
        PdfPCell header6 = new PdfPCell(new Phrase("Дата след. поверки",font));
        header1.setHorizontalAlignment(Element.ALIGN_CENTER);
        header2.setHorizontalAlignment(Element.ALIGN_CENTER);
        header3.setHorizontalAlignment(Element.ALIGN_CENTER);
        header4.setHorizontalAlignment(Element.ALIGN_CENTER);
        header5.setHorizontalAlignment(Element.ALIGN_CENTER);
        header6.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);
        table.addCell(header4);
        table.addCell(header5);
        table.addCell(header6);
        for (Sensor sensor:installList){
            PdfPCell loc = new PdfPCell(new Phrase(sensor.getLocationByLocationId().getNameLoc(),font));
            PdfPCell sens = new PdfPCell(new Phrase(sensor.getModelSensorByModelId().getTypesensByTypesensId().getNameType()+" "
                    + sensor.getModelSensorByModelId().getModelName()+" "
                    +sensor.getModelSensorByModelId().getModelVersion()+" "+sensor.getRangeMin()+"..."+sensor.getRangeMax()+" "
                    +sensor.getUnitByUnitId().getUnitName(),font));
            PdfPCell number = new PdfPCell(new Phrase(sensor.getSensorNumb(),font));
            PdfPCell verification = new PdfPCell(new Phrase(sensor.getVerification()?"Поверка "+sensor.getDateVerification().format(formatter):"Калибровка "+sensor.getDateVerification().format(formatter),font));
            PdfPCell interval = new PdfPCell(new Phrase(sensor.getIntervalVerification().toString(),font));
            PdfPCell dateNextVerification = new PdfPCell(new Phrase(sensor.getDateVerification().plusMonths(sensor.getIntervalVerification())
                    .format(formatter),font));
            loc.setHorizontalAlignment(Element.ALIGN_CENTER);
            sens.setHorizontalAlignment(Element.ALIGN_CENTER);
            number.setHorizontalAlignment(Element.ALIGN_CENTER);
            verification.setHorizontalAlignment(Element.ALIGN_CENTER);
            interval.setHorizontalAlignment(Element.ALIGN_CENTER);
            dateNextVerification.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(loc);
            table.addCell(sens);
            table.addCell(number);
            table.addCell(verification);
            table.addCell(interval);
            table.addCell(dateNextVerification);
        }
//срабатывает только со второй страницы
//        document.setPageSize(PageSize.A4.rotate());
        document.add(table);


    }
}
