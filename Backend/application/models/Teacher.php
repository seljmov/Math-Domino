<?php

namespace application\models;

use application\core\Model;

/**
 *
 */
class Teacher extends Model
{

    private $error;
    private $userId;

    public function getError()
    {
        return $this->error;
    }

    public function getUserId()
    {
        return $this->userId;
    }

    public function createRoom($post, $link) {

        // Передача данных в параметры и создание комнаты
        $params = [
            'id' => '',
            'status' => 'notStarted',
            'link' => $link,
            'roomName' => $post['name'],
            'subject' => $post['subject'],
            'teacher' => $post['teacher'],
            'gamersCount' => 0,
            'studentsCount' => $post['count'],
            'dominoCount' => 2*$post['count'],
            'date' => date("d.m.y"),
        ];
        $this->db->query('INSERT INTO `rooms` VALUES(:id, :status, :link, :roomName, :subject, 
                                                    :teacher, :gamersCount, :studentsCount, :dominoCount, :date)', $params);

        $p = [
            'link' => $link,
        ];
        $cr = $this->db->row('SELECT * FROM `rooms` WHERE link = :link', $p);

        if (!$cr) {
            return false;

        }
        return true;
    }

    public function roomList($post) {

        $p = [
            'status' => 'finished',
            'teacher' => $post['teacher'],
        ];

        $d = $this->db->row('SELECT * FROM `rooms` WHERE `status` != :status AND `teacher` = :teacher ORDER BY `id` DESC', $p);

        return $d;

    }
}