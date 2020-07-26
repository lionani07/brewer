var Brewer = Brewer || {};

Brewer.Multiselecao = (function() {

    function Multiselecao() {
        this.btnAction = $('.js-btn-action');
		this.selecaoCheckbox = $('.js-selecao');
		this.selecaoTodosCheckbox = $(".js-selecao-todos");
		this.url = $('.js-url').data('url');
    }

    Multiselecao.prototype.iniciar = function() {
        this.btnAction.on('click', onclickButton.bind(this));      
        this.selecaoTodosCheckbox.on('click', onSelecaoTodos.bind(this));
        this.selecaoCheckbox.on('click', onSelecao.bind(this));
    }

    function onclickButton(evento) {
        var btnClikado = $(evento.currentTarget);
        var action = btnClikado.data('action');
		var checkBoxSelecionados = this.selecaoCheckbox.filter(":checked");
		
		var codigos = $.map(checkBoxSelecionados, function(c){
			return $(c).data('codigo');
		});
        
        if(codigos.length > 0) {
        
            $.ajax({
                url: this.url,
                method: 'PUT',
                data: {
                    codigos: codigos,
					action: action
                },
				success: refresh.bind(this)
            });
        }		

    }

    function onSelecaoTodos() {
        var status = this.selecaoTodosCheckbox.prop('checked');
        this.selecaoCheckbox.prop('checked', status);
        statusBtnAcao.call(this, status);
		console.log(this.url);
    }

    function onSelecao() {
        var totalCheckboxsChecados = this.selecaoCheckbox.filter(':checked').length;
        var totalCheckboxs = this.selecaoCheckbox.length;
        this.selecaoTodosCheckbox.prop('checked', totalCheckboxs==totalCheckboxsChecados);
        statusBtnAcao.call(this, totalCheckboxsChecados);
    }

    function statusBtnAcao(ativar) {
        ativar ? this.btnAction.removeClass('disabled') : this.btnAction.addClass('disabled');
    }

	function refresh() {
		window.location.reload();
	}

    return Multiselecao;

}());

$(function() {
    var multiselecao = new Brewer.Multiselecao();
    multiselecao.iniciar();
})