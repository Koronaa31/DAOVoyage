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
			<div class="row col-12"></div><h1>${login}, bienvenue sur l'espace administrateur</h1></div>
			
			<div class="row col-12">
				<div class="col-6">
					<h3>Ajouter une ville</h3>
					
					<form action="admin" method="POST">
						<div class="form-group row">
							<label for="nom_ville" class="col-3">Ville</label>
							<div class="col-3"><input type="text" placeholder="Nom" id="nom_ville" name="nom_ville" /></div>
						</div>
						<div class="form-group row">
							<label for="latitude" class="col-3">Latitude</label>
							<div class="col-3"><input type="number" step="0.1" placeholder="latitude" id="latitude" name="latitude" /></div>
						</div>
						<div class="form-group row">
							<label for="longitude" class="col-3">Longitude</label>
							<div class="col-3"><input type="number" step="0.1" placeholder="longitude" id="longitude" name="longitude" /></div>
						</div>
						<div class="form-group row">
							<input type="submit" value="Ajouter la ville" class="btn btn-info" />
							<input type="hidden" name="action" value="ajoutVille"/>
						</div>
					</form>
					
					<div class="row"><a target="_blank" href="https://www.coordonnees-gps.fr/">Chercher les coordonnées d'une ville</a></div>
				</div>
				
				<div class="col-6">
					<h3>Ajouter un moyen de transport</h3>
					
					<form action="admin" method="POST">
						<div class="form-group row">
							<label for="nom_transport" class="col-3">Moyen de transport</label>
							<div class="col-3"><input type="text" placeholder="Nom" id="nom_transport" name="nom_transport" /></div>
						</div>
						<div class="form-group row">	
							<label for="prix" class="col-3">Prix au km</label>
							<div class="col-3"><input type="number" step="0.01" placeholder="prix" id="prix" name="prix" /></div>
						</div>
						<div class="form-group row">	
							<label for="vitesse" class="col-3">Vitesse</label>
							<div class="col-3"><input type="number" step="1" placeholder="vitesse" id="vitesse" name="vitesse" /></div>
						</div>						
						<div class="row">	
							<input type="submit" value="Ajouter le moyen de transport" class="btn btn-info" />
							<input type="hidden" name="action" value="ajoutTransport"/>
						</div>
					</form>
				</div>
			</div>
	
	
		</main>
	
	</body>
	
	
	
</html>