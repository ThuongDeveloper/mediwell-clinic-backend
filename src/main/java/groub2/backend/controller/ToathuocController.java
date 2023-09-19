package groub2.backend.controller;

import groub2.backend.dto.listToathuocDAO;
import groub2.backend.entities.Thuoc;
import groub2.backend.entities.Toathuoc;
import groub2.backend.entities.ToathuocDetails;
import groub2.backend.service.ToathuocDetailsService;
import groub2.backend.service.ToathuocService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/toathuoc")
public class ToathuocController {
    @Autowired
    ToathuocService toathuocService;
    @Autowired
    ToathuocDetailsService toathuocDetailsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Toathuoc> read(){
        return  toathuocService.getAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addDoctor(@RequestBody listToathuocDAO listToathuocDAO) {

        var listobj = listToathuocDAO.getListTT();

        Toathuoc newToathuoc = new Toathuoc();
        newToathuoc.setCreateAt(new Date());
        newToathuoc.setName(listToathuocDAO.getName());
        newToathuoc.setPhone(listToathuocDAO.getPhone());
        newToathuoc.setAddress(listToathuocDAO.getAddress());
        newToathuoc.setSymptom(listToathuocDAO.getSymptom());
        newToathuoc.setDescription(listToathuocDAO.getDescription());
        newToathuoc.setState(listToathuocDAO.isState());

        var modelToathuoc =  toathuocService.saveToathuoc(newToathuoc);


        for(int i = 0 ; i < listobj.size();i++){
            Thuoc newThuoc = new Thuoc();
            newThuoc.setId(listobj.get(i).getThuocID());
            ToathuocDetails newToathuocDetails = new ToathuocDetails();
            newToathuocDetails.setToathuocId(modelToathuoc);
            newToathuocDetails.setChieu(listobj.get(i).getChieu());
            newToathuocDetails.setToi(listobj.get(i).getToi());
            newToathuocDetails.setSang(listobj.get(i).getSang());
            newToathuocDetails.setTrua(listobj.get(i).getTrua());
            newToathuocDetails.setQuantity(listobj.get(i).getQuantity());
            newToathuocDetails.setThuocId(newThuoc);
            toathuocDetailsService.saveToathuoc(newToathuocDetails);
        }

        var a = listToathuocDAO;
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPrescriptionToPDF(@RequestParam("toathuocId") int toathuocId) {
        if (toathuocId <= 0) {
            return ResponseEntity.badRequest().body("Invalid toathuocId".getBytes());
        }

        // Kiểm tra xem toathuocId có tồn tại trong cơ sở dữ liệu không
        Toathuoc toathuoc = toathuocService.getToathuocById(toathuocId);
        if (toathuoc == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Toathuoc not found".getBytes());
        }

        try {
            // Tạo tài liệu PDF và export dữ liệu vào đó
            byte[] pdfBytes = createAndExportPDF(toathuoc);

            // Thiết lập header và trả về file PDF
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "prescription.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(pdfBytes.length)
                    .body(pdfBytes);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private byte[] createAndExportPDF(Toathuoc toathuoc) throws IOException {
        // Tạo một tài liệu PDF mới
        PDDocument document = new PDDocument();

        // Tạo một trang mới cho tài liệu
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        // Tạo một luồng nội dung cho trang
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Tải font Unicode (ví dụ: Arial Unicode MS)
        InputStream fontStream = getClass().getResourceAsStream("/fonts/arial-unicode-ms.ttf");
        PDType0Font font = PDType0Font.load(document, fontStream);

        // Đặt font cho content stream
        contentStream.setFont(font, 12);

        // Thêm tên công ty (góc trên cùng bên trái)
        contentStream.beginText();
        contentStream.newLineAtOffset(50, page.getMediaBox().getHeight() - 20);
        contentStream.showText("MEDIWELL CLINIC");
        contentStream.endText();

        // Thêm Ngày khám (góc trên cùng bên phải)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        String appointmentDate = dateFormat.format(currentDate);
        int textWidth = (int) (font.getStringWidth("Appointment Date: " + appointmentDate) / 1000f * 12); // Tính toán chiều rộng của chuỗi

        contentStream.setFont(font, 10); // Đặt lại font và kích thước cho ngày
        contentStream.beginText();

        // Kiểm tra xem ngày có tràn ra ngoài giới hạn trang không
        if (textWidth > (page.getMediaBox().getWidth() - 100)) {
            // Nếu tràn ra, hãy thay đổi kích thước và vị trí để nó vẫn cân xứng
            contentStream.setFont(font, 8);
            contentStream.newLineAtOffset(page.getMediaBox().getWidth() - textWidth - 50, page.getMediaBox().getHeight() - 20);
        } else {
            contentStream.newLineAtOffset(page.getMediaBox().getWidth() - textWidth - 50, page.getMediaBox().getHeight() - 20);
        }

        contentStream.showText("Appointment Date: " + appointmentDate);
        contentStream.endText();

        // Thêm tiêu đề đơn thuốc (ở giữa)
        contentStream.setFont(font, 16);
        contentStream.beginText();
        contentStream.newLineAtOffset((page.getMediaBox().getWidth() / 2) - 50, page.getMediaBox().getHeight() - 50);
        contentStream.showText("PRESCRIPTION");
        contentStream.endText();

        // Thêm thông tin bệnh nhân
        contentStream.setFont(font, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(50, page.getMediaBox().getHeight() - 80);
        contentStream.showText("Patient Name: " + toathuoc.getName());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Address: " + toathuoc.getAddress());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Symptoms: " + toathuoc.getSymptom());
        contentStream.endText();

        // Thêm đơn thuốc chi tiết
        float marginX = 50;
        float tableY = page.getMediaBox().getHeight() - 170;
        float rowHeight = 20;
        float tableWidth = page.getMediaBox().getWidth() - 2 * marginX;

        // Đặt tiêu đề bảng đơn thuốc chi tiết
        contentStream.setFont(font, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(marginX, tableY);
        contentStream.showText("PRESCRIPTION DETAILS");
        contentStream.endText();

        // Đặt header của bảng đơn thuốc chi tiết
        contentStream.setFont(font, 10);
        contentStream.setLineWidth(1f);
        contentStream.moveTo(marginX, tableY - rowHeight);
        contentStream.lineTo(marginX + tableWidth, tableY - rowHeight);
        contentStream.stroke();
        contentStream.beginText();
        contentStream.newLineAtOffset(marginX, tableY - rowHeight + 4);
        contentStream.showText("Medicine Name");

        // Tính toán khoảng cách đến lề trái của các cột
        float column2X = marginX + 120; // Cột thứ 2
        float column3X = column2X + 60; // Cột thứ 3
        float column4X = column3X + 60; // Cột thứ 4
        float column5X = column4X + 60; // Cột thứ 5
        float column6X = column5X + 60; // Cột thứ 6

        contentStream.newLineAtOffset(column2X - marginX, 0);
        contentStream.showText("Morning");
        contentStream.newLineAtOffset(column3X - column2X, 0);
        contentStream.showText("Noon");
        contentStream.newLineAtOffset(column4X - column3X, 0);
        contentStream.showText("Afternoon");
        contentStream.newLineAtOffset(column5X - column4X, 0);
        contentStream.showText("Evening");
        contentStream.newLineAtOffset(column6X - column5X, 0);
        contentStream.showText("Quantity");

        contentStream.endText();

        // Thêm thông tin đơn thuốc chi tiết (dựa trên dữ liệu thực tế)
        List<ToathuocDetails> medicationDetails = (List<ToathuocDetails>) toathuoc.getToathuocDetailsCollection();
        for (ToathuocDetails detail : medicationDetails) {
            contentStream.setFont(font, 10);
            contentStream.setLineWidth(1f);
            contentStream.moveTo(marginX, tableY - rowHeight * 2);
            contentStream.lineTo(marginX + tableWidth, tableY - rowHeight * 2);
            contentStream.stroke();
            contentStream.beginText();
            contentStream.newLineAtOffset(marginX, tableY - rowHeight * 2 + 4);
            contentStream.showText(detail.getThuocId().getName());
            contentStream.newLineAtOffset(column2X - marginX, 0);
            contentStream.showText(detail.getSang());
            contentStream.newLineAtOffset(column3X - column2X, 0);
            contentStream.showText(detail.getTrua());
            contentStream.newLineAtOffset(column4X - column3X, 0);
            contentStream.showText(detail.getChieu());
            contentStream.newLineAtOffset(column5X - column4X, 0);
            contentStream.showText(detail.getToi());
            contentStream.newLineAtOffset(column6X - column5X, 0);
            contentStream.showText(detail.getQuantity().toString());
            contentStream.endText();
            tableY -= rowHeight * 2;
        }

        // Thêm chữ ký bác sĩ (dưới thông tin đơn thuốc chi tiết)
        String doctorSignature = "Doctor's Signature"; // Thay đổi thành chữ ký thực tế của bác sĩ

        contentStream.setFont(font, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(marginX, tableY - 30);
        contentStream.showText("Doctor's Signature: " + doctorSignature);
        contentStream.endText();

        // Thêm ngày tháng năm tạo (dưới chữ ký bác sĩ)
        SimpleDateFormat creationDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date creationDate = new Date();
        String creationDateString = creationDateFormat.format(creationDate);

        contentStream.setFont(font, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(marginX, tableY - 50);
        contentStream.showText("Date Created: " + creationDateString);
        contentStream.endText();

        // Đóng luồng nội dung
        contentStream.close();

        // Lưu trang và đóng tài liệu
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }

}
