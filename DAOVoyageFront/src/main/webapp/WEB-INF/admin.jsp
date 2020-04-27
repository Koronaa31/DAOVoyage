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
			<div class="centre col-10">
				<h1>${login}, Bienvenue sur l'espace administrateur.</h1>
				<br/>
				<div class="row">
					<form action="admin" method="POST">
						<label for="villes">Gérer la listes des villes disponibles à la vente</label><br/>
						<input type="submit" value="Modifier les villes" id="villes" class="btn btn-info" />
						<input type="hidden" name="action" value="modifVille" />
					</form>
				</div>
				<br/>
				<div class="row">
					<form action="admin" method="POST">
						<label for="transports">Gérer la listes des moyens de transport disponibles à la vente</label><br/>
						<input type="submit" value="Modifier les transports" id="transports" class="btn btn-info" />
						<input type="hidden" name="action" value="modifTransport" />
					</form>
				</div>	
				<br/>

<!-- -------------Gérer Villes------------- -->
		
				<c:if test="${ aGerer == 'gererVilles' }">
				
					<div class="row">	
							
						<h3>Supprimer une ville de la liste</h3>
						
						<table class="table tableau">
							<tr>
								<td class="colonne3"></td>
								<td class="colonne3"><strong>Ville</strong></td>
								<td class="colonne3"><strong>Latitude</strong></td>
								<td class="colonne3"><strong>Longitude</strong></td>
							</tr>
						
							<c:forEach items="${villes}" var="v">
								<tr>
									<td>
										<form action="admin" method="POST">
											<input type="hidden" name="id" value="${v.id}">
											<input type="hidden" name="action" value="supprimerVille">
											<input type="submit" class="btn btn-outline-info" value="Supprimer" />
										</form>
									</td>
									<td>${v.nom}</td>
									<td>${v.latitude}</td>
									<td>${v.longitude}</td>
								</tr>						
							</c:forEach>
						</table>
					</div>
						
					<div class="row">		
						<h3>Ajouter une ville à la liste</h3>
						
						<form class="formLong" action="admin" method="POST">
							<table class="table tableau">
								<thead class="thead-dark">
									<tr>
										<td class="colonne3"></td>
										<td class="colonne3"><strong>Ville</strong></td>
										<td class="colonne3"><strong>Latitude</strong></td>
										<td class="colonne3"><strong>Longitude</strong></td>
									</tr>
								</thead>
								
								<tr>
									<td>
										<input type="hidden" name="action" value="ajouterVille">
										<input type="submit" class="btn btn-outline-info" value="Ajouter" />
									</td>
									<td>
										<input type="text" required placeholder="Nom" id="nom_ville" name="nom_ville" />
									</td>
									<td>
										<input type="number" step="0.1" required placeholder="latitude" id="latitude" name="latitude" />
									</td>
									<td>
										<input type="number" step="0.1" required placeholder="longitude" id="longitude" name="longitude" />
									</td>
								</tr>	
							</table>
						</form>
						<div class="row"><a target="_blank" href="https://www.coordonnees-gps.fr/">Chercher les coordonnées d'une ville</a></div>	
					</div>	
				</c:if>
				
				
		
		<!-- -------------Gérer Transports------------- -->
		
				<c:if test="${ aGerer == 'gererTransports' }">		
					<div class="row">						
						<h3>Supprimer un moyen de transport de la liste</h3>
						
						<table class="table tableau">
							<thead class="thead-dark">
								<tr>
									<td class="colonne3"></td>
									<td class="colonne3"><strong>Transport</strong></td>
									<td class="colonne3"><strong>Prix au km (€)</strong></td>
									<td class="colonne3"><strong>Vitesse (km/h)</strong></td>
								</tr>
							</thead>
						
							<c:forEach items="${transports}" var="t">
								<tr>
									<td>
										<form action="admin" method="POST">
											<input type="hidden" name="id" value="${t.id}">
											<input type="hidden" name="action" value="supprimerTransport">
											<input type="submit" class="btn btn-outline-info" value="Supprimer" />
										</form>
									</td>
									<td>${t.nom}</td>
									<td>${t.prixAuKm}</td>
									<td>${t.vitesse}</td>
								</tr>						
							</c:forEach>
						</table>
					
						
						<h3>Ajouter un moyen de transport à la liste</h3>
						
						<form class="formLong" action="admin" method="POST">
							<table class="table tableau">
								<thead class="thead-dark">
									<tr>
										<td class="colonne3"></td>
										<td class="colonne3"><strong>Transport</strong></td>
										<td class="colonne3"><strong>Prix au km (€)</strong></td>
										<td class="colonne3"><strong>Vitesse (km/h)</strong></td>
									</tr>
								</thead>
								
								<tr>
									<td>
										<input type="hidden" name="action" value="ajouterTransport">
										<input type="submit" class="btn btn-outline-info" value="Ajouter" />
									</td>
									<td>
										<input type="text" required placeholder="Nom" id="nom_transport" name="nom_transport" />
									</td>
									<td>
										<input type="number" step="0.01" required placeholder="prix" id="prix" name="prix" />
									</td>
									<td>
										<input type="number" step="1" required placeholder="vitesse" id="vitesse" name="vitesse" />
									</td>
								</tr>	
							</table>
						</form>
					</div>			
				</c:if>
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