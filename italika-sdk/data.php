<?php

if (isset($_SERVER)) {
    $user_agent = $_SERVER['HTTP_USER_AGENT'];
} else {
    global $HTTP_SERVER_VARS;
    if (isset($HTTP_SERVER_VARS)) {
        $user_agent = $HTTP_SERVER_VARS['HTTP_USER_AGENT'];
    } else {
        global $HTTP_USER_AGENT;
        $user_agent = $HTTP_USER_AGENT;
    }
}

function json($root, $element = null) {
    global $start_time;
    if ($element == null) {
        exit(json_encode(array($root), JSON_PRETTY_PRINT));
    } else {
        $end_time = microtime(true);
        //array_push_key($element, "time_execution", round(($end_time - $start_time), 5));
        exit(json_encode(array($root => $element), JSON_PRETTY_PRINT));
    }
}

function array_push_key(&$array, $key, $value) {
    $array[$key] = $value;
}

function getMoney($n) {
    return sprintf('%0.2f', $n);
}

function myUniqId($numStr, $strPrx = "") {
    srand((double) microtime() * rand(1000000, 9999999));
    $arrChar = array();
    $uId = $strPrx;
    for ($i = 65; $i < 90; $i++) {
        array_push($arrChar, chr($i));
        array_push($arrChar, strtolower(chr($i)));
    }
    for ($i = 48; $i < 57; $i++) {
        array_push($arrChar, chr($i));
    }
    for ($i = 0; $i < $numStr; $i++) {
        $r = rand(0, count($arrChar));
        if (isset($arrChar[$r])) {
            $uId .= $arrChar[$r];
        }
    }
    return $uId;
}

function diff($dateI, $dateF = null) {
    $date1 = new DateTime($dateI);
    if ($dateF == null) {
        $date2 = new DateTime(date("Y-m-d"));
    } else {
        $date2 = new DateTime($dateF);
    }
    $diff = $date1->diff($date2);
    return $diff->days;
}

function getDateStr() {
    return date("Y-m-d");
}

function getTimeStr() {
    return date("H:i");
}

function getTimeAStr() {
    return date("H:i:s A");
}

function getDateTimeStr() {
    return date("Y-m-d H:i:s");
}

function getOS() {
    global $user_agent;
    $os_array = array(
        '/windows nt 10/i' => 'Windows 10',
        '/windows nt 6.3/i' => 'Windows 8.1',
        '/windows nt 6.2/i' => 'Windows 8',
        '/windows nt 6.1/i' => 'Windows 7',
        '/windows nt 6.0/i' => 'Windows Vista',
        '/windows nt 5.2/i' => 'Windows Server 2003/XP x64',
        '/windows nt 5.1/i' => 'Windows XP',
        '/windows xp/i' => 'Windows XP',
        '/windows nt 5.0/i' => 'Windows 2000',
        '/windows me/i' => 'Windows ME',
        '/win98/i' => 'Windows 98',
        '/win95/i' => 'Windows 95',
        '/win16/i' => 'Windows 3.11',
        '/macintosh|mac os x/i' => 'Mac OS X',
        '/mac_powerpc/i' => 'Mac OS 9',
        '/linux/i' => 'Linux',
        '/ubuntu/i' => 'Ubuntu',
        '/iphone/i' => 'iPhone',
        '/ipod/i' => 'iPod',
        '/ipad/i' => 'iPad',
        '/android/i' => 'Android',
        '/blackberry/i' => 'BlackBerry',
        '/webos/i' => 'Mobile'
    );
    $os_platform = "Unknown OS Platform";
    foreach ($os_array as $regex => $value) {
        if (preg_match($regex, $user_agent)) {
            $os_platform = $value;
        }
    }
    return $os_platform;
}

function getBrowser() {
    global $user_agent;
    $browser_array = array(
        '/msie/i' => 'Internet Explorer',
        '/firefox/i' => 'Firefox',
        '/safari/i' => 'Safari',
        '/chrome/i' => 'Chrome',
        '/edge/i' => 'Edge',
        '/opera/i' => 'Opera',
        '/netscape/i' => 'Netscape',
        '/maxthon/i' => 'Maxthon',
        '/konqueror/i' => 'Konqueror',
        '/mobile/i' => 'Handheld Browser'
    );
    $browser = "Unknown Browser";
    foreach ($browser_array as $regex => $value) {
        if (preg_match($regex, $user_agent)) {
            $browser = $value;
        }
    }
    return $browser;
}

function getIpPublic() {
    return $_SERVER["HTTP_X_REAL_IP"];
}

function getIPrivate() {
    return $_SERVER["HTTP_X_FORWARDED_BY"];
}

function getInfoSystem() {
    return array("SO" => getOS(), "Browser" => getBrowser(), "public_ip" => getIpPublic());
}

function exception_1() {
    json("Exception", "Error to params invalid");
}
