$(document).ready( function(){
                $(".btitulo").click( function(evt){
                    evt.preventDefault();
                    var a = $(this).attr("value");
                    $.ajax({
                        type:"POST",
                        url:"articlefullmodel.jsp",
                        data: "artigo="+ a,
                        beforeSend: function(){
                            $("#loader").show();
                        },
                        success: function(data){
                            $("#Conteudo").html(data);
                            $("#loader").hide();
                            
                        }        
                                
                    });
                });   
});
            


