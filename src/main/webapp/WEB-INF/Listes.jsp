<%@page import="java.util.Collection"%>
<%@page import="model.InfoVote"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
	.scroller {
  width: 10em;
  overflow: auto;
  scrollbar-color: rebeccapurple green;
  scrollbar-width: thin;
}

</style>

</head>
<body>
<center style="width :100%">
	<h2>Liste des Listes des votes</h2>
<br /> 
<%
	Collection<InfoVote> infoVotes = (Collection<InfoVote>)request.getAttribute("infoVotes");
%>

<table class="table table-striped" style="width : 100%">
	<thead>
	<tr>
		<th scope="col"><div>Id</div></th>
		<th scope="col"><div>Identifiant</div></th>
		<th scope="col"><div>Nom</div></th>
		<th scope="col"><div>Prénom</div></th>
		<th scope="col"><div>date de naissance</div></th>
		<th scope="col"><div >Bulletin de vote</div></th>
		<th scope="col"><div >Decision</div></th>
	</tr>
	</thead>
<%
	for(InfoVote i : infoVotes){
%>
	<tbody>
		<tr>
		<td><div><%=i.getId() %></div> </td>
		<td> <div><%=i.getIden() %> </div></td>
		<td> <div><%=i.getNom() %> </div></td>
		<td><div><%=i.getPrenom() %></div></td>
		<td><div><%=i.getDate() %></div></td>
		<td><div class="scroller"><%=i.getBulletinvote() %></div> </td>
		<td >
			<div>
				<a href="Envoyer?id=<%=i.getId() %>" class="btn btn-success">Envoyer</a><br>
				<a href="Supprimer?id=<%=i.getId() %>" class="btn btn-danger">Supprimer</a>
		
			</div>
		</td>
	</tr>
	</tbody>
<%
	}
%>
</table>
</center>
</body>
</html>