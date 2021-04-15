<?php
include 'conexion.php';
$Numero=$_GET['numero'];

$consulta ="select * from TRAMITES where Numero='$Numero'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
    $tramites[]=array_map('utf8_encode',$fila);
}

echo json_encode($tramites);
$resultado -> close();

?>