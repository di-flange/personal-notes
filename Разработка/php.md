Включит отображение ошибок 

    <?php
        error_reporting(E_ALL);
        ini_set('display_errors', TRUE);
        ini_set('display_startup_errors', TRUE);

Отправка писем с использованием SMTP и SSL

    <?php
        require 'PHPMailer/PHPMailerAutoload.php';
    
        $mail = new PHPMailer;
        
        $mail->isSMTP();
        $mail->Host = 'smtp.yandex.ru';
        $mail->SMTPAuth = true;        
        $mail->Username = '-@-.-';       
        $mail->Password = '---';       
        $mail->SMTPSecure = 'ssl';     
        $mail->Port = 465;           
        
        $mail->From = '-@-.-';
        $mail->FromName = '---';
        $mail->addAddress('-@-.-', '---');
        $mail->addReplyTo('-@-.-', '---');
        
        $mail->WordWrap = 50;                                 
        $mail->isHTML(true);                                  
        
        $mail->Subject = '---';
        $mail->Body    = '---. <strong>----</strong>. ---.';
        $mail->AltBody = '---. ---. ---.';
        
        if(!$mail->send()) {
            echo 'Error: ' . $mail->ErrorInfo;
        } else {
            echo 'Message has been sent';
        }
