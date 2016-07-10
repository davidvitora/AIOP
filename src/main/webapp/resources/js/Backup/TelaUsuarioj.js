$(document).ready( function(){        
        $("#logar").click( function(evt){
                    evt.preventDefault();
                    var login = $('#login').val();
                    var senha = $('#usr').val();
                    $.ajax({
                        type:"POST",
                        url:"/LoginUsuario",
                        data: { login: login , senha: senha},
                        beforeSend: function(){
                        },
                        success: function(data){
                            $.ajax({
                            type:"POST",
                            url:"jsp/TelaUsuario.jsp",
                            data: "",
                                beforeSend: function(){
                            },
                            success: function(data){
                                parent.$(".pimba").html(data);
                            }        
                            });
                        }        
                    });
                    
                    
                    
                });
});