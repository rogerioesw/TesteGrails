package faculdade


import org.xhtmlrenderer.pdf.ITextRenderer
import static org.springframework.http.HttpStatus.*
import grails.plugin.jyoshiriro.jasperResponse.renderers.Jasper
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProfessorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Professor.list(params), model:[professorInstanceCount: Professor.count()]
    }

    def show(Professor professorInstance) {
        respond professorInstance
    }

    def create() {
        respond new Professor(params)
    }
	
	

    @Transactional
    def save(Professor professorInstance) {
        if (professorInstance == null) {
            notFound()
            return
        }

        if (professorInstance.hasErrors()) {
            respond professorInstance.errors, view:'create'
            return
        }

        professorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'professor.label', default: 'Professor'), professorInstance.id])
                redirect professorInstance
            }
            '*' { respond professorInstance, [status: CREATED] }
        }
    }

    def edit(Professor professorInstance) {
        respond professorInstance
    }

    @Transactional
    def update(Professor professorInstance) {
        if (professorInstance == null) {
            notFound()
            return
        }

        if (professorInstance.hasErrors()) {
            respond professorInstance.errors, view:'edit'
            return
        }

        professorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Professor.label', default: 'Professor'), professorInstance.id])
                redirect professorInstance
            }
            '*'{ respond professorInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Professor professorInstance) {

        if (professorInstance == null) {
            notFound()
            return
        }

        professorInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Professor.label', default: 'Professor'), professorInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
	
	def report() {
		params.jasperForceDownload=true
		params.jasperDownloadName='my-custom-file-name'
		render params as Jasper
	}
	
			

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'professor.label', default: 'Professor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
