import React, {useContext, useState} from 'react';
import {Context} from "../../index";
import {Navigate, useNavigate} from "react-router-dom";
import {AUTH_ROUTE, CHANGE_PROFILE_ROUTE, REGISTRATION_ROUTE} from "../../utils/consts";
import {Container, Form} from "react-bootstrap";
import Card from "react-bootstrap/Card";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";

const Profile = () => {
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
    const navigate = useNavigate();

    if (!user.isAuth){
        return <Navigate to={AUTH_ROUTE} replace/>
    }

    return (
        <Container
            className="d-flex justify-content-center align-items-center"
            style={{height: window.innerHeight - 54}}
        >
            <Card style={{width: 600}} className="p-5">
                <h2 className="m-auto">{name} {surname}</h2>
                <Form className="d-flex flex-column">
                    <Row className="mt-3">
                        Дата рождения: {date}, Телефон: {phone}
                    </Row>
                    <Row className="mt-3">
                        Город {city}, Страна {country}
                    </Row>
                    <Row className="mt-3">
                        Описание:
                    </Row>
                    <Row className="mt-3">
                        <Form className="border p-4">
                            {description}
                        </Form>
                    </Row>
                    <Row className="d-flex justify-content-between mt-3 pl-3 pr-3">
                        <Button
                            variant={"outline-success"}
                            onClick={() => navigate(CHANGE_PROFILE_ROUTE)}
                        >
                            Изменить профиль
                        </Button>
                    </Row>
                </Form>
            </Card>
        </Container>
    );
};

export default Profile;