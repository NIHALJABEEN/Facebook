<?php


$con = mysqli_connect('13.76.209.146/','root','baabteadmin123!','Nihal_Facebook');

if( isset($_REQUEST['FirstName'])&&isset($_REQUEST['Surname'])&&isset($_REQUEST['Email_Phone'])&&isset($_REQUEST['Gender'])&&isset($_REQUEST['Bdate'])&&isset($_REQUEST['Bmonth'])&&isset($_REQUEST['Byear'])&&isset($_REQUEST['Password']))
{
	$fname=$_REQUEST['FirstName'];
	$sname=$_REQUEST['Surname'];
	$email=$_REQUEST['Email_Phone'];
	$gender=$_REQUEST['Gender'];
	$date=$_REQUEST['Bdate'];
	$month=$_REQUEST['Bmonth'];
	$year=$_REQUEST['Byear'];
    $pswd=$_REQUEST['Password'];
	//echo "$fname,$sname,$email,$gender,$date,$month,$year,$pswd";
	$query="insert into Registration (first_name,sur_name,phn_or_email_addrs,gender,Day,Month,Year,password) values ('$fname','$sname','$email','$gender','$date','$month','$year','$pswd')";

	$result=mysqli_query($con,$query);
	

	if ($result)
	{
		$rw=mysqli_fetch_assoc($result);
	

		$rw[0]['ResponseCode']="200";
		$rw[0]['Msg']="Success";
		echo json_encode($rw);
		
	}
	else
	{
		$rw[0]['ResponseCode']="500";
		$rw[0]['Msg']="Registration failed";
		echo json_encode($rw);
		
	}
mysqli_close($con);
}

else
{
	$er = array("ResponseCode"=>"500","Msg"=>"Some data is missing");
    echo json_encode($er);
}

// $conn = mysqli_connect('13.76.213.131','root','baabteadmin123!','Nihal_Facebook');

// if(isset($_REQUEST['first_name'])&&isset($_REQUEST['sur_name'])&&isset($_REQUEST['phn_or_email_addrs'])&&(isset($_REQUEST['gender'])&&(isset($_REQUEST['date_of_birth'])&&isset($_REQUEST['password']))
// {
// 	$fname=$_REQUEST['first_name'];
// 	$sname=$_REQUEST['sur_name'];
// 	$phn_or_email=$_REQUEST['phn_or_email_addrs'];
// 	$gndr=$_REQUEST['gender'];
// 	$date=$_REQUEST['date_of_birth'];
// 	$pswd=$_REQUEST['password'];

	
// 	$query = "insert into Registration (first_name,sur_name,phn_or_email_addrs,gender,date_of_birth,password) values('$fname','$sname','$phn_or_email','$gndr','$date','$pswd')";

// 	$result = mysqli_query($conn,$query);
// 	if($result)
// 	{
// 		$rw[]=mysqli_fetch_assoc($result);
// 		$rw[0]['ResponseCode']="200";
// 		$rw[0]['Msg']="Success";

// 		echo json_encode($rw);
// 	}
// 	else
// 	{
// 		$rw[]=mysqli_fetch_assoc($result);
// 		$rw[0]['ResponseCode']="500";
// 		$rw[0]['Msg']="Failed";

// 		echo json_encode($rw);
// 	}
// 	mysqli_close($conn);
// }
// 	else{
//   $er = array("ResponseCode"=>"500","Msg"=>"Some data is missing");
//   echo json_encode($er);
// }
?>