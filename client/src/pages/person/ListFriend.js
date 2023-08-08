import React, {useContext} from 'react';
import {Context} from "../../index";
import {Navigate, useNavigate} from "react-router-dom";
import {ANOTHER_PROFILE_ROUTE, AUTH_ROUTE} from "../../utils/consts";
import ListGroup from 'react-bootstrap/ListGroup';
import Card from "react-bootstrap/Card";
import {Container} from "react-bootstrap";
import {getListFriend} from "../../api/friendAPI";
import {getAnotherProfile} from "../../api/personAPI";



const ListFriend = async () => {
    const navigate = useNavigate();
    const ctx = useContext(Context);
    const userStore = ctx.user

    const {anotherUser} = useContext(Context)

    const user = userStore.user ? userStore.user : {}

    const lists = (await getListFriend(user.personProfileDto.id)).data.friendsList

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


    return (
        <Container
            className="d-flex justify-content-center align-items-center"
            style={{height: window.innerHeight - 54}}
        >
            <Card style={{width: 600}} className="p-5">
                <h2 className="m-auto">Ваши друзья</h2>
                <div style={{maxHeight: '400px', overflowY: 'auto'}}>
                    <ListGroup as="ol">
                        {lists.map((friendsList) => (
                            <ListGroup.Item
                                as="li"
                                key={friendsList.id}
                                className="list-group-item-action"
                                onClick={() => openProfile(friendsList.id)}
                                style={{cursor: 'pointer'}}
                            >
                                <div className="ms-2 me-auto">
                                    <div className="fw-bold">
                                        {friendsList.name} {friendsList.surname}
                                    </div>
                                    {friendsList.date}
                                </div>
                            </ListGroup.Item>
                        ))}
                    </ListGroup>
                </div>
            </Card>
        </Container>
    );
};


export default ListFriend;