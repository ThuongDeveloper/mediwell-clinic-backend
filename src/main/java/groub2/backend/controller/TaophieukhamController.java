package groub2.backend.controller;

import groub2.backend.entities.Casher;
import groub2.backend.entities.Taophieukham;
import groub2.backend.entities.TypeDoctor;
import groub2.backend.service.CasherService;
import groub2.backend.service.TaophieukhamService;
import groub2.backend.service.TypeDoctorService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/taophieukham")
public class TaophieukhamController {

    @Autowired
    TypeDoctorService TDservice;

    @Autowired
    TaophieukhamService Tservice;

    @Autowired
    CasherService Cservice;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Taophieukham> listTaophieukham() {
        return Tservice.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taophieukham> get(@PathVariable Integer id) {
        Taophieukham taophieukham = Tservice.getTaophieukhamById(id);
        if (taophieukham != null) {
            return new ResponseEntity<>(taophieukham, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/typeDoctorId/{typedoctor}")
    public ResponseEntity<List<Taophieukham>> getTypeDoctor(@PathVariable TypeDoctor typedoctor) {
        List<Taophieukham> taophieukham = Tservice.getTaophieukhamByTypeDoctor(typedoctor);
        if (taophieukham != null) {
            return new ResponseEntity<>(taophieukham, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Taophieukham> addTaophieukham(@RequestBody Taophieukham taophieukham) {
        taophieukham.setTotalMoney(30);
        Tservice.addTaophieukham(taophieukham);
        return new ResponseEntity<>(taophieukham, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Taophieukham> updateTaophieukham(@PathVariable Integer id, @RequestBody Taophieukham updatedTaophieukham) {
        Taophieukham taophieukham = Tservice.getTaophieukhamById(id);
        if (taophieukham != null) {
            updatedTaophieukham.setId(id);
            Taophieukham updated = Tservice.updateTaophieukham(updatedTaophieukham);
            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteTaophieukham(@PathVariable int id) {
        Taophieukham taophieukham = Tservice.getTaophieukhamById(id);
        if (taophieukham != null) {
            Tservice.deleteTaophieukham(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xóa không thành công");
        }
    }

    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPrescriptionToPDF(@RequestParam("casherId") int casherId, @RequestParam("typeDoctorId") int typeDoctorId, @RequestParam("id") int id) {

        if (casherId <= 0 || typeDoctorId <= 0 || id <= 0) {
            return ResponseEntity.badRequest().body("Invalid Casher ID or Type Doctor ID".getBytes());
        }

        // Kiểm tra xem toathuocId có tồn tại trong cơ sở dữ liệu không
        Casher casher = Cservice.getCasherById(casherId);
        TypeDoctor typeDoctor = TDservice.findTypeDoctorbyID(typeDoctorId);

        Taophieukham taophieukham = Tservice.getTaophieukhamById(id);
//        Taophieukham casherTaophieukham = (Taophieukham) Tservice.getTaophieukhamByCasherId(casherId);
//        Taophieukham typeDoctorTaophieukham = (Taophieukham) Tservice.getTaophieukhamByTypeDoctorId(typeDoctorId);
        if (casher == null || typeDoctor == null || taophieukham == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Examination form not found".getBytes());
        }

        try {
            // Tạo tài liệu PDF và export dữ liệu vào đó
            byte[] pdfBytes = createAndExportPDF(casher, typeDoctor, taophieukham);

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

    private byte[] createAndExportPDF(Casher casher, TypeDoctor typeDoctor, Taophieukham taophieukham) throws IOException {
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

        // Thêm thông tin phiếu khám bệnh vào tài liệu PDF
        contentStream.beginText();
        contentStream.newLineAtOffset(50, page.getMediaBox().getHeight() - 20);
        contentStream.showText("MEDIWELL CLINIC");
        contentStream.endText();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        String appointmentDate = dateFormat.format(currentDate);

        contentStream.setFont(font, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(page.getMediaBox().getWidth() - 200, page.getMediaBox().getHeight() - 20);
        contentStream.showText("Appointment Date: " + appointmentDate);
        contentStream.endText();

        // Thêm tiêu đề
        contentStream.setFont(font, 16);
        contentStream.beginText();
        contentStream.newLineAtOffset((page.getMediaBox().getWidth() / 2) - 50, page.getMediaBox().getHeight() - 50);
        contentStream.showText("EXAMINATION FORM");
        contentStream.endText();

        // Thêm thông tin bệnh nhân
        contentStream.setFont(font, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(50, page.getMediaBox().getHeight() - 80);
        contentStream.showText("Patient Name: " + taophieukham.getName());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Phone: " + taophieukham.getPhone());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Address: " + taophieukham.getAddress());
        contentStream.endText();

        float marginX = 50;
        float tableY = page.getMediaBox().getHeight() - 170;
        float rowHeight = 20;
        float tableWidth = page.getMediaBox().getWidth() - 2 * marginX;

        contentStream.setFont(font, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(marginX, tableY);
        contentStream.showText("EXAMINATION FORM DETAILS");
        contentStream.endText();

        contentStream.setFont(font, 10);
        contentStream.setLineWidth(1f);
        contentStream.moveTo(marginX, tableY - rowHeight);
        contentStream.lineTo(marginX + tableWidth, tableY - rowHeight);
        contentStream.stroke();
        contentStream.beginText();
        contentStream.newLineAtOffset(marginX, tableY - rowHeight + 4);
        contentStream.showText("Casher Name");

        float column2X = marginX + 150; // Cột thứ 2
        float column3X = column2X + 290; // Cột thứ 3

        contentStream.newLineAtOffset(column2X - marginX, 0);
        contentStream.showText("Type of Disease");
        contentStream.newLineAtOffset(column3X - column2X, 0);
        contentStream.showText("Total Price");

        contentStream.endText();

        contentStream.setFont(font, 10);
        contentStream.setLineWidth(1f);
        contentStream.moveTo(marginX, tableY - rowHeight * 2);
        contentStream.lineTo(marginX + tableWidth, tableY - rowHeight * 2);
        contentStream.stroke();
        contentStream.beginText();
        contentStream.newLineAtOffset(marginX, tableY - rowHeight * 2 + 4);
        contentStream.showText(casher.getName());
        contentStream.newLineAtOffset(column2X - marginX, 0);
        contentStream.showText(typeDoctor.getName());
        contentStream.newLineAtOffset(column3X - column2X, 0);
        contentStream.showText(taophieukham.getTotalMoney().toString() + "$");
        contentStream.endText();
        tableY -= rowHeight * 2;

        String cashierSignature = "Cashier's Signature";

        contentStream.setFont(font, 10);
        contentStream.beginText();
        contentStream.newLineAtOffset(marginX, tableY - 30);
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
