<?php
include 'conexion.php';
$Nro_ruc_dni=$_GET['ruc_dni'];

$consulta ="select * from TRAMITES where Nro_ruc_dni='$Nro_ruc_dni'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
    $tramites[]=array_map('utf8_encode',$fila);
}

echo json_encode($tramites);
$resultado -> close();

?>