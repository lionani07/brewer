$(function(){

    var modal = $("#cadastroRapidoEstilo");
    var form = modal.find("form");
    var url = form.attr("action");
    var divMessageError = $(".js-div-message-error");
    var inputNome = $("#nomeEstilo");
    var btnSalvar = $(".js-btn-salvar");

    form.on("submit", function(e){
        event.preventDefault();
    })
    modal.on("shown.bs.modal", onModalShow);
    modal.on("hide.bs.modal", onModalHide);
    btnSalvar.on("click", salvarEstiloComAjax);

    function salvarEstiloComAjax(){
       var nomeEstilo = inputNome.val();       
       $.ajax({
            url: url,
            method: "post",
            contentType: "application/json",
            data: JSON.stringify({"nome" : nomeEstilo}),
            success: onSuccessSalvandoEstilo,
            error: onErrorSalvandoEstilo,

       });
    }

    function onSuccessSalvandoEstilo(estilo){
        var option = $("<option></option>").val(estilo.codigo).text(estilo.nome);        
        $("#estilo").append(option).val(estilo.codigo);
        modal.modal("hide");
    }

    function onErrorSalvandoEstilo(error){
    	var span = $("<span></span>").text(error.responseText);
        divMessageError.append(span);
        form.find(".form-group").addClass("has-error");
        divMessageError.removeClass("hidden");
    }

    function onModalShow(){        
        inputNome.focus();
    }
    function onModalHide(){
        inputNome.val("");
        form.find(".form-group").removeClass("has-error");
        divMessageError.addClass("hidden");
    }
   
});