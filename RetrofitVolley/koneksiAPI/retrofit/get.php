<?php
require("config.php");

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST'){

    $id = $_POST["id"];
    
    $query_insert = "SELECT * FROM akun WHERE id = '$id'";
    $result = mysqli_query($conn, $query_insert);
    $cek = mysqli_affected_rows($conn);

    if ($cek > 0){
        $response["kode"] = 1;
        $response["pesan"] = "Data tersedia";
        $response["data"] = array();

        while ($ambil = mysqli_fetch_object($result)){
            $F["id"] = $ambil->id;
            $F["nama"] = $ambil->nama;
            $F["no_telp"] = $ambil->no_telp;
            $F["email"] = $ambil->email;
            $F["alamat"] = $ambil->alamat;
            $F["password"] = $ambil->password;
            
            array_push($response["data"], $F);
        }
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Data tidak tersedia";
    }
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Tidak ada post data";
}

echo json_encode($response);
mysqli_close($conn);