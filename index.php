<?php

$servername = "localhost";
$dBUsername = "root";
$dBPassword = "";
$dBName = "happyleds";

$conn = mysqli_connect($servername, $dBUsername, $dBPassword, $dBName);

if (!$conn) {
	die("Connection failed: ".mysqli_connect_error());
}

if (isset($_POST['toggle'])) {
	$sql = "SELECT * FROM led_status;";
	$result   = mysqli_query($conn, $sql);
	$row  = mysqli_fetch_assoc($result);
	
	if($row['status'] == 0){
		$update = mysqli_query($conn, "UPDATE led_status SET status = 1 WHERE id = 1;");		
	}		
	else{
		$update = mysqli_query($conn, "UPDATE led_status SET status = 0 WHERE id = 1;");		
	}
}

if (isset($_POST['power'])) {
	if($_POST['power'] == 1) {
		mysqli_query($conn, "UPDATE led_status SET status = 1 WHERE id = 1;");		
	}		
	else {
		mysqli_query($conn, "UPDATE led_status SET status = 0 WHERE id = 1;");		
	}
}

if (isset($_POST['brightness'])){
    mysqli_query($conn, "UPDATE led_status SET brightness = {$_POST['brightness']} WHERE id = 1;");
}

if (isset($_POST['effect'])){
    mysqli_query($conn, "UPDATE led_status SET effect = {$_POST['effect']} WHERE id = 1;");
}

if (isset($_POST['colors'])){ // RRR GGG BBB
	$str = $_POST['colors'];
	$rgb = explode(' ', $str);
	mysqli_query($conn, "UPDATE led_status SET r = $rgb[0], g = $rgb[1], b = $rgb[2], effect = $rgb[3] WHERE id = 1;");
}

if (isset($_POST['check_LED_status'])) {
	$sql = "SELECT * FROM led_status;";
	$result   = mysqli_query($conn, $sql);
	$row  = mysqli_fetch_assoc($result);
	echo $row['status'] .' '. $row['r'] .' '. $row['g'] .' '. $row['b'] .' '. $row['brightness'] .' '. $row['effect'];	
}


$sql = "SELECT * FROM led_status;";
$result   = mysqli_query($conn, $sql);
$row  = mysqli_fetch_assoc($result);	

?>


<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- bootstrap 5 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	

	<title>Karlito LED</title>
</head>
<body>
	<div>		
		
		<div class="container-fluid p-5 bg-primary text-white text-center">
			<h1>Happy LEDs <i style="font-size: 25px;">by Karlito</i></h1>
			<p>Bright up your life!</p> 
		</div>

		<div class="container">
			<?php echo '<h1 class="my-3">The status of the LED is: '.$row['status'].'</h1>';?>	

			<div>
				<form action="#" method="POST" enctype="multipart/form-data">			
					<button type="submit" class="btn btn-outline-primary" name="toggle">Toggle</button>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>
