<%@ page import="faculdade.Faculdade" %>



<div class="fieldcontain ${hasErrors(bean: faculdadeInstance, field: 'alunos', 'error')} ">
	<label for="alunos">
		<g:message code="faculdade.alunos.label" default="Alunos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${faculdadeInstance?.alunos?}" var="a">
    <li><g:link controller="aluno" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="aluno" action="create" params="['faculdade.id': faculdadeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'aluno.label', default: 'Aluno')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: faculdadeInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="faculdade.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${faculdadeInstance?.nome}"/>

</div>

