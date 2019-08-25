<?php

namespace database;

use PDO;
use database\Config;

/**
 *
 * @author Anthony nila
 */
class Connection extends PDO {

    public function __construct() {
        try {
            parent::__construct("mysql:host=" . Config::HOST . ";dbname=" . Config::DATABASE, Config::USER, Config::PASSWORD);
            parent::setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch (Exception $ex) {
            echo $ex->getMessage();
        }
    }
    
}
