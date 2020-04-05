package gtes.report;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import gtes.entity.Sensor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class PdfSensorView extends AbstractPdfView {
    static final Logger logger = LogManager.getLogger(PdfArchiveView.class.getName());

    @Override
    protected Document newDocument() {
        return new Document(PageSize.A4.rotate());
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        @SuppressWarnings("unchecked")
        List<Sensor> listSensorPdf = (List<Sensor>) model.get("listSensorPdf");
        String filter = model.get("headerFilter").toString().replace("[", "").replace("]", "");
        System.out.println("filter2---" + filter);
//        document.setPageSize(PageSize.A4.rotate());
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"sensorKIP.pdf\"");
        //httpServletResponse.setCharacterEncoding("UTF-8");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        BaseFont baseFont = BaseFont.createFont("fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font16bold = new Font(baseFont, 16, Font.BOLD);
        Font font14norm = new Font(baseFont,14,Font.NORMAL);
        Paragraph paragraph = new Paragraph("Отчет состояния СИ КИП", font16bold);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(15f);
        document.add(paragraph);
        if (!filter.equals("")) {
            Paragraph paragraphFilter = new Paragraph("Примененый фильтр: " + filter, font14norm);
            paragraphFilter.setAlignment(Element.ALIGN_LEFT);
            paragraphFilter.setSpacingAfter(5f);
            document.add(paragraphFilter);
        }
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{2, 3, 2, 2, 3, 2, 2, 2, 3});
        Font font = new Font(baseFont, 12, Font.NORMAL);
        PdfPCell header1 = new PdfPCell(new Phrase("Установка", font));
        PdfPCell header2 = new PdfPCell(new Phrase("Средство измерения", font));
        PdfPCell header3 = new PdfPCell(new Phrase("Зав.номер", font));
        PdfPCell header4 = new PdfPCell(new Phrase("Инв. номер", font));
        PdfPCell header5 = new PdfPCell(new Phrase("Производство", font));
        PdfPCell header6 = new PdfPCell(new Phrase("Дата изготовления", font));
        PdfPCell header7 = new PdfPCell(new Phrase("Метрология", font));
        PdfPCell header8 = new PdfPCell(new Phrase("Дата след. поверки", font));
        PdfPCell header9 = new PdfPCell(new Phrase("Примечание", font));
        header1.setHorizontalAlignment(Element.ALIGN_CENTER);
        header2.setHorizontalAlignment(Element.ALIGN_CENTER);
        header3.setHorizontalAlignment(Element.ALIGN_CENTER);
        header4.setHorizontalAlignment(Element.ALIGN_CENTER);
        header5.setHorizontalAlignment(Element.ALIGN_CENTER);
        header6.setHorizontalAlignment(Element.ALIGN_CENTER);
        header7.setHorizontalAlignment(Element.ALIGN_CENTER);
        header8.setHorizontalAlignment(Element.ALIGN_CENTER);
        header9.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);
        table.addCell(header4);
        table.addCell(header5);
        table.addCell(header6);
        table.addCell(header7);
        table.addCell(header8);
        table.addCell(header9);
        logger.info("header");
        for (Sensor sensor : listSensorPdf) {
            logger.info("for");
            PdfPCell location = new PdfPCell(new Phrase(sensor.getLocationByLocationId().getNameLoc(), font));
            PdfPCell sensorType = new PdfPCell(new Phrase(sensor.getModelSensorByModelId().getTypesensByTypesensId().getNameType() + " "
                    + sensor.getModelSensorByModelId().getModelName() + " "
                    + sensor.getModelSensorByModelId().getModelVersion() + " " + sensor.getRangeMin() + "..." + sensor.getRangeMax() + " "
                    + sensor.getUnitByUnitId().getUnitName(), font));
            PdfPCell numberSer = new PdfPCell(new Phrase(sensor.getSensorNumb(), font));
            logger.info("numberSer");
            PdfPCell numberInv = new PdfPCell(new Phrase(sensor.getInventoryNumb(), font));
            logger.info("numberInv");
            PdfPCell country = new PdfPCell(new Phrase(sensor.getFirmByFirmId().getNameFirm() + " страна: " + sensor.getCountryByCountryId().getCountryName(), font));
            logger.info("country");
            logger.info("date{}", sensor.getDateManufacture());
            PdfPCell dateManufacture = new PdfPCell(new Phrase(sensor.getPassport() ? sensor.getDateManufacture().format(formatter) + " Паспорт в наличии"
                    : sensor.getDateManufacture().format(formatter) + " Паспорт отсутствует", font));
            logger.info("dateManufacture");
            PdfPCell verification = new PdfPCell(new Phrase(sensor.getVerification() ? "Поверка " + sensor.getDateVerification().format(formatter)
                    + " мпи: " + sensor.getIntervalVerification().toString() + " мес."
                    : "Калибровка " + sensor.getDateVerification().format(formatter)
                    + " мпи: " + sensor.getIntervalVerification().toString() + " мес.", font));
            logger.info("verification");
            PdfPCell dateNextVerification = new PdfPCell(new Phrase(sensor.getDateVerification().plusMonths(sensor.getIntervalVerification())
                    .format(formatter), font));
            logger.info("dateNextVerification");
            PdfPCell note = new PdfPCell(new Phrase(sensor.getNote(), font));
            logger.info("note");
            location.setHorizontalAlignment(Element.ALIGN_CENTER);
            sensorType.setHorizontalAlignment(Element.ALIGN_CENTER);
            numberSer.setHorizontalAlignment(Element.ALIGN_CENTER);
            numberInv.setHorizontalAlignment(Element.ALIGN_CENTER);
            country.setHorizontalAlignment(Element.ALIGN_CENTER);
            dateManufacture.setHorizontalAlignment(Element.ALIGN_CENTER);
            verification.setHorizontalAlignment(Element.ALIGN_CENTER);
            dateNextVerification.setHorizontalAlignment(Element.ALIGN_CENTER);
            note.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(location);
            table.addCell(sensorType);
            table.addCell(numberSer);
            table.addCell(numberInv);
            table.addCell(country);
            table.addCell(dateManufacture);
            table.addCell(verification);
            table.addCell(dateNextVerification);
            table.addCell(note);
        }

        document.add(table);

    }
}