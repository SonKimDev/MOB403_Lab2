<?php
    $width = $_POST['width'];
    $height = $_POST['height'];

    $chuvi = ($width + $height)*2;
    $dientich = $width * $height;

    echo "Chu vi: ". $chuvi . "; Diện tích: ". $dientich;
?>