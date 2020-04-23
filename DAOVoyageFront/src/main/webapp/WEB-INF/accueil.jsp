<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Accueil DAO Voyage</title>
	</head>

	<body id="body" class="container-fluid">

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
				
				<div id="sponsor" class="bandeauSponsor">
					<h6>Nos sponsors :</h6>
					<div class="divImage"><img src="img/airnul.png"/></div>
					<div class="divImage"><img src="img/snif.png"/></div>
					<div class="divImage"><img src="img/brigitteBus.png"/></div>
				</div>
			</div>
		
			<div class="centre col-10">
				<h1>Bienvenue sur DAO Voyage</h1><br/>
				<p>"Mais, vous savez, moi je ne crois pas qu'il y ait de bonne ou de mauvaise situation. Moi, si je devais résumer ma vie aujourd'hui avec vous, je dirais que c'est d'abord des rencontres, des gens qui m'ont tendu la main, peut-être à un moment où je ne pouvais pas, où j'étais seul chez moi. Et c'est assez curieux de se dire que les hasards, les rencontres forgent une destinée... Parce que quand on a le goût de la chose, quand on a le goût de la chose bien faite, le beau geste, parfois on ne trouve pas l'interlocuteur en face, je dirais, le miroir qui vous aide à avancer. Alors ce n'est pas mon cas, comme je le disais là, puisque moi au contraire, j'ai pu ; et je dis merci à la vie, je lui dis merci, je chante la vie, je danse la vie... Je ne suis qu'amour ! Et finalement, quand beaucoup de gens aujourd'hui me disent "Mais comment fais-tu pour avoir cette humanité ?", eh ben je leur réponds très simplement, je leur dis que c'est ce goût de l'amour, ce goût donc qui m'a poussé aujourd'hui à entreprendre une construction mécanique, mais demain, qui sait, peut-être seulement à me mettre au service de la communauté, à faire le don, le don de soi..."</p>
				<h2>Effectuer une recherche</h2><br/>
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
</html>