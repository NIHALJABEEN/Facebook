<?php
$conn = mysqli_connect('13.76.213.131','root','baabteadmin123!','Nihal_Facebook');

if(isset($_REQUEST['Email_Phone']) && isset($_REQUEST['Password']))
{

$email = $_REQUEST['Email_Phone'];
$pswd = $_REQUEST['Password'];

 //$query = "select email,password from Registration where email='$user_email'and password='$user_password'";

 $result = mysqli_query($conn,"select phn_or_email_addrs,password from Registration where phn_or_email_addrs='$email'and password='$pswd'");

 if(mysqli_num_rows($result)==1)
 {
  $rw[]=mysqli_fetch_assoc($result);
  $rw[0]['ResponseCode']="200";
  $rw[0]['Msg']="Success";
 	echo json_encode($rw);
 }

else
 {
  $query = "select email,password from Registration where phn_or_email_addrs='$email'";
  $result = mysqli_query($conn,$query);

  if(mysqli_num_rows($result)>0)
  {
  	$rw[]=mysqli_fetch_assoc($result);
    $rw[0]['ResponseCode']="500";
    $rw[0]['Msg']="Password Incorrect!";
  	echo json_encode($rw);
  }

   else
  {
    $er = array("ResponseCode"=>"404","Msg"=>"Email id does not exist");
   	echo json_encode($er);
  }

 }
}

else
{
  $er = array("ResponseCode"=>"500","Msg"=>"Email or password is not defined");
  echo json_encode($er);
}



?>