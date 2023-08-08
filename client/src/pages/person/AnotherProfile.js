import React, {useContext, useState} from 'react';
import {Context} from "../../index";
import {Navigate, useNavigate} from "react-router-dom";
import {AUTH_ROUTE, CHANGE_PROFILE_ROUTE, PROFILE_ROUTE} from "../../utils/consts";
import Card from "react-bootstrap/Card";
import {Container, Form} from "react-bootstrap";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";
import {removeFriendship, responseInvite} from "../../api/friendAPI";
import {updateProfileData} from "../../api/personAPI";

const AnotherProfile = () => {
    const navigate = useNavigate();
    const ctx = useContext(Context);
    const userStore = ctx.user
    const user = userStore.user ? userStore.user : {}

    const { anotherUser } = useContext(Context);

    const anotherUserStore = ctx.anotherUser
    const profile = userStore.user ? userStore.user : {}

    const [name] = useState(profile.name ? profile.name : '')
    const [surname] = useState(profile.surname ? profile.surname : '')
    const [date] = useState(profile.date ? profile.date : '')
    const [phone] = useState(profile.phone ? profile.phone : '')
    const [city] = useState(profile.city ? profile.city : '')
    const [country] = useState(profile.country ? profile.country : '')
    const [description] = useState(profile.description ? profile.description : '')
    const [isFriend] = useState(profile.description ? profile.description : '')

    const handleAddRemoveFriend = () => {
        if (isFriend){
            try {
                const res = removeFriendship(user.personProfileDto.id, profile.id)
                navigate(PROFILE_ROUTE);
            } catch (error) {
                alert("Неверный формат данных")
            }
        } else {
            try {
                const res = responseInvite(user.personProfileDto.id, profile.id)
                navigate(PROFILE_ROUTE);
            } catch (error) {
                alert("Неверный формат данных")
            }
        }
    };

    const wrightMessage = () => {
        // Logic to add/remove friend goes here
    };

    if (!userStore || !user || !userStore.isAuth){
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
                            variant="outline-success"
                            onClick={wrightMessage}
                        >
                            Написать
                        </Button>
                        <Button
                            variant={isFriend ? 'danger' : 'outline-success'}
                            onClick={handleAddRemoveFriend}
                            className="mt-3"
                        >
                            {isFriend ? 'Удалить из друзей' : 'Добавить в друзья'}
                        </Button>
                    </Row>
                </Form>
            </Card>
        </Container>
    );
};

export default AnotherProfile;