<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Modifier les transports</title>
	</head>

<!-- ----------Bandeaux sup et gauche---------- -->


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
				
				<a href="admin"><input id="accueilButton" type="button" value="Accueil Admin" class="btn btn-warning" /></a>
				
				<div id="sponsor" class="bandeauSponsor">
					<h6>Nos sponsors :</h6>
					<div class="divImage"><img src="img/airnul.png"/></div>
					<div class="divImage"><img src="img/snif.png"/></div>
					<div class="divImage"><img src="img/brigitteBus.png"/></div>
				</div>
			</div>

<!-- --------------------Centre-------------------- -->
		
			<div class="centre col-10">
				<h1>Gérer la liste des moyens de transport</h1>
				<br/>
				
				<div class="row">	
					
					<h3>Supprimer un moyen de transport de la liste</h3>
					
					<table class="table tableau">
						<thead class="thead-dark">
							<tr>
								<td></td>
								<td><strong>Transport</strong></td>
								<td><strong>Prix au km (€)</strong></td>
								<td><strong>Vitesse (km/h)</strong></td>
							</tr>
						</thead>
					
						<c:forEach items="${transports}" var="t">
							<tr>
								<td>
									<form action="adminTransport" method="POST">
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
					
					<form action="adminTransport" method="POST">
						<table class="table tableau">
							<thead class="thead-dark">
								<tr>
									<td></td>
									<td><strong>Transport</strong></td>
									<td><strong>Prix au km (€)</strong></td>
									<td><strong>Vitesse (km/h)</strong></td>
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
			</div>
	
	
		</main>

<!-- --------------------Footer-------------------- -->

		<footer class="row">
			<div class="col-4">&copy Tous droits réservés.</div>
			<div class="col-4"><a href="inscription">Plan du site</a></div>
			<div class="col-4"><a href="inscription">Conditions de vente</a></div>
		</footer>
	
	</body>
	
	
	
</html>