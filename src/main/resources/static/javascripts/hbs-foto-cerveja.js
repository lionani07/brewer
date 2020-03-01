var Brewer = Brewer || {};

Brewer.UploadFoto = (function(){

    function UploadFoto(){
        this.divFotoCerveja = $(".js-foto-cerveja");
        this.inputFoto = $("input[name=foto]");
        this.inputContentType = $("input[name=contentType]");
        this.hbsHtmlFotoCerveja = $("#hbs-template-foto-cerveja").html();
        this.template = Handlebars.compile(this.hbsHtmlFotoCerveja); 
    }

    UploadFoto.prototype.iniciar = function(){
        var settings = {
            url: this.divFotoCerveja.data("url-foto"),
            multiple: false,
            type: "json",
            method: "POST",
            allow: "*.(jpg|jpeg|png)",
            complete: onComplete.bind(this),
        }        
        UIkit.upload('.js-upload', settings);
    }

    function onComplete(response){
        var nomeFoto = response.response.foto;
        var contentType = response.response.contentType;
        this.inputFoto.val(nomeFoto);
        this.inputContentType.val(contentType);

        var html = this.template({nome: nomeFoto});
        this.divFotoCerveja.append(html);

        this.divFotoCerveja.find(".js-upload").addClass("hide");
        $(".js-remove-foto").on("click", onRemoveFoto.bind(this));       
    }

    function onRemoveFoto(){
        $("#hbs-html-foto-cerveja").remove();
        this.inputFoto.val("");
        this.inputContentType.val("");        
        this.divFotoCerveja.find(".js-upload").removeClass("hide");  
    }

    return UploadFoto;

})();

$(function(){
    var uploadFoto = new Brewer.UploadFoto();
    uploadFoto.iniciar();
});