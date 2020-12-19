<?php

return [

	// UsersController

	'users/auth' => [
		'controller' => 'users',
		'action' => 'userAuth',
	],

	'users/registration' => [
		'controller' => 'users',
		'action' => 'userReg',
	],

	'users/login' => [
		'controller' => 'users',
		'action' => 'userLogin',
	],

	'users/logout' => [
		'controller' => 'users',
		'action' => 'userLogout',
	],

    'users/userdata' => [
        'controller' => 'users',
        'action' => 'userData',
    ],

    'users/username' => [
        'controller' => 'users',
        'action' => 'username',
    ],

    'users/response' => [
        'controller' => 'users',
        'action' => 'getMessage',
    ],


    // TeacherController

    'teacher/create_room' => [
        'controller' => 'teacher',
        'action' => 'createRoom',
    ],

    'teacher/room_list' => [
        'controller' => 'teacher',
        'action' => 'roomList',
    ],


    // StudentController

    'student/my_room_list' => [
        'controller' => 'student',
        'action' => 'myRoomList',
    ],

    'student/exist_room' => [
        'controller' => 'student',
        'action' => 'existRoom',
    ],

    'student/search_room' => [
        'controller' => 'student',
        'action' => 'searchRoom',
    ],

    'student/user_connect' => [
        'controller' => 'student',
        'action' => 'userConnect',
    ],

    'student/check_user_connect' => [
        'controller' => 'student',
        'action' => 'checkUserConnect',
    ],

    'student/get_stats' => [
        'controller' => 'student',
        'action' => 'getStats',
    ],

    // RoomController
    'room/gamers_list' => [
        'controller' => 'room',
        'action' => 'gamersList',
    ],

    'room/gamers_list_for_table' => [
        'controller' => 'room',
        'action' => 'gamersListForTable',
    ],

    'room/load_task_list' => [
        'controller' => 'room',
        'action' => 'tasksList',
    ],

    'room/domino_status' => [
        'controller' => 'room',
        'action' => 'dominoStatus',
    ],

    'room/change_domino_status' => [
        'controller' => 'room',
        'action' => 'changeDominoStatus',
    ],

    'room/get_task' => [
        'controller' => 'room',
        'action' => 'getTask',
    ],

    'room/check_task_answer' => [
        'controller' => 'room',
        'action' => 'checkAnswer',
    ],
];