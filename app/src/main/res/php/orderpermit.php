<?php
error_reporting(E_ALL);
ini_set('display_errors',1);

include('dbcon2.php');


//POST 값을 읽어온다.
$uid = isset($_POST['uid']) ? $_POST['uid'] : '';
$pw = isset($_POST['pw']) ? $_POST['pw'] : '';
$name = isset($_POST['name']) ? $_POST['name'] : '';
$addr = isset($_POST['addr']) ? $_POST['addr'] : '';
$phone = isset($_POST['phone']) ? $_POST['phone'] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


if ($uid != "" ){

    // POST 방식으로 전달받은 값을 사용하여 SELECT문 실행
    $sql="UPDATE userinfo set pw='$pw', name='$name', addr='$addr', phone='$phone' where uid='$uid'";
    $stmt = $con->prepare($sql);
    $stmt->execute();

    // 데이터 없으면 에러 출력
    if ($stmt){

        echo "update successfully";
    }
    // 데이터 얻으면 배열 생성
    else{

        echo "update Error";
    }
}

?>



<?php

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if (!$android){
    ?>

    <html>
    <body>

    <form action="<?php $_PHP_SELF ?>" method="POST">
        사용자 uid: <input type = "text" name = "uid" />
        <input type = "submit" name="submit" />
    </form>

    </body>
    </html>
    <?php
}


?>
