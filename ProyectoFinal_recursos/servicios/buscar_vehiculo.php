<?php
include 'conexion.php';
$Numero=$_GET['numero'];

$consulta ="select * from VEHICULOS where Numero='$Numero'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
    $vehiculos[]=array_map('utf8_encode',$fila);
}

echo json_encode($vehiculos);
$resultado -> close();

?>