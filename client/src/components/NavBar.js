import React, {useContext} from 'react';
import {Context} from "../index";
import {Button, Container, Nav, Navbar, NavLink} from "react-bootstrap";
import {
    AUTH_ROUTE,
    CHANGE_PROFILE_ROUTE,
    LIST_FRIEND_ROUTE,
    LIST_INVITE_ROUTE, LIST_MESSAGE_ROUTE,
    PROFILE_ROUTE, REGISTRATION_ROUTE,
    SEARCH_ROUTE
} from "../utils/consts";
import {observer} from "mobx-react-lite";
import {Link, useNavigate} from 'react-router-dom';

const NavBar = observer(() => {
    const { user } = useContext(Context);
    const navigate = useNavigate();

    const logOut = () => {
        user.setUser({});
        user.setIsAuth(false);
        navigate(AUTH_ROUTE);
    };

    return (
        <Navbar bg="dark" variant="dark">
            <Container>
                {user.isAuth ?
                    <>
                        <Link style={{color:'white'}} to={PROFILE_ROUTE}>Профиль</Link>
                        <Nav className="ml-auto" style={{color: 'white'}}>
                            <Button
                                variant={"outline-light"}
                                onClick={() => navigate(LIST_FRIEND_ROUTE)}
                                className="m-2"
                            >
                                Друзья
                            </Button>
                            <Button
                                variant={"outline-light"}
                                onClick={() => navigate(LIST_INVITE_ROUTE)}
                                className="m-2"
                            >
                                Заявки в друзья
                            </Button>
                            <Button
                                variant={"outline-light"}
                                onClick={() => navigate(LIST_MESSAGE_ROUTE)}
                                className="m-2"
                            >
                                Сообщения
                            </Button>
                            <Button
                                variant={"outline-light"}
                                onClick={() => navigate(SEARCH_ROUTE)}
                                className="m-2"
                            >
                                Поиск пользователей
                            </Button>
                            <Button
                                variant={"outline-light"}
                                onClick={() => navigate(CHANGE_PROFILE_ROUTE)}
                                className="m-2"
                            >
                                Изменить профиль
                            </Button>
                            <Button
                                variant={"outline-light"}
                                onClick={() => logOut()}
                                className="m-2"
                            >
                                Выйти
                            </Button>
                        </Nav>
                    </>
                    :
                    <Nav className="ml-auto" style={{color: 'white'}}>
                        <Button variant={"outline-light"}
                                onClick={() => navigate(REGISTRATION_ROUTE)}
                                className="m-2">
                            Регистрация
                        </Button>
                        <Button variant={"outline-light"}
                                onClick={() => navigate(AUTH_ROUTE)}
                                className="m-2">
                            Авторизация
                        </Button>
                    </Nav>
                }
            </Container>
        </Navbar>
    );
});

export default NavBar;