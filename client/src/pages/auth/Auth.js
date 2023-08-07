import React, {useContext, useState} from 'react';
import {Container, Form} from "react-bootstrap";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/Button";
import Row from "react-bootstrap/Row";
import {NavLink, useLocation, useNavigate} from "react-router-dom";
import {AUTH_ROUTE, PROFILE_ROUTE, REGISTRATION_ROUTE} from "../../utils/consts";
import {login} from "../../api/authAPI";
import {Context} from "../../index";
import {inputExceptionAuth} from "../../exeptions/inputException";

const Auth = () => {
    const {user} = useContext(Context)

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate();


    const signIn = async () => {
        try {
            const responce = login(username, password)
            let data;
            data = (await responce).data;

            user.setUser(data)
            user.setIsAuth(true)

            navigate(PROFILE_ROUTE);
        } catch (error) {
            await inputExceptionAuth(error);
        }
    }

    return (
        <Container
            className="d-flex justify-content-center align-items-center"
            style={{height: window.innerHeight - 54}}
        >
            <Card style={{width: 600}} className="p-5">
                <h2 className="m-auto">Авторизация</h2>
                <Form className="d-flex flex-column">
                    <Form.Control
                        className="mt-3"
                        placeholder="Введите ваш email..."
                        value={username}
                        onChange={e => setUsername(e.target.value)}
                        type="email"
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder="Введите ваш пароль..."
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        type="password"
                    />
                    <Row className="d-flex justify-content-between mt-3 pl-3 pr-3">
                        <div>
                            Нет аккаунта? <NavLink to={REGISTRATION_ROUTE}>Зарегистрируйся!</NavLink>
                        </div>
                        <Button
                            variant={"outline-success"}
                            onClick={signIn}
                        >
                            Войти
                        </Button>
                    </Row>

                </Form>
            </Card>
        </Container>
    );
};

export default Auth;