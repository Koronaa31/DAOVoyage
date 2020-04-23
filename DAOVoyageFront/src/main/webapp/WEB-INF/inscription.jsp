<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Inscription</title>
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
				<h1>Inscription</h1> <br/>
				<div class="row">
					<div class="col-2"></div>
					<div class="cadreInscription col-8">
						<div class="jumbotron">
							<div id="titreInscription">
								<h2>Inscrivez vous en moins d'une minute !</h2>
							</div> <br/>
							<div>Veuillez renseigner les champs suivants :</div> <br/>
							<form id="form" name="form" method="POST" action="inscription">
								<div>
									</br><input class="form-control" type="text"
										name="login" value="${sessionScope.login}"
										placeholder="Identifiant" required />
								</div>
								<div>
									</br><input
										id="validationServer01" class="form-control" type="email"
										name="mail" value="${sessionScope.mail}"
										placeholder="Adresse e-mail" required />
								</div>
								<c:if test="${sessionScope.isConnect=='N'}">
									</br/>
									<div class="error">Adresse mail déjà utilisée</div>
								</c:if>
								
								<div>
									<br/><input class="form-control" type="password"
										name="password" id="password" placeholder="Mot de passe" required />
								</div>
								<div>
									<br/><input class="form-control" type="password"
										name="confirm_password" id="confirm_password"
										placeholder="Confirmez le mot de passe" required /><br />
								</div>
								<br />
								<div>
									<input type="submit" class="btn btn-info" value="S'inscrire" />
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

<!-- //-----------------------------------------------------------------//
	//-----------------------------------------------------------------//
	//-----------------------------------------------------------------//
	//-----------------------------------------------------------------//
 -->

	<script>
		$(document).ready(function() {
			var $submitBtn = $("#form input[type='submit']");
			var $passwordBox = $("#password");
			var $confirmBox = $("#confirm_password");
			var $errorMsg = $('<span class="error" id="error_msg">Les mots de passes sont différents</span>');
	
			// This is incase the user hits refresh - some browsers will maintain the disabled state of the button.
			$submitBtn.removeAttr("disabled");
	
			function checkMatchingPasswords() {
				if ($confirmBox.val() != "" && $passwordBox.val != "") {
					if ($confirmBox.val() != $passwordBox.val()) {
						$submitBtn.attr("disabled", "disabled");
						$errorMsg.insertAfter($confirmBox);
					}
				}
			}
	
			function resetPasswordError() {
				$submitBtn.removeAttr("disabled");
				var $errorCont = $("#error_msg");
				if ($errorCont.length > 0) {
					$errorCont.remove();
				}
			}
	
			$("#confirm_password, #password").on("keydown",
					function(e) {
						/* only check when the tab or enter keys are pressed
						 * to prevent the method from being called needlessly  */
						if (e.keyCode == 13 || e.keyCode == 9) {
							checkMatchingPasswords();
						}
					}).on("blur", function() {
				// also check when the element looses focus (clicks somewhere else)
				checkMatchingPasswords();
			}).on("focus", function() {
				// reset the error message when they go to make a change
				resetPasswordError();
			})
	
		});
	</script>
</html>