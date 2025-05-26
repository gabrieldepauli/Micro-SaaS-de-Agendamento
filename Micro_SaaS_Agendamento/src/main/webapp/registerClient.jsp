<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Cliente</title>
    <link rel="icon" href="assets/images/logo.jpg" type="image/x-icon">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
            padding: 20px;
        }
        .container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 1.5rem;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .btn-danger {
            background-color: #dc3545;
            border-color: #dc3545;
        }
        .btn-danger:hover {
            background-color: #c82333;
            border-color: #bd2130;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2 class="text-center mb-4">Cadastro de Estudante</h2>
        
        <form action="ClientController" method="POST">
            <div class="form-group">
                <label for="full_name">Nome:</label>
                <input type="text" class="form-control" id="full_name" name="full_name" required>
            </div>

            <div class="form-group">
                <label for="cpf">CPF:</label>
                <input type="text" class="form-control" id="cpf" name="cpf" required>
            </div>

            <div class="form-group">
				<label for="address">Cidade:</label> <select class="form-control"
					id="address" name="address" required>
					<option value="">Selecione a cidade</option>
					<option value="Araraquara">Araraquara</option>
					<option value="São Carlos">São Carlos</option>
					<option value="Ribeirão Preto">Ribeirão Preto</option>
				</select>
			</div>

            <div class="form-group">
                <label for="phone">Número de Telefone:</label>

                <input type="tel" class="form-control" id="phone" name="phone" 
                       pattern="^\(\d{2}\) \d{5}-\d{4}$" placeholder="(XX) XXXXX-XXXX" required>
                <small id="phoneHelp" class="form-text text-muted">
                    Informe no formato (XX) XXXXX-XXXX.
                </small>
            </div>

            <div class="d-flex flex-column justify-content-center align-items-center">
                <button type="submit" class="btn btn-primary mb-3" style="width: 200px;">Cadastrar</button>

                <a href="javascript:history.back()" class="btn btn-danger" style="width: 200px;">Voltar</a>
            </div>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>
