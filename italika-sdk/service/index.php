<?php

include_once '../payload.php';


// status 0=>Pendiente, 1=>Servicio iniciado, 2=>Terminado

if (isset($request["post"])) {
    $idLogin = $request["idLogin"];
    $idVehicle = $request["idVehicle"];
    $date = getDateStr();
    $time = getTimeStr();
    if (isset($request["complet"])) {
        $note = $request["note"];
        $km = $request["km"];
        $query = "insert into service (`idLogin`, `idVehicle`, `note`, `km`, `date`, `time`) values ('$idLogin', '$idVehicle', '$note', '$km', '$date', '$time')";
        connection()->query($query);
        json("success", "success");
    } else if (isset($request["schedule"])) {
        $date = $request["date"];
        $time = $request["time"];
        $query = "insert into service (`idLogin`, `idVehicle`, `km`, `date`, `time`) values ('$idLogin', '$idVehicle', '', '$date', '$time')";
        connection()->query($query);
        json("success", "success");
    }
} else if (isset($request["get"])) {
    if (isset($request["all"])) {
        $query = "select * from service";
        $data = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC);
        foreach ($data as $key => $value) {
            $query = "select * from user where idLogin='" . $value["idLogin"] . "'";
            $temp = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC);
            array_push_key($data[$key], "client", $temp[0]);

            $query = "select * from vehicle where id='" . $value["idVehicle"] . "'";
            $temp = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC);
            array_push_key($data[$key], "vehicle", $temp[0]);

            $query = "select * from serviceList where idService='" . $value["id"] . "'";
            $temp = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC);
            array_push_key($data[$key], "refactions", $temp);
        }
        json("data", $data);
    } else if (isset($request["idLogin"])) {
        $idLogin = $request["idLogin"];
        $query = "select * from service where idLogin='$idLogin'";
        $data = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC);
        foreach ($data as $key => $value) {
            $query = "select * from serviceList where idService='" . $value["id"] . "'";
            $temp = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC);
            array_push_key($data[$key], "refactions", $temp);
            
            $query = "select SUM(serviceList.price) AS total from serviceList where serviceList.idService='".$value["id"]."'";
            $temp = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
            array_push_key($data[$key], "total", $temp);
        }
        json("data", $data);
    }
} else if (isset($request["add"])) {
    $id = $request["idIservice"];
    $name = $request["name"];
    $price = $request["price"];
    $none = $request["note"];
    $query = "insert into serviceList (`idService`, `name`, `price`, `note`) values ('$id', '$name', '$price', '$none')";
    connection()->query($query);
    json("success", "success");
} else if (isset($request["init"])) {
    $id = $request["idService"];
    $date = getDateStr();
    $query = "update service set status='1', dateInit='$date' where id='$id' and status=0";
    connection()->query($query);
    json("success", "success");
} else if (isset($request["finish"])) {
    $id = $request["idService"];
    $query = "update service set status='2', dateOut='$date' where id='$id' and status=1";
    connection()->query($query);
    json("success", "success");
}