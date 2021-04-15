<?php
include 'conexion.php';
$dni=$_POST['usuario'];
$password=$_POST['password'];

//$dni="12345678";
//$password="123abc";

$sentencia=$conexion->prepare("SELECT * FROM usuarios WHERE dni=? AND password=?");
$sentencia->bind_param('ss',$dni,$password);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
         echo json_encode($fila,JSON_UNESCAPED_UNICODE);     
}
$sentencia->close();
$conexion->close();
?>