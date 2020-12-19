<?php 

// Разрешаем отображение ошибок всех видов
ini_set('display_errors', 1);
error_reporting(E_ALL);

// Функция, позволяющая дебажить переменные
function debug($str)
{
	echo '<pre>';
	var_dump($str);
	echo '</pre>';
	exit();
}