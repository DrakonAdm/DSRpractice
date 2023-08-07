export const inputExceptionAuth = async (error) =>{
    let message;
    if (error.response && error.response.status === 401) {
        message = 'Неправильный логин или пароль';
    } else if (error.response && error.response.data && error.response.data.message) {
        message = error.response.data.message;
    } else {
        message = 'Произошла ошибка';
    }
    alert(message)
}

export const inputExceptionReg = (error) => {
    let message;
    if (error.response && error.response.status === 400) {
        message = 'Пользователь с данным именем уже существует';
    } else if (error.response && error.response.data && error.response.data.message) {
        message = error.response.data.message;
    } else {
        message = 'Произошла ошибка';
    }

    alert(message)
}