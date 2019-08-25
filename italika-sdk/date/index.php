<?php

include_once '../payload.php';

if (isset($request["get"])) {
    $dias = array("Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado");
    $meses = array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");

    json("date", $dias[date('w')] . " " . date('d') . " de " . $meses[date('n') - 1] . " del " . date('Y'));
}