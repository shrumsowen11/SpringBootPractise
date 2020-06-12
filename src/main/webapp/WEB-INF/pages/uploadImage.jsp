<!DOCTYPE html>
<html>
<head>
<title>Upload Image</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>


<!-- Self Made CSS file library import  -->

<link rel="stylesheet" href="css/style.css">
<!-- Self Made CSS file library import  -->

</head>
<body
style="background-image: url(Images/chinaTemple.jpg); background-size:cover; background-attachment:fixed; height:100%; width:100%">	


	<header class="header">
	 <b style="margin-left: 20px;">${applicationScope.email}</b>
     
      <b style="margin-left: 20px;">Mobile : ${applicationScope.mobile}</b>
	</header>
	<br />
	<br />
	
	

	<div class="container mb-3 mt-3">



	 <div class="row "> 
			<div class="col-md-2 col-sm-2 col-xs-12"></div>
			<div class="col-md-8 col-sm-8 col-xs-12 ">
						<img src="Images/coolguy.jpg" style="margin-left: auto; display:block; margin-right: auto; height = 200px; width = 50%; "><br />

				<div class="form-group" style=" width: 100%">

					<form action="updatePhoto" class = "myFormStyle" method="post"  enctype="multipart/form-data">
						<h4><span style="font-size: 18px; color: red ; /* #ec8484 - slight pinky red */" ><b>${message}</b></span></h4><br/>   <!-- Try to add the blinking effect  -->
						<!-- when you do not write this "method", then automatically, it calls the doGet() method and this get method will show all the data(password also) in the browser title. -->
						<!--methods are "post(secured), put, delete, patch, get(default and not secure)"  -->
						<!-- whenever the submit button is clicked, then this page is called -->
							
						<h4 style="color: #e5fb2f/* light yellow */">Upload your profile picture:</h4>
						<label for="photo" style="color: white"> Choose your Profile Photo </label>
						<input type = "file" class = "form-control"  name = "photo" style = "height : 45px ; align-items : flex-end">
				
						<button type="submit" class="btn btn-primary">Upload</button>
						
						<a href = "showAllData" >
							<button type="button" class="btn btn-success">Cancel</button>
						</a>
					</form>
				</div>
			</div>
			<div class="col-md-2 col-sm-2 col-xs-12"></div>
		 </div>
	</div>
	<br />
	<br />
	<footer class="footer">@Copyright Banepali 2020</footer>
</body>
</html>