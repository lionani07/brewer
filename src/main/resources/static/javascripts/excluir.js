var Brewer = Brewer || {};

Brewer.Excluir = (function() {

    Excluir = function() {
        this.btnExcluir = $('.js-excluir');			
    }

    Excluir.prototype.iniciar = function() {
        this.btnExcluir.on('click', onExcluir.bind(this));
    }

    function onExcluir(event) {
		var btnClicado = $(event.currentTarget);
		var url = btnClicado.data('url');
		
		swal({
			title: 'Tem certeza?',
			text: 'Excluir',
			showCancelButton: true,
			confirmButtonText: 'Sim, exclua agora!'
		}, onExcluirConfirmado.bind(this, url));
    }

	function onExcluirConfirmado(url) {
		$.ajax({
            url: url,
            method: 'DELETE',
            success: onDeletadoConSucceso.bind(this)
		});
	}

    function onDeletadoConSucceso() {
        window.location.reload();
    }

    return Excluir;

})();

$(function() {
    var excluir = new Brewer.Excluir();
    excluir.iniciar();
});