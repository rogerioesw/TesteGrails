package faculdade

class Professor {
	String nome
	String email

    static constraints = {
		nome(blank:false)
		email(blank:false)
    }
}
