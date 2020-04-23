<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Votre panier</title>
	</head>
	
	<body class="container-fluid">
		<header id="header" class="row">
			<div id="logo" class="bandeauSup col-2"><img src="img/logoDef.png"/></div>
			<div class="bandeauSup col-10"><img src="img/bandeau.jpg"/></div>
		</header>

		<main class="row">
			<div class="bandeauGauche col-2">
				<c:if test="${sessionScope.isConnect == null}">
					<div id="inscription">
						<a href="inscription">Inscription</a>
					</div>
					Déjà abonné ? <br/>
					<form class="form-group" method="POST" action="accueil" name="formConnection">
				 		<input class="form-control inputWidth" required placeholder="Login" type="text" name="login"/>
				 		<input class="form-control inputWidth" required placeholder="Password" type="password" name="password"/>
						<input class="btn btn-info" value="Se connecter" type="submit"/>
						<input type="hidden" name="action" value="seConnecter"/>
					</form>
					<c:if test="${sessionScope.error == 'Y'}">
						<div id="error">Login/Password incorrects !</div>
					</c:if>
				</c:if>
				
				<c:if test="${sessionScope.isConnect == 'Y'}">
					<div id="Welcome">Bienvenue ${login}.</div>
				</c:if>
				
				<div id="panier">
					<a href="panier">Voir mon panier.</a><br/>
				</div>
				
				<c:if test="${sessionScope.isConnect == 'Y'}">
					<div id="seDeconnecter">
						<form method="POST" action="accueil">
							<input type="hidden" name="action" value="seDeconnecter">
							<input type="submit" class="btn btn-danger" value="Log out"/>
						</form>
					</div>
				</c:if>
				
				<a href="accueil"><input id="accueilButton" type="button" value="Accueil" class="btn btn-warning" /></a>
				
				<div id="sponsor" class="bandeauSponsor">
					<h6>Nos sponsors :</h6>
					<div class="divImage"><img src="img/airnul.png"/></div>
					<div class="divImage"><img src="img/snif.png"/></div>
					<div class="divImage"><img src="img/brigitteBus.png"/></div>
				</div>
			</div>	

			<div class="centre col-10">
				<h1>Voici votre panier :</h1> <br/>
				<table class="table tableau">
					<thead class="thead-dark">
						<tr>
							<td><strong>Supprimer</strong></td>
							<td><strong>Ville de départ</strong></td>
							<td><strong>Ville d'arrivée</strong></td>
							<td><strong>Moyen de transport</strong></td>
							<td><strong>Durée</strong></td>
							<td><strong>Prix</strong></td>
						</tr>
					</thead>

					<c:forEach items="${ panier }" var="voy" varStatus="myIndex">
						<tr>
							<td>
								<form action="panier" method="POST">
									<input type="hidden" name="nb" value="${myIndex.index}">
									<input type="hidden" name="action" value="deletePanier">
									<input type="submit" class="supprimerVoyage btn btn-outline-info" value="Supprimer" />
								</form>
							</td>
							<td>${voy.v1.nom}</td>
							<td>${voy.v2.nom}</td>
							<td>${voy.t}</td>
							<td>${voy.duree}</td>
							<td>${voy.prix} €</td>
						</tr>
					</c:forEach>
			
					<tr>
						<td colspan="5">Total de votre commande</td>
						<td><strong>${total} €</strong></td>
					</tr>
				</table> <br/>
	
				<div class="row">
					<div class="col-6">			
						<form action="panier" method="POST">
           					 <input type="hidden" name="action" value="clearPanier"/>
           					 <input type="submit" value="Valider mon panier et payer" class="payer btn btn-info" />
       					 </form>
					</div>
					<div class="col-6">			
						<a href="accueil"><input type="button" value="Continuer mes achats" class="btn btn-info"/></a>
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

<!-- //-----------------------------------------------------------------//
	//-----------------------------------------------------------------//
	//-----------------------------------------------------------------//
	//-----------------------------------------------------------------//
 -->	
	
	<script>
	
	$(".payer").click(function(){
		
		alert("Paiement validé, merci !")
	});
	
	</script>

</html>