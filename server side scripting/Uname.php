<?php
$conn = mysqli_connect('13.76.209.146','root','baabteadmin123!','Nihal_Facebook');

if(isset($_REQUEST['Email_Phone']))

{
	$email=$_REQUEST['Email_Phone'];


	$result=mysqli_query($conn,"select first_name from Registration where phn_or_email_addrs='$email'");

	if(mysqli_num_rows($result)==1)

	{
		$rw[]=mysqli_fetch_assoc($result);
  		$rw[0]['ResponseCode']="200";
  		$rw[0]['Msg']="Success";
 		echo json_encode($rw);
	}
	else
	{
		$rw[]=mysqli_fetch_assoc($result);
    	$rw[0]['ResponseCode']="500";
    	$rw[0]['Msg']="Failed  ..!";
  		echo json_encode($rw);
	}
}
else
{
	$er = array("ResponseCode"=>"500","Msg"=>"Name does not exist");
  	echo json_encode($er);
}


?>