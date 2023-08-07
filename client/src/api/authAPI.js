import {$host, $authHost} from "./index";
import jwt_decode from "jwt-decode";

export const registration = async (username, password, confirmPassword, name, surname, date) =>{
    return await $host.post('/api-auth/registration', {
            "username": username,
            "password": password,
            "confirmPassword": confirmPassword,
            "name": name,
            "surname": surname,
            "date": date
        }
    );
}

export const login = async (username, password) =>{
    const response = await $host.post('/api-auth/login', {
        "username": username,
        "password": password,
    })

    return response;
}

export const check = async () =>{
    return await $host.post('/api-auth/me')
}

export const createToken = async (username, password) =>{
    return await $host.post('/api-auth/auth', {
        username, password
    })
}