
<%@page import="bean.Discussion"%>
<%@page import="bean.ListDiscussion"%>
<%@page import="bean.New"%>
<%@page import="pager.PagerNew"%>
<%@page import="critere.CritereEtatActivite"%>
<%@page import="servlet.membre.FrontalCommun"%>
<%@page import="text.pageweb.MesActivites"%>
<%@page import="bean.Activite"%>
<%@page import="pager.PagerMesActivites"%>
<%@page import="critere.FiltreRecherche"%>
<%@page import="outils.Outils"%>
<%@page import="servlet.membre.Frontal"%>
<%@page import="parametre.ActionPage"%>
<%@page import="dao.CacheDAO"%>
<%@page import="bean.RefTypeActivite"%>
<%@page import="text.pageweb.ProposeActiviteMembre"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<title>><%=ProposeActiviteMembre.TITRE_ONGLET%></title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/prettify/r298/run_prettify.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/css/bootstrap-dialog.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/js/bootstrap-dialog.min.js"></script>

<script src="/waydplace/js/moment.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
	<link href="/waydplace/css/styleWaydSlide.css" rel="stylesheet"
	type="text/css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link href="/waydplace/css/slide.css" rel="stylesheet" type="text/css">

	
	<script src="/waydplace/js/slide.js"></script>
	
</head>
<body>

<%

Profil profil = (Profil) request.getSession()
.getAttribute("profil");
ListDiscussion listDiscussion = new ListDiscussion(profil);

%>


<div class="row">
    <!-- uncomment code for absolute positioning tweek see top comment in css -->
    <!-- <div class="absolute-wrapper"> </div> -->
    <!-- Menu -->
    <div class="side-menu">
    
    <nav class="navbar navbar-default" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <div class="brand-wrapper">
            <!-- Hamburger -->
            <button type="button" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <!-- Brand -->
            <div class="brand-name-wrapper">
                <a class="navbar-brand" href="#">
                    Brand
                </a>
            </div>

            <!-- Search -->
            <a data-toggle="collapse" href="#search" class="btn btn-default" id="search-trigger">
                <span class="glyphicon glyphicon-search"></span>
            </a>

            <!-- Search body -->
            <div id="search" class="panel-collapse collapse">
             	<div class="row">
					<div class="col-sm-3">
					
					</div>

				</div>
            </div>
        </div>

    </div>

    <!-- Main Menu -->
    <div class="side-menu-container">
        <ul class="nav navbar-nav">

<%@ include file="menuMembreTest.jsp"%>

        </ul>
    </div><!-- /.navbar-collapse -->
</nav>
    
    </div>
</div>

    <!-- Main Content -->
    <div class="container-fluid">
        <div class="side-body">
       

	
	<table class="table table-striped table-responsive "
			style='border: 5px solid #fff;' id='matable'>

			<tbody>

				<%
					for (Discussion discussion : listDiscussion.getMesDiscussion()) {
				%>
				<tr
					onclick="document.location='<%=discussion.lienHtmlMessageDiscussion(profil)%>'">
					<td><%=discussion.getAdpaterListHtml(profil)%></td>
				</tr>


				<%
					}
				%>

			</tbody>
		</table>

</div></div>

</body>
</html>
