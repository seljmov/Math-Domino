<?php 

// Указываем пространство имен
namespace application\core;

use application\core\View;

// Класс для роутинга url'ов
class Router
{
	protected $routes = [];
	protected $params = [];


	public function __construct()
	{
		$arr = require 'application/config/routes.php';
		foreach ($arr as $key => $val) {
			$this->add($key, $val);
		}
	}

	/* Делаем название маршрута регулярным выражением
	И добавляем его вместе с массивом данных в массив Routes  */
	public function add($route, $params)
	{
		 $route = preg_replace('/{([a-z]+):([^\}]+)}/', '(?P<\1>\2)', $route);
		$route = '#^'.$route.'$#';
		$this->routes[$route] = $params;
	}

	/* Проверяем, прописан ли у нас такой путь в файле \config\routes.php
	Если путь есть, то true, иначе - false */
	public function match()
	{
		$url = trim($_SERVER['REQUEST_URI'], '/');
		foreach ($this->routes as $route => $params) {
			if (preg_match($route, $url, $matches)) {
				foreach ($matches as $key => $match) {
					if (is_string($key)) {
						if (is_numeric($match)) {
							$match = (int) $match;
						}
						$params[$key] = $match;
					}	
				}
				$this->params = $params;
				return true;
			}
		}
		return false;
	}

	/* Проверяем, если все окей, то переводим по полученной ссылке
	Иначе выводим соответствующее сообщение об ошибке */
	public function run()
	{
		if ($this->match()) {
			$path = 'application\controllers\\'.ucfirst($this->params['controller']).'Controller';
			if (class_exists($path)) {		// Проверяем наличие класса
				$action = $this->params['action'].'Action'; 
				if (method_exists($path, $action)) {		// Проверяем наличие метода
					$controller = new $path($this->params);
					$controller->$action();
				} else {
					 //View::errorCode(404);
					echo "Не сущ-ет странцы";
				}
			} else {
				//View::errorCode(404);
				echo "Не сущ-ет контроллер<br> $path";
			}
		} else {
			//View::errorCode(404);
			echo "Нет совпадений с листингом данных из routes.php";
		}
	}
}