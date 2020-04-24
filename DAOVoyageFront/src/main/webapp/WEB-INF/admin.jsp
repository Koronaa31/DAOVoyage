<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Admin</title>
	</head>

	<body class="container-fluid"> 
	
	<!-- -------------------Bandeau haut et gauche------------------ -->
	
		<header id="header" class="row">
			<div id="logo" class="bandeauSup col-2"><img src="img/logoDef.png"/></div>
			<div class="bandeauSup col-10"><img src="img/bandeau.jpg"/></div>
		</header>
		
		<main class="row">
		
			<div class="bandeauGauche col-2">				
				<div id="Welcome">Bienvenue ${login}.</div>
				<div id="seDeconnecter">
					<form method="POST" action="accueil">
						<input type="hidden" name="action" value="seDeconnecter">
						<input type="submit" class="btn btn-danger" value="Log out"/>
					</form>
				</div>
				
				<div id="sponsor" class="bandeauSponsor">
					<h6>Nos sponsors :</h6>
					<div class="divImage"><img src="img/airnul.png"/></div>
					<div class="divImage"><img src="img/snif.png"/></div>
					<div class="divImage"><img src="img/brigitteBus.png"/></div>
				</div>
			</div>
		
		
<!-- -------------------Centre------------------ -->
			<div class="col-10">
				<div class="row">
					<form action="admin" method="POST">
						<label for="villes">Gérer la listes des villes disponibles à la vente</label><br/>
						<input type="submit" value="Modifier les villes" id="villes" />
						<input type="hidden" name="action" value="modifVille" />
					</form>
				</div>	
				<div class="row">
					<form action="admin" method="POST">
						<label for="transports">Gérer la listes des moyens de transport disponibles à la vente</label><br/>
						<input type="submit" value="Modifier les transports" id="transports" />
						<input type="hidden" name="action" value="modifTransport" />
					</form>
				</div>
			</div>

		</main>
		
<!-- -------------------Footer------------------ -->
		
		<footer class="row">
			<div class="col-4">&copy Tous droits réservés.</div>
			<div class="col-4"><a href="inscription">Plan du site</a></div>
			<div class="col-4"><a href="inscription">Conditions de vente</a></div>
		</footer>
	
	</body>
	
	
	
</html>