package groub2.backend.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/export-data")
public class PDFController {

    @GetMapping
    public ResponseEntity<byte[]> exportDataToPDF(
            @RequestParam(name = "sang", defaultValue = "false") boolean sang,
            @RequestParam(name = "trua", defaultValue = "false") boolean trua,
            @RequestParam(name = "chieu", defaultValue = "false") boolean chieu,
            @RequestParam(name = "name", defaultValue = "false") boolean name,
            @RequestParam(name = "phone", defaultValue = "false") boolean phone,
            @RequestParam(name = "address", defaultValue = "false") boolean address) {

        try {
            // Create a new PDF document
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Create a content stream for the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Add content to the PDF
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);

            // Replace this with your actual data based on the query parameters
            if (name) {
                contentStream.showText("Name: John Doe");
                contentStream.newLine();
            }
            if (phone) {
                contentStream.showText("Phone: 123-456-7890");
                contentStream.newLine();
            }
            if (address) {
                contentStream.showText("Address: 123 Main St");
                contentStream.newLine();
            }
            if (sang) {
                contentStream.showText("Sang: ..."); // Add Sang data here
                contentStream.newLine();
            }
            if (trua) {
                contentStream.showText("Trua: ..."); // Add Trua data here
                contentStream.newLine();
            }
            if (chieu) {
                contentStream.showText("Chieu: ..."); // Add Chieu data here
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();

            // Create a ByteArrayOutputStream to hold the PDF content
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();

            // Set response headers for the PDF file
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "exported_data.pdf");

            // Return the PDF as a byte array
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(byteArrayOutputStream.size())
                    .body(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
