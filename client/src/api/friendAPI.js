import {$authHost, $host} from "./index";


export const getListFriend = async (id) =>{
    return await $authHost.get(`/api-friends/personId?personId=${id}`
    );
}

export const removeFriendship = async (id, anotherId) =>{
    return await $authHost.post(`/api-friends/remove-friendship?personId=${id}&anotherId=${anotherId}`
    );
}

export const getListInvite = async (id) =>{
    return await $authHost.get(`/api-friends/unseen-invite-friends/personId?personId=${id}`
    );
}

export const responseInvite = async (id, anotherId) =>{
    return await $authHost.post(`/api-friends/add-friendship?personId=${id}&anotherId=${anotherId}`
    );
}


export const acceptInvite = async (inviteId) =>{
    return await $authHost.post(`/api-friends/create-friendship?inviteId=${inviteId}`
    );
}


export const deleteInviteAPI = async (inviteId) =>{
    return await $authHost.post(`/api-friends/delete-invite?inviteId=${inviteId}`
    );
}
