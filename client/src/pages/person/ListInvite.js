import React, {useContext} from 'react';
import {Context} from "../../index";
import {Link, Navigate, useNavigate} from "react-router-dom";
import {
    ANOTHER_PROFILE_ROUTE,
    AUTH_ROUTE,
    CHANGE_PROFILE_ROUTE,
    LIST_INVITE_ROUTE,
    PROFILE_ROUTE
} from "../../utils/consts";
import {Container} from "react-bootstrap";
import Card from "react-bootstrap/Card";
import ListGroup from "react-bootstrap/ListGroup";
import Button from "react-bootstrap/Button";
import {acceptInvite, deleteInviteAPI, getListFriend, getListInvite} from "../../api/friendAPI";
import {getAnotherProfile, updatePassword} from "../../api/personAPI";

const ListInvite = async () => {
    const navigate = useNavigate();
    const ctx = useContext(Context);
    const userStore = ctx.user
    const user = userStore.user ? userStore.user : {}

    const {anotherUser} = useContext(Context)

    const lists = (await getListInvite(user.personProfileDto.id)).data.unseenInviteFriends

    if (!userStore || !user || !userStore.isAuth) {
        return <Navigate to={AUTH_ROUTE} replace/>
    }


    const openProfile = async (id) => {
        try {
            const responce = getAnotherProfile(user.personProfileDto.id, id)
            const anotherProfile = (await responce).data.anotherProfileDto;
            anotherUser.setAnotherUser(anotherProfile)
            navigate(ANOTHER_PROFILE_ROUTE);
        } catch (error) {
            alert("Неверный формат данных")
        }
    };

    const deleteInvite = async (id) => {
        try {
            const responce = deleteInviteAPI(id)
            navigate(LIST_INVITE_ROUTE);
        } catch (error) {
            alert("Неверный формат данных")
        }
    };

    const addFriend = async (id) => {
        try {
            const responce = acceptInvite(id)
            navigate(LIST_INVITE_ROUTE);
        } catch (error) {
            alert("Неверный формат данных")
        }
    };


    return (
        <Container
            className="d-flex justify-content-center align-items-center"
            style={{height: window.innerHeight - 54}}
        >
            <Card style={{width: 600}} className="p-5">
                <h2 className="m-auto">Заявки в друзья</h2>
                <div style={{maxHeight: '400px', overflowY: 'auto'}}>
                    <ListGroup as="ol">
                        {lists.map((invite) => (
                            <ListGroup.Item
                                as="li"
                                key={invite.id}
                                style={{cursor: 'pointer'}}
                            >
                                <div className="ms-2 me-auto">
                                    <div className="fw-bold">
                                        <Button variant="light"
                                                className="text-dark"
                                                onClick={() => openProfile(invite.personId)}>
                                            {invite.name} {invite.surname}
                                        </Button>
                                    </div>
                                    {invite.date}
                                </div>
                                <div>
                                    <Button variant="success" onClick={() => addFriend(invite.id)}>Добавить</Button>
                                    <Button variant="danger" className="mx-2"
                                            onClick={() => deleteInvite(invite.id)}>Отказать</Button>
                                </div>
                            </ListGroup.Item>
                        ))}
                    </ListGroup>
                </div>
            </Card>
        </Container>
    );
};

export default ListInvite;