<?php

namespace application\controllers;

use application\core\Controller;
use application\lib\Db;


class StudentController extends Controller
{

    public function __construct($route)
    {
        parent::__construct($route);
    }

    public function myRoomListAction() {

        $data = $this->model->roomList($_POST);

        echo json_encode($data);

    }

    public function existRoomAction() {

        $room = $this->model->existRoom($_POST);

        if ($room) {
            $m = [
                'response' => "yes",
            ];
        } else {
            $m = [
                'response' => "no",
            ];
        }

        echo json_encode($m);

    }

    public function searchRoomAction() {

        $room = $this->model->searchRoom($_POST);

        echo json_encode($room);

    }

    public function userConnectAction() {

        $connect = $this->model->connectToRoom($_POST);

        if ($connect) {
            $m = [
                'response' => "cool",
            ];
        } else {
            $m = [
                'response' => "В комнате уже максимальное количество участников",
            ];
        }

        echo json_encode($m);

    }

    public function checkUserConnectAction() {

        $connect = $this->model->checkConnect($_POST);

        if ($connect) {
            $m = [
                'response' => "yes",
            ];
        } else {
            $m = [
                'response' => "no",
            ];
        }

        echo json_encode($m);

    }

    public function getStatsAction() {

        $data = $this->model->getStats($_POST);

        echo json_encode($data);
    }

}

?>