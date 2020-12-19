<?php

namespace application\core;

class View 
{
	public $route;
	public $path;

	public function __construct($route)
	{
		$this->route = $route;
		$this->path = $route['controller'].'/'.$route['action'];
	}

	public function loadingScript($post)
	{
		$path = 'application/views/'.$this->path.'.php';
		require $path;
	}
}

?>