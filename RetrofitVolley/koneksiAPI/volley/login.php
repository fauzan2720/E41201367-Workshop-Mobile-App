<?php 

require("../retrofit/config.php");

$no_telp = $_POST['no_telp'];
$email = $_POST['email'];
$password = $_POST['password'];

$query_insert = "SELECT * FROM akun WHERE (no_telp = '$_POST[no_telp]' OR email = '$_POST[email]') AND password = '$_POST[password]'";
$result = mysqli_query($conn, $result);

if ($data = mysqli_fetch_array($result)) {
    echo '1';
}

?>