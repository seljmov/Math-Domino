<?php

namespace application\controllers;

use application\core\Controller;
use application\lib\Db;


class TeacherController extends Controller
{

	public function __construct($route)
	{
		parent::__construct($route);
	}

	public function createRoomAction() {

        $link = bin2hex(random_bytes(3));

        $create = $this->model->createRoom($_POST, $link);

        if ($create) {
            $m = [
                'link' => $link,
            ];
        } else {

            $m = [
                'link' => 'Ошибка',
            ];
        }

        echo json_encode($m);

    }

    public function roomListAction() {

	    $data = $this->model->roomList($_POST);

	    echo json_encode($data);

    }
}

?>