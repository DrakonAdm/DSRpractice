import {$authHost, $host} from "./index";


export const profile = async (username) =>{
    return await $authHost.get(`/api-dataPerson/profile?email=${username}`
    );
}

export const updateProfileData = async (id, name, surname, date, phone, city, country, description) =>{
    return await $authHost.post('/api-auth/update', {
            "id": id,
            "name": name,
            "surname": surname,
            "date": date,
            "phone": phone,
            "city": city,
            "country": country,
            "description": description,
        }
    );
}

export const updatePassword = async (id, pass) =>{
    return await $authHost.post(`/api-dataPerson/updatePassword?id=${id}&pass=${pass}`);
}

export const searchUsers = async (id, name, surname, date, phone, city, country, birthYear) =>{
    return await $authHost.post(`/api-dataPerson/search${id}`, {
            "name": name,
            "surname": surname,
            "date": date,
            "phone": phone,
            "city": city,
            "country": country,
            "birthYear": birthYear,
        }
    );
}


export const searchUsername = async (username) =>{
    return await $authHost.get(`/api-dataPerson/by-username?email=${username}`
    );
}


export const getAnotherProfile = async (personId, anotherId) =>{
    return await $authHost.get(`/api-dataPerson/another-profile?personId=${personId}&anotherId=${anotherId}`
    );
}