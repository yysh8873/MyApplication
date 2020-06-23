
<?php

error_reporting(E_ALL);
ini_set('display_errors',1);

include('dbcon2.php');


$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");


if( (($_SERVER['REQUEST_METHOD'] == 'POST') && isset($_POST['submit'])) || $android )
{

    // 안드로이드 코드의 postParameters 변수에 적어준 이름을 가지고 값을 전달 받습니다.

    $oid=isset($_POST['oid'])?$_POST['oid']:"";
    $tdate=$_POST['tdate'];
    $menu=$_POST['menu'];
    $uid=$_POST['uid'];
    $price=$_POST['price'];
    $paycon=$_POST['paycon'];
    $addr=$_POST['addr'];
    $phone=$_POST['phone'];
    $delcon=$_POST['delcon'];
    // uservalue=1, cash=3000


    if(empty($oid)){
        $errMSG = "주문 번호를 입력하세요.";
    }

    if(!isset($errMSG)) // 모두 입력되면
    {
        try{
            // SQL문을 실행하여 데이터를 MySQL 서버의 person 테이블에 저장합니다.
            $stmt = $con->prepare('INSERT INTO orderinfo VALUES(:oid, :tdate, :menu, :uid, :price, :paycon,:addr, :phone, :delcon)');
            $stmt->bindParam(':oid', $oid);
            $stmt->bindParam(':tdate', $tdate);
            $stmt->bindParam(':menu', $menu);
            $stmt->bindParam(':uid', $uid);
            $stmt->bindParam(':price', $price);
            $stmt->bindParam(':paycon', $paycon);
            $stmt->bindParam(':addr', $addr);
            $stmt->bindParam(':phone', $phone);
            $stmt->bindParam(':delcon', $delcon);

            if($stmt->execute())
            {
                $successMSG = "새로운 주문을 추가했습니다.";
            }
            else
            {
                $errMSG = "주문 추가 에러";
            }

        } catch(PDOException $e) {
            die("Database error: " . $e->getMessage());
        }
    }

}

?>


<?php
if (isset($errMSG)) echo $errMSG;
if (isset($successMSG)) echo $successMSG;

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if( !$android )
{
    ?>
    <html>
    <body>

    <form action="<?php $_PHP_SELF ?>" method="POST">
        oid: <input type = "text" name = "oid" />
        tdate: <input type = "text" name = "tdate" />
        menu: <input type = "text" name = "menu" />
        uid: <input type = "text" name = "uid" />
        price: <input type = "text" name = "price" />
        paycon: <input type = "text" name = "paycon" />
        addr: <input type = "text" name = "addr" />
        phone: <input type = "text" name = "phone" />
        delcon: <input type = "text" name = "delcon" />
        <input type = "submit" name = "submit" />
    </form>

    </body>
    </html>

    <?php
}
?>

