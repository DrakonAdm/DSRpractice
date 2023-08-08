import React, {useContext, useState} from 'react';
import {Context} from "../../index";
import {Navigate, NavLink, useNavigate} from "react-router-dom";
import {AUTH_ROUTE, CHANGE_PROFILE_ROUTE, PROFILE_ROUTE} from "../../utils/consts";
import Card from "react-bootstrap/Card";
import {Container, Form} from "react-bootstrap";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";
import {login} from "../../api/authAPI";
import {inputExceptionAuth} from "../../exeptions/inputException";
import {updatePassword, updateProfileData} from "../../api/personAPI";

const ChangeProfile = () => {
    const navigate = useNavigate();
    const ctx = useContext(Context);
    const userStore = ctx.user

    const user = userStore.user ? userStore.user : {}
    const profile = user.personProfileDto ? user.personProfileDto : {}


    const [name, setName] = useState(profile.name ? profile.name : '')
    const [surname, setSurname] = useState(profile.surname ? profile.surname : '')
    const [date, setDate] = useState(profile.date ? profile.date : '')
    const [phone, setPhone] = useState(profile.phone ? profile.phone : '')
    const [city, setCity] = useState(profile.city ? profile.city : '')
    const [country, setCountry] = useState(profile.country ? profile.country : '')
    const [description, setDescription] = useState(profile.description ? profile.description : '')

    const [pass, setPass] = useState('')


    if (!userStore || !user || !userStore.isAuth){
        return <Navigate to={AUTH_ROUTE} replace/>
    }

    const updateData = async () => {
        try {
            const responce = updateProfileData(profile.id, name, surname, date, phone, city, country, description)
            let data;
            data = (await responce).data;

            user.personProfileDto = data.personProfileDto;
            navigate(CHANGE_PROFILE_ROUTE);
        } catch (error) {
            alert("Неверный формат данных")
        }
    };

    const updatePass = async () => {
        try {
            const responce = updatePassword(profile.id, pass)
            let data;
            data = (await responce).data;

            user.personProfileDto = data.personProfileDto;
            navigate(CHANGE_PROFILE_ROUTE);
        } catch (error) {
            alert("Неверный формат данных")
            console.error('Update error', error);
        }
    };

    return (
        <Container
            className="d-flex justify-content-center align-items-center"
            style={{height: window.innerHeight - 54}}
        >
            <Card style={{width: 600}} className="p-5">
                <h2 className="m-auto">Редактирование профиля</h2>
                <Form className="d-flex flex-column">
                    <Form.Control
                        className="mt-3"
                        placeholder="name"
                        value={name ? name : ''}
                        onChange={e => setName(e.target.value)}
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder="surname"
                        value={surname ? surname : ''}
                        onChange={e => setSurname(e.target.value)}
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder="date"
                        value={date ? date : ''}
                        onChange={e => setDate(e.target.value)}
                        type="date"
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder="phone"
                        value={phone ? phone : ''}
                        onChange={e => setPhone(e.target.value)}
                        type="phone"
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder="city"
                        value={city ? city : ''}
                        onChange={e => setCity(e.target.value)}
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder="country"
                        value={country ? country : ''}
                        onChange={e => setCountry(e.target.value)}
                    />
                    <Form.Control
                        className="mt-3"
                        placeholder="description"
                        value={description ? description : ''}
                        onChange={e => setDescription(e.target.value)}
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
                        value={pass}
                        onChange={e => setPass(e.target.value)}
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