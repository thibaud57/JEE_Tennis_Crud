<%@include file="taglibs.jsp"%>
<!doctype html>
<html lang="fr">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="./starter-template.css">
    <title>Hello, world!</title>
  </head>
  <body>

  <%@ include file="menu.jsp" %>

<main role="main" class="container">

  <div class="starter-template">
    <h1>Liste des joueurs</h1>
    <p>Bienvenue <c:out value="${ connectedUser.login }" /> </p>

    <p class="lead">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
  <a href="/index/listetournoi">Liste tournoi</a>
  </div>

</main><!-- /.container -->
<div class="container">


<div style="    padding: 1.5rem;    margin-right: 0;    margin-left: 0;    border-width: .2rem;">
  <a href="/index/ajouterjoueur">	<button type="button" class="btn btn-primary">Ajouter joueur </button></a>
</div>

  <table class="table">
  <thead>
    <tr>
      <th scope="col" style="width:10%">#</th>
      <th scope="col" style="width:25%">Nom</th>
      <th scope="col" style="width:20%">Prenom</th>
      <th scope="col" style="width:20%">Sexe</th>
	  <th scope="col" style="width:20%"></th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${joueurs}" var="joueur">
    <form action="modifierjoueur" method="get">
      <tr>
        <th scope="row"><c:out value="${ joueur.id }" /></th>
        <td><c:out value="${ joueur.nom }" /></td>
        <td><c:out value="${ joueur.prenom }" /></td>
        <td><c:out value="${ joueur.sexe }" /></td>
        <td>
          <input type="hidden" name="idjoueur" value="${ joueur.id }" />
          <button type="submit" name="action" value="Modifier" class="btn btn-outline-primary" <c:if test="${connectedUser.profil == '2'}" >disabled</c:if>>Modifier</button>
          <button type="submit"  name="action" value="Supprimer" class="btn btn-outline-warning" <c:if test="${connectedUser.profil == '2'}" >disabled</c:if>>Supprimer</button>
        </td>
      </tr>
    </form>
  </c:forEach>
  </tbody>
</table>


  <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>


