import React, {useContext} from 'react';
import {Context} from "../../index";
import {Link, Navigate} from "react-router-dom";
import {AUTH_ROUTE, PROFILE_ROUTE} from "../../utils/consts";
import {Container} from "react-bootstrap";
import Card from "react-bootstrap/Card";
import ListGroup from "react-bootstrap/ListGroup";
import Button from "react-bootstrap/Button";

const ListInvite = () => {
    const { user, lists } = useContext(Context);

    if (!user.isAuth){
        return <Navigate to={AUTH_ROUTE} replace/>
    }

    const openProfile = (id) => {
        // Logic to open the profile with the given id
        // You can navigate to the profile page using a router or another approach
    };

    const deleteInvite = (id) => {
        // Logic to open the profile with the given id
        // You can navigate to the profile page using a router or another approach
    };

    const addFriend = (id) => {
        // Logic to open the profile with the given id
        // You can navigate to the profile page using a router or another approach
    };

    console.log(lists)

    return (
        <Container
            className="d-flex justify-content-center align-items-center"
            style={{ height: window.innerHeight - 54 }}
        >
            <Card style={{ width: 600 }} className="p-5">
                <h2 className="m-auto">Ваши друзья</h2>
                <div style={{ maxHeight: '400px', overflowY: 'auto' }}>
                    <ListGroup as="ol">
                        {lists.listInvite.map((invite) => (
                            <ListGroup.Item
                                as="li"
                                key={invite.id}
                                style={{ cursor: 'pointer' }}
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
                                    <Button variant="danger" className="mx-2" onClick={() => deleteInvite(invite.id)}>Отказать</Button>
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