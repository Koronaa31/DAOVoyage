<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Admin</title>
	</head>

	<body class="container-fluid"> 
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
		
			<div class="centre col-10">
				<h1>${login}, bienvenue sur l'espace administrateur.</h1>
				<br/>
				
				<div class="row">
					<div class="col-1"></div>
					<div class="col-5">
						<h3>Ajouter une ville</h3>
						<br/>
						<form action="admin" method="POST">
							<div class="form-group row">
								<label for="nom_ville" class="col-3">Ville</label>
								<div class="col-3"><input type="text" required placeholder="Nom" id="nom_ville" name="nom_ville" /></div>
							</div>
							<div class="form-group row">
								<label for="latitude" class="col-3">Latitude</label>
								<div class="col-3"><input type="number" step="0.1" required placeholder="latitude" id="latitude" name="latitude" /></div>
							</div>
							<div class="form-group row">
								<label for="longitude" class="col-3">Longitude</label>
								<div class="col-3"><input type="number" step="0.1" required placeholder="longitude" id="longitude" name="longitude" /></div>
							</div>
							<div class="row btnAdmin">
								<input type="submit" value="Ajouter la ville" class="btn btn-info" />
								<input type="hidden" name="action" value="ajoutVille"/>
							</div>
						</form>
						
						<div class="row btnAdmin"><a target="_blank" href="https://www.coordonnees-gps.fr/">Chercher les coordonnées d'une ville</a></div>
					</div>
					
					<div class="col-6">
						<h3>Ajouter un moyen de transport</h3>
						<br/>
						<form action="admin" method="POST">
							<div class="form-group row">
								<label for="nom_transport" class="col-3">Moyen de transport</label>
								<div class="col-3"><input type="text" required placeholder="Nom" id="nom_transport" name="nom_transport" /></div>
							</div>
							<div class="form-group row">	
								<label for="prix" class="col-3">Prix au km</label>
								<div class="col-3"><input type="number" step="0.01" required placeholder="prix" id="prix" name="prix" /></div>
							</div>
							<div class="form-group row">	
								<label for="vitesse" class="col-3">Vitesse</label>
								<div class="col-3"><input type="number" step="1" required placeholder="vitesse" id="vitesse" name="vitesse" /></div>
							</div>						
							<div class="row btnAdmin">	
								<input type="submit" value="Ajouter le moyen de transport" class="btn btn-info" />
								<input type="hidden" name="action" value="ajoutTransport"/>
							</div>
						</form>
					</div>	
				</div>			
			</div>
	
	
		</main>
		
		<footer class="row">
			<div class="col-4">&copy Tous droits réservés.</div>
			<div class="col-4"><a href="inscription">Plan du site</a></div>
			<div class="col-4"><a href="inscription">Conditions de vente</a></div>
		</footer>
	
	</body>
	
	
	
</html>