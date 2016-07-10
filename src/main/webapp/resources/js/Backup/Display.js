$(document).ready( function(){
    var atualiz = 0;
                $(".nave").show( function(evtoon){

                    var p = "1";
                     $.ajax({
                          type:"POST",
                          url:"Display.jsp",
                          data: "pa="+ p,
                            beforeSend: function(){
                              $("#loader").show();
                       },
                         success: function(data){
                               $("#Conteudo").html(data);
                             $("#loader").hide();

                           }        

                        });
                });
                $(".nave").click( function(evt){
                    evt.preventDefault();
                    var p = $(this).attr("id");
                    $.ajax({
                        type:"POST",
                        url:"Display.jsp",
                        data: "pa="+ p,
                        beforeSend: function(){
                            $("#loader").show();
                        },
                        success: function(data){
                            $("#Conteudo").html(data);
                            $("#loader").hide();
                            
                        }        
                                
                    });
                });
                $("#navegacao").show( function(){
                 $.ajax({
                      type:"POST",
                      url:"/jsp/TelaUsuario.jsp",
                      data: "",
                        beforeSend: function(){
                   },
                     success: function(data){
                           $("#navegacao").html(data);
   
                       }        
                              
                    });
                 });
                 

                
            
});
            


