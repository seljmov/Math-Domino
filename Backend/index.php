<?php

session_start();

require 'application/lib/dev.php';

use application\core\Router;

// Метод, обеспечивающий автозагрузку классов
spl_autoload_register(function ($class)
{
	$path = str_replace('\\', '/', $class.'.php');
	if (file_exists($path)) {
		require $path;
	}
});

$router = new Router();
$router->run();