<?php

include_once './payload.php';

if (isset($request["post"])) {

    $idLogin = $request["idLogin"];
    $name = $request["name"];
    $direction = $request["direction"];
    $email = $request["email"];
    $ciudad = $request["city"];
    $estado = $request["state"];

    $query = "select * from user where idLogin='$idLogin'";
    $user = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC);
    if ($user != null) {
        $query = "update user set name='$name', direction='$direction', email='$email', ciudad='$ciudad', '$estado' where idLogin='$idLogin'";
    } else {
        $query = "insert into user (`idLogin`, `name`, 'direction', `email`, `city`, `state`) values ('$idLogin', '$name', '$direction', '$email', '$ciudad', '$estado')";
    }
    connection()->query($query);
    json("success", "success");
} 