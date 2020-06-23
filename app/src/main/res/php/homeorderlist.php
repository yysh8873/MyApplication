<?php

    error_reporting(E_ALL);
    ini_set('display_errors',1);

    include('dbcon2.php');


    //POST 값을 읽어온다. - 로그인 한 did
    $uid=isset($_POST['uid']) ? $_POST['uid'] : '';
    $uid = "so";

    $android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


    if ($uid != "" ){

         $sql="select * from orderinfo where uid='$uid'";
         $stmt = $con->prepare($sql);
         $stmt->execute();

        if ($stmt->rowCount() == 0){

                echo "'";
                echo $uid,", ",$uid;
                echo "'은 찾을 수 없습니다.";
        }
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
         주문일: <input type = "text" name = "tdate" />
         메뉴: <input type = "text" name = "menu" />
         주문자: <input type = "text" name = "uid" />
         가격: <input type = "text" name = "price" />
         결제상태: <input type = "text" name = "paycon" />
         주소: <input type = "text" name = "addr" />
         핸드폰번호: <input type = "text" name = "phone" />
         배달상태: <input type = "text" name = "delcon" />
         <input type = "submit" />
      </form>

   </body>
</html>
<?php
}


?>