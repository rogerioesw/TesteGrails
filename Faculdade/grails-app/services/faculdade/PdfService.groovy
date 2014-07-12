package faculdade

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfCell
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import grails.transaction.Transactional

@Transactional
class PdfService {
	boolean transactional = false
	
	def createPDF() {
		//def professor = Professor.get(params.id)
		//def render_result = g.render(template:"/professor/pdf.gsp", model: [professor: professor])
		//Professor professorInstance = null;
		ByteArrayOutputStream b = null;
		
		Document document = new Document();
		
		try {
			b = new ByteArrayOutputStream();
			PdfWriter.getInstance(document,b);
			document.open();
			document.add(new Paragraph("Palavras Reservadas"));
		} catch(DocumentException de) {
			System.err0.println(de.getMessage());
		}
		
		document.close();
		
		byte[] pdf = b.toByteArray();
		
		return pdf;
	}


    def serviceMethod() {

    }
}
