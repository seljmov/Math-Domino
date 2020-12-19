<?php

namespace application\controllers;

use application\core\Controller;
use application\lib\Db;


class RoomController extends Controller
{

    public function __construct($route)
    {
        parent::__construct($route);
    }

    public function gamersListAction() {

        $list = $this->model->gamersList($_POST);

        echo json_encode($list);

    }

    public function gamersListForTableAction() {

        $list = $this->model->gamersListForTable($_POST);

        echo json_encode($list);

    }

    public function tasksListAction() {

        $list = $this->model->tasksList($_POST);

        echo json_encode($list);

    }

    public function dominoStatusAction() {

        $list = $this->model->dominoStatus($_POST);

        echo json_encode($list);

    }

    public function changeDominoStatusAction() {

        $resp = $this->model->changeDominoStatus($_POST);

        if (!$resp) {
            $m = [
                'response' => "bad",
            ];
        } else {
            $m = [
                'response' => "cool",
            ];
        }

        echo json_encode($m);
    }

    public function getTaskAction() {

        $list = $this->model->getTask($_POST);

        echo json_encode($list);

    }

    public function checkAnswerAction() {

        $resp = $this->model->checkAnswer($_POST);

        if (!$resp) {
            $m = [
                'response' => "false",
            ];
        } else {
            $m = [
                'response' => "true",
            ];
        }

        echo json_encode($m);
    }
}
?>