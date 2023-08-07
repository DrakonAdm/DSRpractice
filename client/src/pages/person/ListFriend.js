import React, {useContext} from 'react';
import {Context} from "../../index";
import {Navigate} from "react-router-dom";
import { AUTH_ROUTE } from "../../utils/consts";
import ListGroup from 'react-bootstrap/ListGroup';
import Card from "react-bootstrap/Card";
import {Container} from "react-bootstrap";



const ListFriend = () => {
    const { user, lists } = useContext(Context);

    if (!user.isAuth){
        return <Navigate to={AUTH_ROUTE} replace/>
    }

    const openProfile = (id) => {
        // Logic to open the profile with the given id
        // You can navigate to the profile page using a router or another approach
    };


    return (
        <Container
            className="d-flex justify-content-center align-items-center"
            style={{ height: window.innerHeight - 54 }}
        >
            <Card style={{ width: 600 }} className="p-5">
                <h2 className="m-auto">Ваши друзья</h2>
                <div style={{ maxHeight: '400px', overflowY: 'auto' }}>
                    <ListGroup as="ol">
                        {lists.listFriend.map((friend) => (
                            <ListGroup.Item
                                as="li"
                                key={friend.id}
                                className="list-group-item-action"
                                onClick={() => openProfile(friend.id)}
                                style={{ cursor: 'pointer' }}
                            >
                                <div className="ms-2 me-auto">
                                    <div className="fw-bold">
                                        {friend.name} {friend.surname}
                                    </div>
                                    {friend.date}
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