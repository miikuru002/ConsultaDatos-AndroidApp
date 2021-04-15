<?php
include 'conexion.php';
$dni=$_POST['usuario'];
$nombres=$_POST['nombres'];
$apellidos=$_POST['apellidos'];
$password=$_POST['password'];

$consulta="INSERT INTO usuarios VALUES('".$dni."','".$nombres."','".$apellidos."','".$password."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);

?>