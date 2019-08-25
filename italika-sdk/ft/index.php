<?php

include_once '../payload.php';

if (isset($request["token"])) {
    $idLogin = $request["idLogin"];
    $token = $request["token"];
    $query = "update ft set token='' where token='$token'";
    connection()->query($query);
    $query = "select * from ft where idLogin='$idLogin'";
    $ft = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
    if ($ft != null) {
        $query = "update ft set token='$token' where idLogin='$idLogin'";
    } else {
        $query = "insert into ft (`idLogin`, `token`) values ('$idLogin', '$token')";
    }
    connection()->query($query);
    json("success", "success");
}