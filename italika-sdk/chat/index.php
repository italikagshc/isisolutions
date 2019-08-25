<?php

include_once '../payload.php';

if (isset($request["post"])) {
    $idSender = $request["sender"];
    $idReceiver = $request["receiver"];
    $idService = $request["idService"];
    $message = $request["message"];
    $date = getDateStr();

    $query = "insert into message (`idSender`, `idReceiver`, `idService`, `message`, `date`) values ('$idSender', '$idReceiver', '$idService', '$message', '$date')";
    connection()->query($query);

    $query = "select * from ft where idLogin='$idReceiver'";
    $ft = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
    if ($ft != null) {
        sendNotification($ft["token"], $message, "Mensaje nuevo");
    }

    json("success", "success");
} else if (isset($request["conversation"])) {
    if (isset($request["service"])) {
        $id = $request["service"];
        $query = "select * from message where idService='$id'";
        $data = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC);
        foreach ($data as $key => $value) {
            $query = "select * from user where idLogin='" . $value["idSender"] . "'";
            $sender = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
            array_push_key($data[$key], "sender", $sender);
            $query = "select * from user where idLogin='" . $value["idReceiver"] . "'";
            $receiver = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
            array_push_key($data[$key], "receiver", $receiver);
        }
        json("data", $data);
    } else {
        $query = "select * from message";
        $data = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC);
        foreach ($data as $key => $value) {
            $query = "select * from user where idLogin='" . $value["idSender"] . "'";
            $sender = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
            array_push_key($data[$key], "sender", $sender);
            $query = "select * from user where idLogin='" . $value["idReceiver"] . "'";
            $receiver = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
            array_push_key($data[$key], "receiver", $receiver);
        }
        json("data", $data);
    }
}