<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Browser content</title>
 </head>
 <body>

 <?php
	$viewer = getenv( "HTTP_USER_AGENT" );

	$browser = "an unidentified browser";
	if( preg_match( "/MSIE/i", "$viewer")){ $browser = "Internet Explorer"; }
	else if( preg_match( "/Netscape/i", "$viewer"))
	{ $browser = "Netscape"; }
	else if( preg_match( "/Opera/i", "$viewer"))
	{ $browser = "Opera"; }

	$platform = "an unidentified operating system";
	if( preg_match( "/Windows/i", "$viewer"))
	{ $platform = "Windows"; }
	else if( preg_match( "/Linux/i", "$viewer"))
	{ $platform = "Linux"; }

	echo( "You're using $browser on $platform" );
 ?>

 </body>
</html>