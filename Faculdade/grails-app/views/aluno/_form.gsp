<%@ page import="faculdade.Aluno" %>



<div class="fieldcontain ${hasErrors(bean: alunoInstance, field: 'faculdade', 'error')} required">
	<label for="faculdade">
		<g:message code="aluno.faculdade.label" default="Faculdade" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="faculdade" name="faculdade.id" from="${faculdade.Faculdade.list()}" optionKey="id" required="" value="${alunoInstance?.faculdade?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: alunoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="aluno.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${alunoInstance?.nome}"/>

</div>

