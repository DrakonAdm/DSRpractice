import React, {useContext, useState} from 'react';
import {Context} from "../../index";
import {Navigate, useNavigate} from "react-router-dom";
import {AUTH_ROUTE, CHANGE_PROFILE_ROUTE, REGISTRATION_ROUTE} from "../../utils/consts";
import {Container, Form} from "react-bootstrap";
import Card from "react-bootstrap/Card";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";

const Profile = () => {
    const navigate = useNavigate();
    const ctx = useContext(Context);
    const userStore = ctx.user

    const user = userStore.user ? userStore.user : {}
    const profile = user.personProfileDto ? user.personProfileDto : {}

    const [name] = useState(profile.name ? profile.name : '')
    const [surname] = useState(profile.surname ? profile.surname : '')
    const [date] = useState(profile.date ? profile.date : '')
    const [phone] = useState(profile.phone ? profile.phone : '')
    const [city] = useState(profile.city ? profile.city : '')
    const [country] = useState(profile.country ? profile.country : '')
    const [description] = useState(profile.description ? profile.description : '')


    if (!userStore || !user || !userStore.isAuth){
        return <Navigate to={AUTH_ROUTE} replace/>
    }

    return (
        <Container
            className="d-flex justify-content-center align-items-center"
            style={{height: window.innerHeight - 54}}
        >
            <Card style={{width: 600}} className="p-5">
                <h2 className="m-auto">{name ? name : ''} {surname ? surname : ''}</h2>
                <Form className="d-flex flex-column">
                    <Row className="mt-3">
                        Дата рождения: {date ? date : ''}, Телефон: {phone ? phone : 'Не указан'}
                    </Row>
                    <Row className="mt-3">
                        Город {city ? city : 'Не указан'}, Страна {country ? country : 'Не указана'}
                    </Row>
                    <Row className="mt-3">
                        Описание:
                    </Row>
                    <Row className="mt-3">
                        <Form className="border p-4">
                            {description ? description : ''}
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