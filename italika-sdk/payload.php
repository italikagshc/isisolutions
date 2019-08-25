<?php

header("Access-Control-Allow-Origin: *");
//header('Access-Control-Allow-Credentials: true');
//header("Access-Control-Allow-Headers: X-Requested-With");
header('Content-Type: text/html; charset=utf-8');
//header('P3P: CP="IDC DSP COR CURa ADMa OUR IND PHY ONL COM STA"');
header('Content-Type: application/json; charset=utf-8');

$start_time = microtime(true);

include_once __DIR__ . '/database/Config.php';
include_once __DIR__ . '/database/Connection.php';
include_once __DIR__ . '/data.php';

const _MODE_SANDBOX = true;
const _MODE_PRODUCTION = false;

const _VALID_PARAMETERS = true;
const _VALID_KEY = true;

$request = array_merge($_POST, $_GET);

if (_VALID_PARAMETERS) {
    if (count($request) == 0) {
        json("Exception", "KOKYBE No parameters valid");
    }
}

if (_MODE_PRODUCTION) {
    if (_VALID_KEY) {
        if (isset($request["key"])) {
            if (!validKey($request["key"])) {
                json("Exception", "Key no valid for KOKYBE");
            }
        } else {
            json("Exception", "Key no found for KOKYBE");
        }
        unset($request["key"]);
    } else {
        if (isset($request["key"])) {
            unset($request["key"]);
        }
    }
}

use database\Connection;

try {
    $connection = null;
    $connection = new Connection();
} catch (Exception $exc) {
    json("Exception", $exc->getMessage());
}

/**
 * 
 * @global \database\Connection $con
 * @return \database\Connection
 */
function connection() {
    global $connection;
    return $connection;
}

function sendNotification($token, $body, $title, $cls = null, $id = null) {
    if ($cls == null) {
        $array = [
            "to" => $token,
            "notification" => [
                "body" => $body,
                "title" => $title,
                "icon" => "ic_launcher"
            ],
            "android" => [
                "priority" => "normal"
            ]
        ];
    } else {
        $array = [
            "to" => $token,
            "data" => [
                "action" => $cls,
                "key" => $id
            ],
            "notification" => [
                "body" => $body,
                "title" => $title,
                "icon" => "ic_launcher"
            ],
            "android" => [
                "priority" => "normal"
            ]
        ];
    }


    $data = json_encode($array);
    $url = 'https://fcm.googleapis.com/fcm/send';
    $server_key = 'AAAAkE0A5cQ:APA91bFRG2Mzbufslovd1ykR3mx473QSVjdqOxn8A2eiWi8pZh2I9zxWpjxxcBEx4OZYOagP2_djPardkWjKFkWuGrsRdFquOaNRdzpB6sNXUybjAvqokMVocJ0ppzavtWIZKCiOOoVN';
    $headers = array(
        'Content-Type:application/json',
        'Authorization:key=' . $server_key
    );
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
    $result = curl_exec($ch);
    if ($result === FALSE) {
        //die('Oops! FCM Send Error: ' . curl_error($ch));
    }
    curl_close($ch);
}
