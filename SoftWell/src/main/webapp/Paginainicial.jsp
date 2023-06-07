<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="style.css">
    <script src="inputmask.min.js"></script>
</head>

<body>
    <div class="container">
        <h1 class="display-4">SOFTWELL</h1>
            <div id="divLogin">
                <form action="UsuarioLogin" method="post">
                    <label for="email">E-mail:</label>
                    <input type="email" id="email" name="email" required >
                    <label for="senha">Senha:</label>
                    <input type="password" id="senha" name="senha" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required>
                    <input type="submit"value="login">
                </form>
            </div>
            <button type="button" class="btn">Esqueci a senha</button>
            <h2 style="display: none;" id="esqSenha" class="mt-4">Esqueceu a senha</h2>
            <div id="DivEsquecersenha" style="display: none;">
                <form action="" method="post">
                    <label for="emailEsquecido">E-mail de cadastro:</label>
                    <input type="email" id="emailEsquecido" name="emailEsquecido" required >
                    <input type="submit" value="Enviar">
                </form>
            </div>
        <button type="button" class="btn" id="buttonCriarConta">Criar conta</button>
        <h2 id="TituloCriarConta" style="display: none;" class="mt-4">Criar conta</h2>
            <div id="DivCadastro" style="display: none;">
                    <form action="UsuarioCadastro" method="post">
                        <label for="nome">Nome:</label>
                        <input type="text" id="nome" name="nome" pattern="[A-Za-z\s]+" required class="form-control">
                        <label for="sexo">Sexo:</label>
                        <select id="sexo" name="sexo" required class="form-control">
                            <option value="">Selecione</option>
                            <option value="Feminino">Feminino</option>
                            <option value="Masculino">Masculino</option>
                            <option value="Prefiro não especificar">Prefiro não especificar</option>
                        </select>
                        <label for="emailCadastro">E-mail:</label>
                        <input type="email" id="emailCadastro" name="emailCadastro" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" required class="form-control">
                        <label for="celular">Celular:</label>
                        <input type="tel" id="celular" name="celular"  data-inputmask="'mask': '(99) 99999-9999'" pattern="\(\d{2}\) \d{5}-\d{4}" placeholder="(99) 99999-9999" required>
                        <label for="senhaCadastro">Senha:</label>
                        

                        <input type="password" id="Cadastrorsenha" name="Cadastrorsenha" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" placeholder="Senha" minlength="8" required>
                        <input type="password" id="senhaConfirmar" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" placeholder="Confirmar Senha" minlength="8" required>
                        <input type="submit" value="Registrar" onclick="compararSenhas()">
                    </form>
                    
                </div>  
                </div>
        
        
            </div>
        </form>
    </div>

    <script>
        $(document).ready(function() {
                var btnEsquecerSenha = $("button.btn");
                var divLogin = $("#divLogin");
                var divEsquecerSenha = $("#DivEsquecersenha");
                var esqSenha = $("#esqSenha");

                btnEsquecerSenha.on("click", function() {
                    divLogin.hide();
                    btnEsquecerSenha.hide();
                    divEsquecerSenha.show();
                    esqSenha.show();
                });

                var btnCriarConta = $("#buttonCriarConta");
                var divLogin = $("#divLogin");
                var divEsquecerSenha = $("#DivEsquecersenha");
                var divCriarConta = $("#DivCadastro");
                var btnEsquecerSenha = $("button.btn");
                var esqSenha = $("#esqSenha");
                var TituloCriarConta = $("#TituloCriarConta");

                btnCriarConta.on("click", function() {
                    divLogin.hide();
                    divEsquecerSenha.hide();
                    divCriarConta.show();
                    esqSenha.hide();
                    btnCriarConta.hide();
                    btnEsquecerSenha.hide();
                    TituloCriarConta.show();
                });
            
                function compararSenhas() {
  var senha = $("#Cadastrorsenha").val();
  var senhaConfirmar = $("#senhaConfirmar").val();

  if (senha.toLowerCase() !== senhaConfirmar.toLowerCase()) {
    alert("As senhas não coincidem!");
  }
}
});

  


            


        Inputmask().mask(document.getElementById("celular"));
    </script>
    <script src="script.js"></script>
</body>
</html>
