var Brewer = Brewer || {};

Brewer.MascaraCep = (function() {
	
	MascaraCep = function() {
		this.inputCep = $(".js-cep");
	}
	
	MascaraCep.prototype.enabled = function() {
		this.inputCep.mask("00000-000");
	}
	
	return MascaraCep;	
	
})();

$(function() {
	var mascaraCep = new Brewer.MascaraCep();
	mascaraCep.enabled();
})