<?php
error_reporting(E_ALL);
ini_set('display_errors',1);

include('dbcon2.php');


//POST 값을 읽어온다.
$uid = isset($_POST['uid']) ? $_POST['uid'] : '';
$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


if ($uid != "" ){

    // POST 방식으로 전달받은 값을 사용하여 SELECT문 실행
    $sql="select * from orderinfo where uid='$uid'";
    $stmt = $con->prepare($sql);
    $stmt->execute();

    // 데이터 없으면 에러 출력
    if ($stmt->rowCount() == 0){

        echo "ID number '";
        echo $uid;
        echo "'가 존재하지 않습니다.";
    }
    // 데이터 얻으면 배열 생성
    else{

        $data = array();

        while($row=$stmt->fetch(PDO::FETCH_ASSOC)){

            extract($row);

            array_push($data,
                array('oid'=>$row["oid"],
                    'tdate'=>$row["tdate"],
                    'menu'=>$row["menu"],
                    'uid'=>$row["uid"],
                    'price'=>$row["price"],
                    'paycon'=>$row["paycon"],
                    'addr'=>$row["addr"],
                    'phone'=>$row["phone"],
                    'delcon'=>$row["delcon"]
                ));
        }


        if (!$android) {
            echo "<pre>";
            print_r($data);
            echo '</pre>';
        }else
        {
            header('Content-Type: application/json; charset=utf8');
            $json = json_encode(array("webnautes"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
            echo $json;
        }
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
        주문자: <input type = "text" name = "uid" />
        <input type = "submit" name="submit" />
    </form>

    </body>
    </html>
    <?php
}


?>
