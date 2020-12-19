<?php

namespace application\models;

use application\core\Model;

/**
 *
 */
class Room extends Model
{

    private $error;

    public function getError()
    {
        return $this->error;
    }

    public function gamersList($post) {

        $p = [
            'roomLink' => $post['link'],
        ];

        return $this->db->row('SELECT `username` FROM `gamers` WHERE roomLink = :roomLink', $p);
    }

    public function gamersListForTable($post) {

        $p = [
            'roomLink' => $post['link'],
        ];

        return $this->db->row('SELECT `username`, `points`, `plus`, `minus` FROM `gamers` WHERE roomLink = :roomLink ORDER BY `plus` DESC', $p);
    }

    public function tasksList($post) {

        $p = [
            'link' => $post['link'],
        ];

        return $this->db->row('SELECT `title`,`task`,`answer`,`points` FROM `tasks_for_game` WHERE link = :link', $p);
    }

    public function dominoStatus($post) {

        $p = [
            'roomLink' => $post['link'],
            'domino_id' => $post['domino_id'],
        ];


        $list = $this->db->row('SELECT * FROM `domino_status` WHERE roomLink=:roomLink AND domino_id=:domino_id', $p);
        $i = [];

        foreach ($list as $v) {
            $i = [
                'id' => $v['id'],
                'roomLink' => $v['roomLink'],
                'gamer' => $v['gamer'],
                'domino_id' => $v['domino_id'],
                'status' => $v['status'],
                'task1Status' => $v['task1Status'],
                'task2Status' => $v['task2Status'],
            ];
        }

        return $i;
    }

    public function changeDominoStatus($post) {

        $p = [
            'gamer' => $post['gamer'],
            'status' => 2,
            'roomLink' => $post['link'],
            'domino_id' => $post['domino_id'],
        ];
        $this->db->query('UPDATE `domino_status` SET `gamer` = :gamer, `status` = :status WHERE roomLink = :roomLink AND domino_id = :domino_id', $p);

        $p2 = [
            'roomLink' => $post['link'],
            'domino_id' => $post['domino_id'],
        ];


        $resp = $this->db->row('SELECT * FROM `domino_status` WHERE roomLink = :roomLink AND domino_id = :domino_id', $p2);

        if (!$resp) {
            return false;
        }
        return true;
    }

    public function getTask($post) {

        $p = [
            'link' => $post['link'],
            'task_id' => $post['task_id'],
        ];
        
        $list = $this->db->row('SELECT `title`,`task` FROM `tasks_for_game` WHERE link=:link AND task_id=:task_id', $p);

        $i = [
            'title' => $list[0]['title'],
            'task' => $list[0]['task'],
        ];

//        foreach ($list as $v) {
//            $i = [
//                'title' => $v['title'],
//                'task' => $v['task'],
//            ];
//        }

        return $i;
    }

