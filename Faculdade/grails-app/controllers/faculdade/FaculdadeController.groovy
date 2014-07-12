package faculdade



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FaculdadeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Faculdade.list(params), model:[faculdadeInstanceCount: Faculdade.count()]
    }

    def show(Faculdade faculdadeInstance) {
        respond faculdadeInstance
    }

    def create() {
        respond new Faculdade(params)
    }

    @Transactional
    def save(Faculdade faculdadeInstance) {
        if (faculdadeInstance == null) {
            notFound()
            return
        }

        if (faculdadeInstance.hasErrors()) {
            respond faculdadeInstance.errors, view:'create'
            return
        }

        faculdadeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'faculdade.label', default: 'Faculdade'), faculdadeInstance.id])
                redirect faculdadeInstance
            }
            '*' { respond faculdadeInstance, [status: CREATED] }
        }
    }

    def edit(Faculdade faculdadeInstance) {
        respond faculdadeInstance
    }

    @Transactional
    def update(Faculdade faculdadeInstance) {
        if (faculdadeInstance == null) {
            notFound()
            return
        }

        if (faculdadeInstance.hasErrors()) {
            respond faculdadeInstance.errors, view:'edit'
            return
        }

        faculdadeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Faculdade.label', default: 'Faculdade'), faculdadeInstance.id])
                redirect faculdadeInstance
            }
            '*'{ respond faculdadeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Faculdade faculdadeInstance) {

        if (faculdadeInstance == null) {
            notFound()
            return
        }

        faculdadeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Faculdade.label', default: 'Faculdade'), faculdadeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'faculdade.label', default: 'Faculdade'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
