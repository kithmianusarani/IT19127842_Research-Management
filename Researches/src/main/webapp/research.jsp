<%@page import="com.research" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Researcher Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/research.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Researcher Management</h1>
<form id="formresearch" name="formresearch">
 Researcher Name: 
 <input id="researchName" name="researchName" type="text" 
 class="form-control form-control-sm">
 <br> Researcher Contact_No: 
 <input id="researchContactNo" name="researchContactNo" type="text" 
 class="form-control form-control-sm">
 <br> Researcher Email: 
 <input id="researchEmail" name="researchEmail" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidresearchIDSave" 
 name="hidresearchIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divresearchGrid">

 <%
 research researchObj = new research(); 
 out.print(researchObj.readresearch()); 
 %>
 
</div>
</div> </div> </div> 
</body>
</html>