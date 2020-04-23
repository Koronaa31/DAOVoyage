<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Voyages disponibles</title>
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
				<h1>Voyages disponibles</h1> <br/>
				<table class="table tableau">
					<thead class="thead-dark">
						<tr>
							<td><strong>Sélectionner</strong></td>
							<td><strong>Ville de départ</strong></td>
							<td><strong>Ville d'arrivée</strong></td>
							<td><strong>Moyen de transport</strong></td>
							<td><strong>Durée</strong></td>
							<td><strong>Prix</strong></td>
						</tr>
					</thead>

					<c:forEach items="${ recherche }" var="voy">
						<tr>
							<td> <input type="button" class="addPanier btn btn-outline-info" v1="${voy.v1.nom}" v2="${voy.v2.nom}" t="${voy.t}" value="Ajouter au panier"> </td>
							<td>${ voy.v1.nom } <input type="hidden" value="${voy.v1.nom}"/> </td>
							<td>${ voy.v2.nom }</td>
							<td>${ voy.t }</td>
							<td>${ voy.duree }</td>
							<td>${ voy.prix } €</td>
						</tr>
					</c:forEach>
				</table> <br/>
				<div class="row col-12 boutonCentre">
					<a href="panier"><input type="button" id="showPanier" nbPanier="${panier}" value="Accéder au panier (${panier})" name="boutonPanier" class="btn btn-info" /></a>
				</div> <br/>
				<h2>Effectuer une nouvelle recherche</h2><br/>
				<form class="form-group row" method="POST" action="recherche">
					<div class="col-4">
						Ville de départ
						<select name="v1" class="form-control formWidth">
							<option value="Toulouse">Toulouse</option>
							<option value="Paris">Paris</option>
							<option value="Lyon">Lyon</option>
							<option value="Strasbourg">Strasbourg</option>
							<option value="Nice">Nice</option>
						</select>
					</div>
					<div class="col-4">
						Ville d'arrivée
						<select name="v2" class="form-control formWidth">
							<option selected value="N">Toutes</option>
							<option value="Toulouse">Toulouse</option>
							<option value="Paris">Paris</option>
							<option value="Lyon">Lyon</option>
							<option value="Strasbourg">Strasbourg</option>
							<option value="Nice">Nice</option>
						</select>
					</div>
					<div class="col-4">
						Moyen de transport
						<select name="t" class="form-control formWidth">
							<option selected value="N">Tous</option>
							<option value="Avion">Avion</option>
							<option value="Train">Train</option>
							<option value="Bus">Bus</option>
							<option value="Cheval">Cheval</option>
							<option value="PoussePousse">PoussePousse</option>
						</select>
					</div>
					<div id="rechercheButton" class="col-12">
						<input class="btn btn-info" type="submit" value="Lancer la recherche">
					</div>
				</form>
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
	
	$(".addPanier").click(function(){
		
		var nom1=$(this).attr('v1');
		var nom2=$(this).attr('v2');
		var t=$(this).attr('t');
	
		var btnPanier=$("#showPanier");
		var nbPanier=btnPanier.attr("nbPanier");
		
		$.ajax("panier", { type: "POST",
			data: { v1:nom1,v2:nom2,t:t,action:"addPanier" },
			success: function (retour) 
			{ 
				
				nbPanier++;
				
				btnPanier.val("Accéder au panier ("+nbPanier+")");
				btnPanier.attr("nbPanier",nbPanier);
				
			} 
		});
	});
	
	</script>
</html>