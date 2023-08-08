import React, {useContext, useState} from 'react';
import {Context} from "../../index";
import {Navigate, useNavigate} from "react-router-dom";
import {ANOTHER_PROFILE_ROUTE, AUTH_ROUTE} from "../../utils/consts";
import {Form} from "react-bootstrap";
import {getAnotherProfile, searchUsername, searchUsers} from "../../api/personAPI";
import ListGroup from "react-bootstrap/ListGroup";

const Search = () => {
    const navigate = useNavigate();
    const ctx = useContext(Context);
    const userStore = ctx.user

    const {anotherUser} = useContext(Context)

    const user = userStore.user ? userStore.user : {}

    const [name, setName] = useState('')
    const [surname, setSurname] = useState('')
    const [date, setDate] = useState('')
    const [phone, setPhone] = useState('')
    const [city, setCity] = useState('')
    const [country, setCountry] = useState('')
    const [birthYear, setBirthYear] = useState('')
    const [username, setUsername] = useState('')
    const [results, setResults] = useState([])

    const search = async () => {
        const responce = searchUsers(user.personProfileDto.id, name, surname, date, phone, city, country, birthYear)
        const res = (await responce).data.briefPersonDto;
        setResults(res)
    }

    const searchEmail = async () => {
        const responce = searchUsername(username)
        const res = (await responce).data.briefPersonDto;
        setResults(res)
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

    if (!userStore || !user || !userStore.isAuth){
        return <Navigate to={AUTH_ROUTE} replace/>
    }

    return (
        <div className="container mt-5">
            <h1>Поиск друзей</h1>
            <Form.Control
                className="mt-3"
                placeholder="name"
                value={name}
                onChange={e => setName(e.target.value)}
            />
            <Form.Control
                className="mt-3"
                placeholder="surname"
                value={surname}
                onChange={e => setSurname(e.target.value)}
            />
            <Form.Control
                className="mt-3"
                placeholder="date"
                value={date}
                onChange={e => setDate(e.target.value)}
                type="date"
            />
            <Form.Control
                className="mt-3"
                placeholder="phone"
                value={phone}
                onChange={e => setPhone(e.target.value)}
                type="phone"
            />
            <Form.Control
                className="mt-3"
                placeholder="city"
                value={city}
                onChange={e => setCity(e.target.value)}
            />
            <Form.Control
                className="mt-3"
                placeholder="country"
                value={country}
                onChange={e => setCountry(e.target.value)}
            />
            <Form.Control
                className="mt-3"
                placeholder="birthYear"
                value={birthYear}
                onChange={e => setBirthYear(e.target.value)}
                type="year"
            />
            <button className="btn btn-success my-3" onClick={search}>Искать</button>
            <Form.Control
                className="mt-3"
                placeholder="username"
                value={username}
                onChange={e => setUsername(e.target.value)}
                type="email"
            />
            <button className="btn btn-success my-3" onClick={searchEmail}>Искать</button>
            <div>Результаты ({ results.length })</div>
            <div>
                { results.map(person => <div>{ person.name }</div>) }
                <ListGroup as="ol">
                    {results.map((briefPersonDto) => (
                        <ListGroup.Item
                            as="li"
                            key={briefPersonDto.id}
                            className="list-group-item-action"
                            onClick={() => openProfile(briefPersonDto.id)}
                            style={{ cursor: 'pointer' }}
                        >
                            <div className="ms-2 me-auto">
                                <div className="fw-bold">
                                    {briefPersonDto.name} {briefPersonDto.surname}
                                </div>
                                {briefPersonDto.date}
                            </div>
                        </ListGroup.Item>
                    ))}
                </ListGroup>
            </div>
        </div>
    );
};

export default Search;