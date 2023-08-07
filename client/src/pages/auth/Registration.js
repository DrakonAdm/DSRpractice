import React, {useState} from 'react';
import Card from "react-bootstrap/Card";
import {Container, Form} from "react-bootstrap";
import Row from "react-bootstrap/Row";
import {NavLink, useNavigate} from "react-router-dom";
import {AUTH_ROUTE, PROFILE_ROUTE} from "../../utils/consts";
import Button from "react-bootstrap/Button";
import {login, registration} from "../../api/authAPI";
import {inputException, inputExceptionReg} from "../../exeptions/inputException";

const Registration = () => {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [confirmPassword, setConfirmPassword] = useState('')
    const [name, setName] = useState('')
    const [surname, setSurname] = useState('')
    const [date, setDate] = useState('')

    const navigate = useNavigate();

    const signUp = async () => {
        try {
            const responce = registration(username, password, confirmPassword, name, surname, date)
        } catch (error) {
           await inputExceptionReg(error);
        }
    }

    return (
        <Container
            className="d-flex justify-content-center align-items-center"
            style={{height: window.innerHeight - 54}}
        >
            <Card style={{width: 600}} className="p-5">
                <h2 className="m-auto">Регистрация</h2>
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
                        placeholder="Введите ваше имя..."
                        value={name}
                        onChange={e => setName(e.target.value)}
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder="Введите вашу фамилию..."
                        value={surname}
                        onChange={e => setSurname(e.target.value)}
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder="Введите вашу дату рождения..."
                        value={date}
                        onChange={e => setDate(e.target.value)}
                        type="date"
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder="Введите ваш пароль..."
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        type="password"
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder="Повторите ваш пароль..."
                        value={confirmPassword}
                        onChange={e => setConfirmPassword(e.target.value)}
                        type="password"
                    />
                    <Row className="d-flex justify-content-between mt-3 pl-3 pr-3">
                        <div>
                            Зарегистрировались? <NavLink to={AUTH_ROUTE}>Авторизоваться!</NavLink>
                        </div>
                        <Button
                            variant={"outline-success"}
                            onClick={signUp}
                        >
                            Зарегистрироваться
                        </Button>
                    </Row>

                </Form>
            </Card>
        </Container>
    );
};

export default Registration;