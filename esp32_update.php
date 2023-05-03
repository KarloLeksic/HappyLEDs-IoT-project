<?php

$servername = "localhost";
$dBUsername = "root";
$dBPassword = "";
$dBName = "happyleds";

$conn = mysqli_connect($servername, $dBUsername, $dBPassword, $dBName);
if (!$conn) {
	die("Connection failed: ".mysqli_connect_error());
}

if (isset($_POST['check_LED_status'])) {
	$sql = "SELECT * FROM LED_status;";
	$result   = mysqli_query($conn, $sql);
	$row  = mysqli_fetch_assoc($result);
	echo $row['status'] .' '. $row['r'] .' '. $row['g'] .' '. $row['b'] .' '. $row['brightness'];	
}	

?>