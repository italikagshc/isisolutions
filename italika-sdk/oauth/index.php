<?php

include_once '../payload.php';

if (isset($request["oauth"])) {
    $phone = $request["phone"];
    $password = $request["password"];
    $query = "select * from login where phone='$phone'";
    $login = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
    if ($login != null) {
        if ($password == $login["password"]) {
            $query = "select * from user where idLogin='" . $login["id"] . "'";
            $user = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
            if ($user != null) {
                array_push_key($login, "user", $user);
            } else {
                array_push_key($login, "user", "no");
            }
            json("login", $login);
        } else {
            json("fail_pwd", "password incorrect");
        }
    }
    json("fail", "fail");
} else if (isset($request["signin"])) {
    $phone = $request["phone"];
    $password = $request["password"];
    $query = "select * from login where phone='$phone'";
    $login = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
    if ($login == null) {
        $query = "insert into login (`$phone`, `$password`) values ('$phone', '$password')";
        connection()->query($query);
        $lastID = connection()->lastInsertId();
        $query = "select * from login where id='$lastID'";
        $login = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
        array_push_key($login, "user", "no");
        json("login", $login);
    } else {
        json("fail", "fail");
    }
}
    