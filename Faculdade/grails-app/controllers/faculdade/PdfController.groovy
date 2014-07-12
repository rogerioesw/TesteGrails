package faculdade

class PdfController {
	
	PdfService pdfService;
	
    def index() { 
		response.contentType = "application/pdf"
		response.outputStream<<pdfService.createPDF()
	}
}
