var Brewer = Brewer || {};

Brewer.ComboEstado = (function() {
	
	ComboEstado = function() {
		this.combo = $("#combo-estado");
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboEstado.prototype.iniciar = function() {
		this.combo.on("change", onEstadoAlterado.bind(this));
	}
	
	function onEstadoAlterado() {
		this.emitter.trigger("alterado", this.combo.val());
	}
	
	return ComboEstado;	
})();

Brewer.ComboCidade = (function() {
	
	ComboCidade = function(comboEstado) {
		this.comboEstado = comboEstado;
		this.combo = $('#cidade');
		this.imgLoading = $('.js-img-loading');
	}
	
	ComboCidade.prototype.iniciar = function() {
		reset.call(this);
		this.comboEstado.on("alterado", onEstadoAlterado.bind(this));
	}
	
	function onEstadoAlterado(evento, codigoEstado) {
		if (codigoEstado) {
			var resposta = $.ajax({
				url: this.combo.attr('action'),
				method: 'GET',
				contentType: 'application/json',
				data: {'estado': codigoEstado},
				beforeSend: inicarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});			
			resposta.done(onBuscarCidadesFinalizado.bind(this));			
		} else {
			reset.call(this);
		}
	}
	
	function onBuscarCidadesFinalizado(cidades) {		
		var options = [];
		cidades.forEach(function(cidade) {			
			var option = $("<option></option>").val(cidade.codigo).text(cidade.nome);			
			options.push(option);
		});	 		
		this.combo.empty().append(options);
		this.combo.removeAttr('disabled');
	}
	
	function reset() {
		var option = $("<option></option>").text('Selecione a cidade');
		this.combo.empty().append(option);		
		this.combo.attr('disabled', 'disabled');
	}
	
	function inicarRequisicao() {
		reset.call(this);
		this.imgLoading.removeClass('hide');
	}
	
	function finalizarRequisicao() {
		this.imgLoading.addClass('hide');
	}
	
	return ComboCidade;
	
})();


$(function() {
	var comboEstado = new Brewer.ComboEstado();
	comboEstado.iniciar();
	
	var comboCidade = new Brewer.ComboCidade(comboEstado);
	comboCidade.iniciar();
	
});