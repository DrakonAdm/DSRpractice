import React, {useContext} from 'react';
import {Context} from "../../index";
import {Navigate, NavLink} from "react-router-dom";
import {AUTH_ROUTE} from "../../utils/consts";
import Card from "react-bootstrap/Card";
import {Container, Form} from "react-bootstrap";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";

const ChangeProfile = () => {
    const { user } = useContext(Context);
    const {
        name,
        surname,
        date,
        phone,
        city,
        country,
        description,
    } = user.user;

    if (!user.isAuth){
        return <Navigate to={AUTH_ROUTE} replace/>
    }

    const updateData = () => {
        // Logic to add/remove friend goes here
    };

    const updatePass = () => {
        // Logic to add/remove friend goes here
    };

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
                        placeholder={name}
                        // value={name}
                        // onChange={e => setName(e.target.value)}
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder={surname}
                        // value={surname}
                        // onChange={e => setSurname(e.target.value)}
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder={date}
                        // value={date}
                        // onChange={e => setDate(e.target.value)}
                        type="date"
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder={phone}
                        // value={phone}
                        // onChange={e => setPhone(e.target.value)}
                        type="phone"
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder={city}
                        // value={city}
                        // onChange={e => setCity(e.target.value)}
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder={country}
                        // value={country}
                        // onChange={e => setCountry(e.target.value)}
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder={description}
                        // value={description}
                        // onChange={e => setDescription(e.target.value)}
                    />
                    <Row className="d-flex justify-content-between mt-3 pl-3 pr-3">
                        <Button
                            variant={"outline-success"}
                            onClick={updateData}
                        >
                            Сохранить
                        </Button>
                    </Row>

                    <Form.Control
                        className="mt-3"
                        placeholder="Введите новый пароль"
                        // value={pass}
                        // onChange={e => setPassword(e.target.value)}
                        type="password"
                    />

                    <Row className="d-flex justify-content-between mt-3 pl-3 pr-3">
                        <Button
                            variant={'danger'}
                            onClick={updatePass}
                        >
                            Изменить пароль
                        </Button>
                    </Row>

                </Form>
            </Card>
        </Container>
    );
};

export default ChangeProfile;