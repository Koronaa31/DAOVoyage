<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
		<title>Mes commandes</title>
	</head>
	<body class="container-fluid">
		<header id="header" class="row">
			<div id="logo" class="bandeauSup col-2"><img src="img/logoDef.png"/></div>
			<div class="bandeauSup col-10"><img src="img/bandeau.jpg"/></div>
		</header>

		<main class="row">
			<div class="bandeauGauche col-2">
				<c:if test="${sessionScope.isConnect == 'Y'}">
					<div id="Welcome">${login}</div>
				</c:if>
				
				<div id="panier">
					<a href="panier">Voir mon panier.</a><br/>
				</div>
				
				<div id="seDeconnecter">
					<form method="POST" action="accueil">
						<input type="hidden" name="action" value="seDeconnecter">
						<input type="submit" class="btn btn-danger" value="Log out"/>
					</form>
				</div>
				
				<a href="accueil"><input id="accueilButton" type="button" value="Accueil" class="btn btn-warning" /></a>
				
				<div id="sponsor" class="bandeauSponsor">
					<h6>Nos sponsors :</h6>
					<div class="divImage"><img src="img/airnul.png"/></div>
					<div class="divImage"><img src="img/snif.png"/></div>
					<div class="divImage"><img src="img/brigitteBus.png"/></div>
				</div>
			</div>	

			<div class="centre col-10">
				<h1>Récapitulatif de vos commandes :</h1> <br/>
				<table class="table tableau">
					<c:if test="${sessionScope.commandes == null}">
						<div>Vous n'avez pas encore de commandes !</div>
					</c:if>
					<c:if test="${sessionScope.commandes != null}">
					<thead class="thead-dark">
						<tr>
							<td><strong>Ville de départ</strong></td>
							<td><strong>Ville d'arrivée</strong></td>
							<td><strong>Moyen de transport</strong></td>
							<td><strong>Durée</strong></td>
							<td><strong>Prix</strong></td>
						</tr>
					</thead>

					<c:forEach items="${commandes}" var="com" varStatus="myIndex">
						<tr>
							<td>${com.v1.nom}</td>
							<td>${com.v2.nom}</td>
							<td>${com.t.nom}</td>
							<td>${com.duree}</td>
							<td>${com.prix} €</td>
						</tr>
					</c:forEach>
					</c:if>
				</table>
			</div>
		</main>
		
		<footer class="row">
			<div class="col-4">&copy Tous droits réservés.</div>
			<div class="col-4"><a href="inscription">Plan du site</a></div>
			<div class="col-4"><a href="inscription">Conditions de vente</a></div>
		</footer>
	</body>
</html>