<?php

include_once '../payload.php';

if (isset($request["post"])) {
    // modelo, año, noSerie, noPlacas, marca, 
    $idLogin = $request["idLogin"];
    $model = $request["model"];
    $year = $request["year"];
    $noSerie = $request["noSerie"];
    $plates = $request["plates"];
    $brand = $request["brand"];

    $query = "insert into vehicle (`idLogin`, `model`, `year`, `noSerie`, `plates`, `brand`) values ('$idLogin', '$model', '$year', '$noSerie', '$plates', '$brand')";
    connection()->query($query);
    json("success", "success");
} else if (isset($request["get"])) {
    $idLogin = $request["idLogin"];
    $query = "select * from vehicle where idLogin='$idLogin'";
    $data = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC);
    json("data", ($data != null) ? $data : "No data");
} 