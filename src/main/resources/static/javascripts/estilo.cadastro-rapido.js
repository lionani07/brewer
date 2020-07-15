var Brewer = Brewer || {};

Brewer.EstiloCadastroRapido = (function(){

    function EstiloCadastroRapido(){
        this.modal = $("#cadastroRapidoEstilo");
        this.form = this.modal.find("form");
        this.url = this.form.attr("action");
        this.divMessageError = $(".js-div-message-error");
        this.inputNome = $("#nomeEstilo");
        this.btnSalvar = $(".js-btn-salvar");
    }

    EstiloCadastroRapido.prototype.iniciar = function(){
        this.form.on("submit", function(e){ e.preventDefault(); })
        this.modal.on("shown.bs.modal", onModalShow.bind(this));
        this.modal.on("hide.bs.modal", onModalHide.bind(this));
        this.btnSalvar.on("click", salvarEstiloComAjax.bind(this));
    }

    function salvarEstiloComAjax(){
        var nomeEstilo = this.inputNome.val().trim();       
        $.ajax({
             url: this.url,
             method: "post",
             contentType: "application/json",
             data: JSON.stringify({"nome" : nomeEstilo}),
             success: onSuccessSalvandoEstilo.bind(this),
             error: onErrorSalvandoEstilo.bind(this) 
        });
     }
 
     function onSuccessSalvandoEstilo(estilo){
         var option = $("<option></option>").val(estilo.codigo).text(estilo.nome);        
         $("#estilo").append(option).val(estilo.codigo);
         this.modal.modal("hide");
     }
 
     function onErrorSalvandoEstilo(error){
         var span = $("<span></span>").text(error.responseText);
         this.divMessageError.html(span);
         this.form.find(".form-group").addClass("has-error");
         this.divMessageError.removeClass("hidden");
     }
 
     function onModalShow(){        
         this.inputNome.focus();
     }
     
     function onModalHide(){
         this.inputNome.val("");        
         this.form.find(".form-group").removeClass("has-error");
         this.divMessageError.addClass("hidden");
     }

    return EstiloCadastroRapido;

})();

$(function(){
    var estiloCadastrRapido = new Brewer.EstiloCadastroRapido();
    estiloCadastrRapido.iniciar();
});