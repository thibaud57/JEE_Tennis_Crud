<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <img class="mb-4" src="./plogo.png" style="width:25px;" alt="" />
    <a class="navbar-brand" href="/index/listejoueur">Menu</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">


            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Ajouter</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="/index/ajouterjoueur">Ajouter joueur</a>
                    <a class="dropdown-item" href="/index/ajoutertournoi">Ajouter tournoi</a>
                    <a class="dropdown-item" href="#">Ajouter match</a>
                </div>
            </li>


            <li class="nav-item">
                <form method="post" action="deco">
                    <button class="btn btn-secondary my-2 my-sm-0" type="submit" >Deconnexion</button>
                </form>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" method="post" action="listejoueur">
            <input class="form-control mr-sm-2" type="text" placeholder="Recherche" name="txtSearch" aria-label="Recherche">
            <button class="btn btn-secondary my-2 my-sm-0" type="submit">Rechercher</button>
        </form>
    </div>
</nav>
