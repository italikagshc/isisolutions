<?php
include_once '../payload.php';

if (isset($request["getList"])) {
    $query = "select * from message GROUP by idService";
    $data = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC);
    foreach ($data as $key => $value) {
        $query = "select * from user where idLogin='" . $value["idSender"] . "'";
        $sender = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
        array_push_key($data[$key], "sender", $sender);
        $query = "select * from user where idLogin='" . $value["idReceiver"] . "'";
        $receiver = connection()->query($query)->fetchAll(PDO::FETCH_ASSOC)[0];
        array_push_key($data[$key], "receiver", $receiver);
    }
    foreach ($data as $value) {
        ?>
        <a data-rol='viewChat' data-id='<?php echo $value["idService"]; ?>' data-client='<?php echo $value["idSender"]; ?>' href="#" class="list-group-item list-group-item-action media">
            <div class="d-flex justify-content-between mb-1 ">
                <hp class="mb-1 font-weight-bold"><strong><?php echo $value["sender"]["name"]; ?></strong></hp>
                <small><?php echo $value["date"]; ?></small>
            </div> 
        </a>
        <?php
    }
} else if (isset($request["chat"])) {
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
    foreach ($data as $value) {
        if ($value["idReceiver"] != "-1") {
            ?>
            <div class="text-center"><small><?php echo $value["date"]; ?></small></div>
            <div class="d-flex justify-content-start media">
                <p class="grey lighten-3 rounded p-3 w-75">
                    <label class="font-weight-bold mt-0 mb-0"><?php echo $value["sender"]["name"]; ?></label><br>
                    <?php echo $value["message"]; ?>
                </p>
            </div>
            <?php
        } else {
            ?>
            <div class="text-center"><small><?php echo $value["date"]; ?></small></div>
            <div class="d-flex justify-content-end">
                <p class="primary-color rounded p-3 text-white w-75">
                    <label class="font-weight-bold mt-0 mb-0"><?php echo $value["sender"]["name"]; ?></label><br>
                    <?php echo $value["message"]; ?>
                    <label class="float-right font-small mt-2"><small><?php echo ($value["read_c"] == "1") ? "✓✓" : ""; ?></small></label>
                </p>
            </div>
            <?php
        }
    }
}