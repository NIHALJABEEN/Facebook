<?php
$con = mysqli_connect('13.76.209.146','root','baabteadmin123!','Nihal_Facebook');
if( isset($_REQUEST['Address'])&&isset($_REQUEST['Status']))
{

	$adrs=$_REQUEST['Address'];
	$sts=$_REQUEST['Status'];

	$query="insert into CheckIn (Address,Status) values ('$adrs','$sts')";

	$result=mysqli_query($con,$query);
	

	if ($result==1)
	{
		$rw=mysqli_fetch_assoc($result);
	

		$rw[0]['ResponseCode']="200";
		$rw[0]['Msg']="Success";
		echo json_encode($rw);
		
	}
	else
	{
		$rw[0]['ResponseCode']="500";
		$rw[0]['Msg']="CheckIn  failed";
		echo json_encode($rw);
		
	}
mysqli_close($con);
}

else
{
	$er = array("ResponseCode"=>"500","Msg"=>"Some data is missing");
    echo json_encode($er);
}