    public function checkAnswer($post) {

        $status = 3;
        $endStatus = 4;
        $trueAnswer = 2;
        $falseAnswer = 1;

        $dominoId = $post['domino_id'];
        $taskId = $post['task_id'];
        $taskNum = $post['task_num'];
        $gamer = $post['gamer'];
        $link = $post['link'];
        $answer = $post['answer'];

        $p = [
            'link' => $link,
            'task_id' => $taskId,
        ];

        $data = [
            'gamer' => $gamer,
            'answer' => $answer,
        ];

        $list = $this->db->row('SELECT * FROM `tasks_for_game` WHERE link=:link AND task_id=:task_id', $p);
        $points = $list[0]['points'];

        $gParam = [
            'username' => $gamer,
            'roomLink' => $link,
        ];
        $gamerData = $this->db->row('SELECT * FROM `gamers` WHERE username=:username AND roomLink=:roomLink', $gParam);

        // Если ответ правильный
        if ($list[0]['answer'] == $data['answer']) {
            // Если это первая задача
            if ($taskNum == 1) {
                // Отправить запрос на сервер и поменять статус задачи и доминошки
                $param1 = [
                    'status' => $status,
                    'task_status' => $trueAnswer,
                    'roomLink' => $link,
                    'gamer' => $gamer,
                    'domino_id' => $dominoId,
                ];
                $this->db->query('UPDATE `domino_status` SET `status`=:status,`task1Status`=:task_status WHERE roomLink=:roomLink AND gamer=:gamer AND domino_id=:domino_id', $param1);
            } else { // Иначе если это вторая задача
                // Отправить запрос на сервер и поменять статус задачи и доминошки
                $param1 = [
                    'status' => $endStatus,
                    'task_status' => $trueAnswer,
                    'roomLink' => $link,
                    'gamer' => $gamer,
                    'domino_id' => $dominoId,
                ];
                $this->db->query('UPDATE `domino_status` SET `status`=:status,`task2Status`=:task_status WHERE roomLink=:roomLink AND gamer=:gamer AND domino_id=:domino_id', $param1);
            }
            // Данные дня получения общего кол-ва очков и плюсов пользователя
            $param0 = [
                'username' => $gamer,
            ];
            $gamerFullData = $this->db->row('SELECT * FROM `user_profile_info` WHERE username=:username', $param0);

            // Данные для обновления кол-ва очков и плюсов в комнате
            $param2 = [
                'points' => $gamerData[0]['points'] + $points,
                'plus' => $gamerData[0]['plus'] + 1,
                'username' => $gamer,
                'roomLink' => $link,
            ];
            $this->db->query('UPDATE `gamers` SET `points`=:points,`plus`=:plus WHERE username=:username AND roomLink=:roomLink', $param2);

            // Данные дня обновления общего кол-ва очков и плюсов пользователя
            $param3 = [
                'points' => $gamerFullData[0]['points'] + $points,
                'plus' => $gamerFullData[0]['plus'] + 1,
                'username' => $gamer,
            ];
            $this->db->query('UPDATE `user_profile_info` SET `points`=:points,`plus`=:plus WHERE username=:username', $param3);

            // Возвращаем истина, так как ответ правильный
            return true;
        } else { // Иначе если ответ неправильный
            // Если это первая задача
            if ($taskNum == 1) {
                // Отправить запрос на сервер и поменять статус задачи и доминошки
                $param1 = [
                    'status' => $status,
                    'task_status' => $falseAnswer,
                    'roomLink' => $link,
                    'gamer' => $gamer,
                    'domino_id' => $dominoId,
                ];
                $this->db->query('UPDATE `domino_status` SET `status`=:status,`task1Status`=:task_status WHERE roomLink=:roomLink AND gamer=:gamer AND domino_id=:domino_id', $param1);
            } else { // Иначе если это вторая задача
                // Отправить запрос на сервер и поменять статус задачи и доминошки
                $param1 = [
                    'status' => $endStatus,
                    'task_status' => $falseAnswer,
                    'roomLink' => $link,
                    'gamer' => $gamer,
                    'domino_id' => $dominoId,
                ];
                $this->db->query('UPDATE `domino_status` SET `status`=:status,`task2Status`=:task_status WHERE roomLink=:roomLink AND gamer=:gamer AND domino_id=:domino_id', $param1);
            }
            // Данные дня получения общего кол-ва очков и минусов пользователя
            $param0 = [
                'username' => $gamer,
            ];

            $gamerFullData = $this->db->row('SELECT * FROM `user_profile_info` WHERE username=:username', $param0);

            // Данные для обновления кол-ва очков и минусов в комнате
            $param2 = [
                'points' => $gamerData[0]['points'] - $points,
                'minus' => $gamerData[0]['minus'] - 1,
                'username' => $gamer,
                'roomLink' => $link,
            ];
            $this->db->query('UPDATE `gamers` SET `points`=:points,`minus`=:minus WHERE username=:username AND roomLink=:roomLink', $param2);

            // Данные дня обновления общего кол-ва очков и минусов пользователя
            $param3 = [
                'points' => $gamerFullData[0]['points'] - $points,
                'minus' => $gamerFullData[0]['minus'] - 1,
                'username' => $gamer,
            ];
            $this->db->query('UPDATE `user_profile_info` SET `points`=:points,`minus`=:minus WHERE username=:username', $param3);
        }
        // Возвращаем ложь, так как ответ неправильный
        return false;
    }
}