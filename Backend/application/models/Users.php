<?php

namespace application\models;

use application\core\Model;

/**
 * 
 */
class Users extends Model
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


	public function loginUser($post, $token) {

        $p = [
            'email' => $post['email'],
        ];

        $passHash = $this->db->column('SELECT `password` FROM `users` WHERE email = :email', $p);
        $id = $this->db->column('SELECT `id` FROM `users` WHERE email = :email', $p);
        $this->userId = $id;

        $passVerify = password_verify($post['password'], $passHash);

        if (!$id) {
            $this->error = "Пользователя с таким Email не существует!";
            return false;
        } elseif (empty($passVerify)) {
            $this->error = "Вы ввели неправильный пароль!";
            return false;
        }

        // Берется id юзера и идет запись в таблицу авторизованности
        $newParams = [
            'id' => '',
            'user_id' => $id,
            'token' => $token,
        ];
        $this->db->query('INSERT INTO `usersauth` VALUES(:id, :user_id, :token)', $newParams);

        return true;

    }

    public function loadUserData($id) {

        $p = [
            'id' => $id,
        ];
        
        $data = $this->db->row('SELECT * FROM `user_profile_info` WHERE user_id = :id', $p);
        
        $m = [
                'name' => $data[0]['name'],
                'surname' => $data[0]['surname'],
                'username' => $data[0]['username'],
                'email' => $data[0]['email'],
                'avatar' => $data[0]['avatar'],
                'role' => $data[0]['role'],
            ];

        return $m;

    }

	public function userIsAuth($post) {

	    $p = [
	        'token' => $post['token'],
        ];
	    $auth = $this->db->row('SELECT * FROM `usersauth` WHERE token = :token', $p);

	    if ($auth) {

            $id = $this->db->column('SELECT `id` FROM `usersauth` WHERE token = :token', $p);
            $this->userId = $id;

	        return true;
        } else {
	        return false;
        }

    }

    public function isUsername($post) {

        $user = $this->isUsernameExists($post['username']);

        if ($user) {
            $this->error = "Пользователь с таким Никнеймом уже существует!";
            return false;
        }
        return true;

    }

	// Регистрация пользователя
	public function regUser($post, $token)
	{

	    /*
	        Проверка на наличие пользователя с таким email в базе,
	        Если такой юзер есть, то вернуть соответствующее сообщение,
	        Иначе - зарегистрировать
         */
	    $user = $this->isUserExists($post['email']);
	    if ($user) {

            $this->error = "Пользователь с таким email уже существует!";
	        return false;

        } else {

            // Передача данных в параметры и регистрация пользователя
            $params = [
                'id' => '',
                'username' => $post['username'],
                'email' => $post['email'],
                'role' => $post['role'],
                'password' => password_hash($post['password'], PASSWORD_DEFAULT),
            ];
            $this->db->query('INSERT INTO `users` VALUES(:id, :username, :email, :role, :password)', $params);

            // Берется id юзера и идет запись в таблицу авторизованности
            $id = $this->db->lastInsertId();
            $this->userId = $id;
            $newParams = [
                'id' => '',
                'user_id' => $id,
                'token' => $token,
            ];
            $this->db->query('INSERT INTO `usersauth` VALUES(:id, :user_id, :token)', $newParams);

            $newParams2 = [
                'id' => '',
                'user_id' => $id,
                'name' => $post['name'],
                'surname' => $post['surname'],
                'username' => $post['username'],
                'avatar' => $post['avatar'],
                'role' => $post['role'],
                'email' => $post['email'],
                'password' => password_hash($post['password'], PASSWORD_DEFAULT),
                'reg_date' => date("d.m.y"),
            ];
            $this->db->query('INSERT INTO `user_profile_info` VALUES(:id, :user_id,  :name, :surname,
                                   :username, :avatar, :role, :email, :password, :reg_date)', $newParams2);

            return true;
        }
		
	}

	// Выход пользователя из приложения(Удаление токена авторизации)
	public function logoutUser($post) {

	    $params = [
	        'token' => $post['token'],
        ];
        $this->db->row('DELETE FROM `usersauth` WHERE token = :token', $params);

        return true;

    }

	// Проверка на наличие пользователя с приведенным Email'ом
	public function isUserExists($email)
	{
	    $p = [
	        'email' => $email,
        ];
		return $this->db->row('SELECT * FROM `users` WHERE email = :email', $p);
	}

    // Проверка на наличие пользователя с приведенным Username'мом
    public function isUsernameExists($username)
    {
        $p = [
            'username' => $username,
        ];
        return $this->db->row('SELECT * FROM `users` WHERE username = :username', $p);
    }
}