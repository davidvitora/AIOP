$(function() {
                // valida o formulário
                $('#form-signup').validate({
                    // define regras para os campos
                    rules: {
                        email: {
                            required: true,    
                            email: true                           
                        },
                        nome: {
                            required: true,
                            minlength: 4
                        },
                        login: {
                            required: true,
                            minlength: 4,
                            maxlength: 10
                        },
                        senha: {
                            required: true,
                            minlength: 6,
                            maxlength: 16
                        },
                        comfirmarsenha: {
                            equalTo: "#senha"
                        }
                    },
                    // define messages para cada campo
                    messages: {
                        email: {
                            required: "Por favor, informe um e-mail",
                            email: "Por favor insira um e-mail valido"
                        },
                        nome: "Preencha o seu nome",
                        login: {
                            required: "Digite um login",
                            minlength:"Login de no minimo 4 digitos",
                            maxlength: "Login acima de 10 caracteres"
                            
                        },
                        senha: {
                            required: "Digite uma senha",
                            minlength: "Senha de no minimo 6 digitos",
                            maxlength: "Senha acima de 16 caracteres"
                        },
                        comfirmarsenha: {
                            equalTo: "As senhas digitadas sao distintas"
                        }
                    }
                });
});

