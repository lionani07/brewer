var Brewer = Brewer || {};

Brewer.MascaraCpfCnpj = (function() {
	
	MascaraCpfCnpj = function() {
		this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
		this.labelCpfCnpj = $('[for=cpf-cnpj]');
		this.inputCpfCnpj = $('#cpf-cnpj');
	}
	
	MascaraCpfCnpj.prototype.iniciar = function() {
		this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));
	}
	
	function onTipoPessoaAlterado(evento) {
		var tipoPessoaSelecionada = $(evento.currentTarget);		
		
		this.labelCpfCnpj.text(tipoPessoaSelecionada.data('documento'));
		this.inputCpfCnpj.mask(tipoPessoaSelecionada.data('mascara'));	
		this.inputCpfCnpj.val('');		
		this.inputCpfCnpj.removeAttr('disabled');
		this.inputCpfCnpj.focus();
	}
	
	return MascaraCpfCnpj;
	
})();


$(function() {
	
	var mascaraCpfCnpj = new Brewer.MascaraCpfCnpj();
	mascaraCpfCnpj.iniciar();
	
});