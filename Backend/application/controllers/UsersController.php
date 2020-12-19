<?php

namespace application\controllers;

use application\core\Controller;
use application\lib\Db;


class UsersController extends Controller
{

	public function __construct($route)
	{
		parent::__construct($route);
	}

	public function userAuthAction()
	{
	    $auth = $this->model->userIsAuth($_POST);

	    if ($auth) {
            $m = [
                'message' => $this->model->getUserId(),
            ];
        } else {
            $m = [
                'message' => "bad",
            ];
        }
	    echo json_encode($m);

	}

	public function userLoginAction()
	{

        $token = bin2hex(random_bytes(16));

	    $log = $this->model->loginUser($_POST, $token);

	    if ($log) {
            $t = [
                'message' => $token,
                'userId' => $this->model->getUserId(),
            ];
        } else {
            $t = [
                'message' => $this->model->getError(),
            ];
        }
	    echo json_encode($t);

	}

	public function userDataAction() {

	    $data = $this->model->loadUserData($_POST['user_id']);

	    echo json_encode($data);
    }

    public function usernameAction() {

	    $user = $this->model->isUsername($_POST);

	    if ($user) {
            $m = $this->model->getError();
        } else {
	        $m = "cool";
        }
	    echo $m;

    }

	public function getMessageAction()
	{
        $token = bin2hex(random_bytes(5));
	    $m = [
	        'token' => $token,
        ];
	    echo json_encode($m);
	}

	public function userRegAction()
	{
	    // Генерация токена
        $token = bin2hex(random_bytes(16));

        // Вызывается метод для регистрации пользователя
	    $reg = $this->model->regUser($_POST, $token);

	    /*
	     * Если пользователь успешно зарегистрирован,
	     * то вернуть токен авторизации,
	     * Иначе вернуть сообщение с ошибкой
	     * */
        if ($reg) {
            $t = [
                'token' => $token,
                'userId' => $this->model->getUserId(),
            ];
        } else {
            $t = [
                'token' => $this->model->getError(),
            ];
        }
        echo json_encode($t);

	}

	public function userLogoutAction()
	{

		$logout = $this->model->logoutUser($_POST);

        if ($logout) {
            $m = [
                'message' => "cool",
            ];
        } else {
            $m = [
                'message' => "error",
            ];
        }

        echo json_encode($m);
	}

}

?>