<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
<link rel="stylesheet" href="/nakitWeb/css/style.css" type="text/css" />
</head>
<body>
<section id="sidebar"> 
  <div class="white-label">
  </div> 
  <div id="sidebar-nav">   
    <ul>
      <li class="active"><a href="admin.jsp"><i class="fa fa-dashboard"></i> Ubacivanje</a></li>
      <li><a href=""><i class="fa fa-desktop"></i> Brisanje</a></li>
      <li><a href=""><i class="fa fa-usd"></i> Azuriranje rezrvacija</a></li>
      <li><a href=""><i class="fa fa-pencil-square-o"></i>Azuriranje ostalog</a></li>
      
    </ul>
  </div>
</section>
<section id="content">
  <div id="header">
    <div class="header-nav">
      <div class="menu-button">
        <!--<i class="fa fa-navicon"></i>-->
      </div>
      <div class="nav">
        <ul>
          
          <li class="nav-profile">
            <div class="nav-profile-image">
              <div class="nav-profile-name">Admin<i class="fa fa-caret-down"></i></div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
  <div class="content">
    <div class="content-header">
      <center><h1>Oglasna tabla </h1>
      <p>Lep nakit, jos lepse cene, a Jelena najlepsa</p></center>
    </div>
    <div class="widget-box sample-widget">
      <div class="widget-header">
        <h2>Oglas1</h2>
        
        <i class="fa fa-cog"></i>
      </div>
      <div class="widget-content">
      
      </div>
    </div>
    <div class="widget-box sample-widget">
      <div class="widget-header">
        <h2>Oglas 2</h2>
        <i class="fa fa-cog"></i>
      </div>
      <div class="widget-content">
        
      </div>
    </div>
    <div class="widget-box sample-widget">
      <div class="widget-header">
        <h2>Oglas 3</h2>
        <i class="fa fa-cog"></i>
      </div>
     	 <div class="widget-content">
        
     	 </div>
    	</div>
      <div class="widget-box sample-widget">
      <div class="widget-header">
        <h2>Oglas 4</h2>
        <i class="fa fa-cog"></i>
      </div>
      <div class="widget-content">
        
      </div>
    </div>  
  </div>
</section>

</body>
</html>