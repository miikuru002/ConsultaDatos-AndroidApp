<?php
include 'conexion.php';
$Fecha_resolucion=$_GET['fecha'];

$consulta ="select * from TRAMITES where Fecha_resolucion='$Fecha_resolucion'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
    $tramites[]=array_map('utf8_encode',$fila);
}

echo json_encode($tramites);
$resultado -> close();

?>