<?php 

require("config.php");

$no_telp = $_POST['no_telp'] ??'';
$password = $_POST['password'] ??'';

$response = []; //Data Response

// cek no_telp didalam database
$query_insert = "SELECT * FROM akun WHERE no_telp = ?";
$result = mysqli_query($conn, $result);
// $result = mysqli_query($conn, "SELECT * FROM akun WHERE (no_telp = '$_POST[no_telp]' OR email = '$_POST[email]') AND password = '$_POST[password]'");
if ($query_insert -> rowCount() == 0){
    $response['status'] = false;
    $response['message'] = "Nomor telepon belum terdaftar!";
} else {

    $password = $result['password']; // cek password

    if (strcmp(md5($password), $password) === 0) {
        $response["kode"] = 1;
        $response["pesan"] = "Login berhasil!";
        $response["data"] = array();
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Login gagal!"; 
    }
}

echo json_encode($response);
mysqli_close($conn);

// if ($data = mysqli_fetch_array($result)) {
//     $response["kode"] = 1;
//     $response["pesan"] = "Login berhasil!";
//     $response["data"] = array();
// } else {
//     $response["kode"] = 0;
//     $response["pesan"] = "Login gagal!";
// }

?>