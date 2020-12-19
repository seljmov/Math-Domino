<?php

namespace application\models;

use application\core\Model;

/**
 *
 */
class Student extends Model
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

    public function roomList($post) {

        $p = [
            'username' => $post['gamer'],
        ];

        $d = $this->db->row('SELECT `roomLink` FROM `gamers` WHERE `username` = :username ORDER BY `id` DESC', $p);

        $l = [];

        foreach ($d as $k => $v) {
            array_push($l, $v['roomLink']);
        }

        $z = [];

        for ($i = 0; $i < count($l); $i++) {

            $param = [
                'link' => $l[$i],
            ];

            $r = $this->db->row('SELECT * FROM `rooms` WHERE link = :link', $param);
            array_push($z, $r[0]);
        }

        return $z;

    }

    public function existRoom($post) {

        $p = [
            'link' => $post['link'],
        ];

        return $this->db->row('SELECT * FROM `rooms` WHERE link = :link', $p);
    }

    public function searchRoom($post) {

        $p = [
            'link' => $post['link'],
        ];

        return $this->db->row('SELECT * FROM `rooms` WHERE link = :link', $p);
    }

    public function connectToRoom($post) {

        $p0 = [
            'link' => $post['link'],
        ];

        $g = $this->db->row('SELECT `gamersCount`, `studentsCount` FROM `rooms` WHERE `link` = :link', $p0);

        $l = [];

        foreach ($g as $k => $v) {
            array_push($l, (int)($v['gamersCount']));
            array_push($l, (int)($v['studentsCount']));
        }

        if ($l[0] < $l[1]) {

            $p = [
                'id' => '',
                'username' => $post['username'],
                'roomName' => $post['roomName'],
                'roomLink' => $post['link'],
                'plus' => 0,
                'minus' => 0,
            ];

            $this->db->query('INSERT INTO `gamers` VALUES(:id, :username, :roomName, :roomLink, :plus, :minus)', $p);


            $p2 = [
                'username' => $post['username'],
                'roomLink' => $post['link'],
            ];

            $c = $this->db->row('SELECT * FROM `gamers` WHERE `username` = :username AND `roomLink` = :roomLink', $p2);

            if ($c) {

                $a = [
                    'link' => $post['link'],
                    'gamersCount' => $l[0]+1,
                ];

                $this->db->query('UPDATE `rooms` SET `gamersCount` = :gamersCount WHERE `link` = :link', $a);

                return true;
            }
            return false;

        } else return false;

    }

    public function checkConnect($post) {

        $p = [
            'username' => $post['username'],
            'roomLink' => $post['link'],
        ];

        $c = $this->db->row('SELECT * FROM `gamers` WHERE `username` = :username AND `roomLink` = :roomLink', $p);

        if ($c) {
            return true;
        }
        return false;

    }

    public function getStats($post) {

        $p = [
            'username' => $post['username'],
        ];

        $stats = $this->db->row('SELECT * FROM `user_profile_info` WHERE `username`=:username', $p);

        $i = [
            'points' => $stats[0]['points'],
            'plus' => $stats[0]['plus'],
            'minus' => $stats[0]['minus'],
        ];

        return $i;
    }


}