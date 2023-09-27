package groub2.backend.controller;

import groub2.backend.dto.ListHoaDonThuocDAO;
import groub2.backend.entities.Doctor;
import groub2.backend.entities.Donthuoc;
import groub2.backend.entities.DonthuocDetails;
import groub2.backend.entities.Taophieukham;
import groub2.backend.entities.Thuoc;
import groub2.backend.entities.Toathuoc;
import groub2.backend.service.DoctorService;
import groub2.backend.service.DonthuocDetailsService;
import groub2.backend.service.DonthuocService;
import groub2.backend.service.TaophieukhamService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/donthuoc")
public class DonthuocController {

    @Autowired
    DonthuocService donthuocService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    TaophieukhamService Tservice;
    @Autowired
    DonthuocDetailsService donthuocDetailsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Donthuoc> read() {
        return donthuocService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donthuoc> get(@PathVariable Integer id) {
        Donthuoc donthuoc = donthuocService.getDonThuocId(id);
        if (donthuoc != null) {
            return new ResponseEntity<>(donthuoc, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addCasher(@RequestBody ListHoaDonThuocDAO listHoaDonThuocDAO) {

        int totalmoney = 0;
        var listobj = listHoaDonThuocDAO.getListHDT();
        for (int i = 0; i < listobj.size(); i++) {
            totalmoney += (listobj.get(i).getPrice() * listobj.get(i).getQuantity());
        }

        Donthuoc newDonThuoc = new Donthuoc();
        newDonThuoc.setCreateAt(new Date());
        newDonThuoc.getToathuocId().getTaophieukhamId().setName(listHoaDonThuocDAO.getName());
        newDonThuoc.getToathuocId().getTaophieukhamId().setPhone(listHoaDonThuocDAO.getPhone());
        newDonThuoc.getToathuocId().getTaophieukhamId().setAddress(listHoaDonThuocDAO.getAddress());
        newDonThuoc.getToathuocId().getTaophieukhamId().setDob(listHoaDonThuocDAO.getDob());
        newDonThuoc.getToathuocId().getTaophieukhamId().setGender(listHoaDonThuocDAO.getGender());
        newDonThuoc.getToathuocId().getTaophieukhamId().setTotalMoney(totalmoney);
        newDonThuoc.getToathuocId().getTaophieukhamId().setCasherId(listHoaDonThuocDAO.getCasherId());

        var modelDonthuoc = donthuocService.saveDonthuoc(newDonThuoc);

        for (int i = 0; i < listobj.size(); i++) {
            Thuoc newThuoc = new Thuoc();
            newThuoc.setId(listobj.get(i).getThuocID());
            DonthuocDetails newDonthuocDetails = new DonthuocDetails();
            newDonthuocDetails.setDonthuocId(modelDonthuoc);
            newDonthuocDetails.setPrice(listobj.get(i).getPrice());
            newDonthuocDetails.setQuantity(listobj.get(i).getQuantity());
            newDonthuocDetails.setThuocId(newThuoc);
            donthuocDetailsService.saveDonthuoc(newDonthuocDetails);
        }

        var a = listHoaDonThuocDAO;
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPrescriptionToPDF(@RequestParam("donthuocId") int donthuocId, @RequestParam("phieukhamId") int phieukhamId) {
        if (donthuocId <= 0 || phieukhamId <= 0) {
            return ResponseEntity.badRequest().body("Invalid donthuocId AND phieukhamId".getBytes());
        }

        // Kiểm tra xem toathuocId có tồn tại trong cơ sở dữ liệu không
        Donthuoc donthuoc = donthuocService.getDonThuocId(donthuocId);
        Taophieukham taophieukham = Tservice.getTaophieukhamById(phieukhamId);
        if (donthuoc == null || taophieukham == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dơn Thuoc AND Phieu Kham not found".getBytes());
        }

        try {
            // Tạo tài liệu PDF và export dữ liệu vào đó
            byte[] pdfBytes = createAndExportPDF(donthuoc, taophieukham);

            // Thiết lập header và trả về file PDF
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "Medication_invoice.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(pdfBytes.length)
                    .body(pdfBytes);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private byte[] createAndExportPDF(Donthuoc donthuoc, Taophieukham taophieukham) throws IOException {
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
        contentStream.showText("MEDICATION INVOICE");
        contentStream.endText();

        contentStream.setFont(font, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(50, page.getMediaBox().getHeight() - 80);
        contentStream.showText("Patient Name: " + donthuoc.getToathuocId().getTaophieukhamId().getName());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Phone: " + donthuoc.getToathuocId().getTaophieukhamId().getPhone());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Address: " + donthuoc.getToathuocId().getTaophieukhamId().getAddress());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Type of Disease: " + taophieukham.getTypeDoctorId().getName());
        contentStream.endText();

        float marginX = 50;
        float tableY = page.getMediaBox().getHeight() - 170;
        float rowHeight = 20;
        float tableWidth = page.getMediaBox().getWidth() - 2 * marginX;

        contentStream.setFont(font, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(marginX, tableY);
        contentStream.showText("MEDICATION INVOICE DETAILS");
        contentStream.endText();

        contentStream.setFont(font, 10);
        contentStream.setLineWidth(1f);
        contentStream.moveTo(marginX, tableY - rowHeight);
        contentStream.lineTo(marginX + tableWidth, tableY - rowHeight);
        contentStream.stroke();
        contentStream.beginText();
        contentStream.newLineAtOffset(marginX, tableY - rowHeight + 4);
        contentStream.showText("Medicine Name");

        float column2X = marginX + 250; // Cột thứ 2
        float column3X = column2X + 100; // Cột thứ 3
        float column4X = column3X + 100; // Cột thứ 4

        contentStream.newLineAtOffset(column2X - marginX, 0);
        contentStream.showText("Quantity");
        contentStream.newLineAtOffset(column3X - column2X, 0);
        contentStream.showText("Price");
        contentStream.newLineAtOffset(column4X - column3X, 0);
        contentStream.showText("Into Money");

        contentStream.endText();
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        List<DonthuocDetails> donthuocDetailses = (List<DonthuocDetails>) donthuoc.getDonthuocDetailsCollection();
        for (DonthuocDetails detail : donthuocDetailses) {
            int quantity = detail.getQuantity();
            double price = detail.getPrice();
            double amount = quantity * price;
            contentStream.setFont(font, 10);
            contentStream.setLineWidth(1f);
            contentStream.moveTo(marginX, tableY - rowHeight * 2);
            contentStream.lineTo(marginX + tableWidth, tableY - rowHeight * 2);
            contentStream.stroke();
            contentStream.beginText();
            contentStream.newLineAtOffset(marginX, tableY - rowHeight * 2 + 4);
            contentStream.showText(detail.getThuocId().getName());
            contentStream.newLineAtOffset(column2X - marginX, 0);
            contentStream.showText(detail.getQuantity().toString());
            contentStream.newLineAtOffset(column3X - column2X, 0);
            contentStream.showText(decimalFormat.format(detail.getPrice()) + "$");
            contentStream.newLineAtOffset(column4X - column3X, 0);
            contentStream.showText(decimalFormat.format(amount) + "$");
            contentStream.endText();
            tableY -= rowHeight * 1;
        }

        contentStream.setFont(font, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(marginX, tableY - 40);
        contentStream.showText("Total Money: " + decimalFormat.format(donthuoc.getTotalMoney()) + "$");
        contentStream.endText();

        String cashierSignature = "Cashier's Signature";

        contentStream.setFont(font, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(marginX, tableY - 80);
        contentStream.showText(cashierSignature);
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
