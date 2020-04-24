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
				
				<a href="accueil"><input id="accueilButton" type="button" value="Accueil" class="btn btn-warning" /></a>
				
				<div id="sponsor" class="bandeauSponsor">
					<h6>Nos sponsors :</h6>
					<div class="divImage"><img src="img/airnul.png"/></div>
					<div class="divImage"><img src="img/snif.png"/></div>
					<div class="divImage"><img src="img/brigitteBus.png"/></div>
				</div>
			</div>
		
			<div class="centre col-10">
				<c:if test="${sessionScope.aAfficher == 'creation'}">
					<h1>Création d'une cagnotte</h1><br/>
					<div class="row">
						<div class="col-6">
							<div>
								<label>Client Initiateur</label>
								<input class="form-control" type="text" placeholder=${login} readonly>
							</div></br>
							<c:if test="${sessionScope.checkLogin == 'wrong'}">
								<form method="POST" action="cagnotte">
									<div class="form-group">
										<label>Client Destinataire</label>
										<input name="loginDestinataire" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
										<input type="hidden" name="action" value="checkLogin"/>
									</div>
									<button type="submit" class="btn btn-info">Submit</button>
								</form>
							</c:if>
							<c:if test="${sessionScope.checkLogin == 'right'}">
								<div>
									<label>Client Destinataire</label>
									<input class="form-control" type="text" placeholder=${destinataire.login} readonly></br>
									<label><u>Choisir le voyage à offrir</u></label>
									<form class="form-group" method="POST" action="cagnotte">
										Ville de départ
										<select required name="v1" class="form-control formWidth">
											<option selected value="N">Sélectionnez une ville de départ</option>
										    <c:forEach items="${villes1}" var="v">
										        <option value="${v.nom}">${v.nom}</option>
										    </c:forEach>
										</select>
										Ville d'arrivée
										<select required name="v1" class="form-control formWidth">
											<option selected value="N">Sélectionnez une ville d'arrivée</option>
										    <c:forEach items="${villes2}" var="v">
										        <option value="${v.nom}">${v.nom}</option>
										    </c:forEach>
										</select>
										Moyen de transport
										<select required name="v1" class="form-control formWidth">
											<option selected value="N">Sélectionnez un transport</option>
										    <c:forEach items="${transports}" var="t">
										        <option value="${t.nom}">${t.nom}</option>
										    </c:forEach>
										</select>
										<div id="rechercheButton" class="col-12">
											<input class="btn btn-info" type="submit" value="Créer une cagnotte">
										</div>
									</form>
								</div>
								
							</c:if>
						</div>
					</div>
				</c:if>
				<c:if test="${sessionScope.aAfficher == 'participation'}">
					<h1>Participation à une cagnotte</h1><br/>
				</c:if>
				<c:if test="${sessionScope.aAfficher == 'archives'}">
					<h1>Voici vos différentes cagnotes</h1><br/>
				</c:if>
			</div>
		</main>
	
		<footer class="row">
			<div class="col-4">&copy Tous droits réservés.</div>
			<div class="col-4"><a href="inscription">Plan du site</a></div>
			<div class="col-4"><a href="inscription">Conditions de vente</a></div>
		</footer>
	</body>
</html>