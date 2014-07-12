package faculdade

class Faculdade {
	String nome
	static hasMany = [alunos:Aluno]

    static constraints = {
    }
}
