# DSRpractice

## Описание:
Написать клиент-серверное приложение, предоставляющее следующую функциональность:  
·    Ручная регистрация через электронную почту  
·    Редактирование профиля, просмотр профилей других пользователей  
·    Отправка сообщений пользователям.  
·    Добавление в друзья / удаление из друзей  
·    Возможность поиска пользователей по различным критериям (страна, город, возраст и т.д.)  
 
## Требования:
·    Язык реализации: Java/Kotlin. Версия JVM: 11+. Система сборки: Maven/Gradle.  
·    Серверное приложение: Spring Boot, Spring 5.  
·    Клиентское приложение должно иметь WEB UI. Для реализации можно использовать любые UI фреймворки и библиотеки: jQuery, Angular, React, Bootstrap, etc. или Native JS. Возможно использование Spring MVC.  
·    Использовать Flyway для миграций и начального заполнения базы данных.  
·    Серверное приложение должно публиковать REST-like API, содержащий необходимые для работы клиента вызовы и ресурсы  
·    Клиент и сервер должны обмениваться данными в формате JSON.  
 
## Бонус (не требуется для зачета, но идет в плюс, если выполнено):
·    Добавить Unit тесты для как можно большего числа методов (как сервисных, так и контроллеров).  
·    Используя docker и docker-compose, реализовать задачи для сборки jar и запуска контейнера с приложением.  
·    Авторизация через сторонние ресурсы (VK, Google, и другие. Не менее двух на выбор)  
