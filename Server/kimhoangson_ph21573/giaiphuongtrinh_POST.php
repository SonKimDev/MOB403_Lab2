<?php
    $a = $_POST['a'];
    $b = $_POST['b'];
    $c = $_POST['c'];
    $delta = pow($b,2) - 4*$a*$c;
    $ketqua = "";
    if($a==0){
        if($b==0){
            $ketqua = "Vô số nghiệm";
        }else{
            $x1 = -$c/$b;
            $ketqua = "Phương trình có 1 nghiệm duy nhất: ". $x1;
        }
    }else{
        if($delta<0){
            $ketqua = "Phương trình vô nghiệm";
        }else{
            if($delta==0){
                $x1 = -$b/(2*$a);
                $ketqua = "Phương trình có nghiệm kép: ". $x1;
            }else{
                $x1 = ((-$b+sqrt($delta))/(2*$a));
                $x2 = ((-$b-sqrt($delta))/(2*$a));
                $ketqua = "Phương trình có 2 nghiệm phân biệt: x1 = ". $x1 . "; x2 = ". $x2;
            }
        }
    }

    echo $ketqua;
?>