<h1 align="center">filmorate group project</a>
<h3 align="center">Групповой проект сервиса по рекомендации фильмов</h3>

Проект представляет собой веб-сервис, который помогает пользователям делиться своими любимыми фильмами и находить новые на основе рекомендаций.

Сервис позволяет:
1) создавать пользовательские профили;
2) добавлять друзей;
3) ставить лайки фильмам;
4) составление рекомендаций на основе пользователей с похожими предпочтениями;
5) поиск фильмов;
6) просматривать ленту событий, которая конспектирует действия пользователя;
7) сортировать пользователей с общими фильмами и фильмы по популярности.

Примеры HTTP запросов:
1) Получение всех режиссеров: (GET) {{baseUrl}}/directors
2) Получение режиссера по id: (GET) {{baseUrl}}/directors/{id}
3) Добавление лайка фильму: (PUT) {{baseUrl}}/films/{id}/like/{userId}
4) Список популярных фильмов жанра: (GET) {{baseUrl}}/films/popular?genreId=2
5) Список популярных фильмов одного года выпуска: (GET) {{baseUrl}}/films/popular?year=1999
6) Список популярных фильмов одного года выпуска и жанра: (GET) {{baseUrl}}/films/popular?year=1999&genreId=1

В проекте используется базв данных H2 для хранения информации о пользователях, фильмах и связях между ними. Данная база данных выбрана не случайно, она не требует отдельной установки и её можно встроить в приложение — достаточно добавить зависимость в сборку проекта.
ER диаграмма базы данных
![plot](./ER-model.png)
Ссылка на ER модель базы данных программы: https://dbdiagram.io/d/64a45f7c02bd1c4a5e7ec08a

Примеры запросов к БД:
1) Получение всех режиссеров одного фильма:
   SELECT * FROM directors WHERE id IN (SELECT director_id FROM film_directors WHERE film_id = ?)
2) Добавление события в ленту:
   INSERT INTO events (time_stamp, user_id, event_type, operation, entity_id) VALUES(?, ?, ?, ?, ?)
3) Добавление в друзья:
   INSERT INTO user_friend_list (from_user_id, to_user_id, boolean_status) VALUES(?, ?, ?)
4) Добавление лайка фильму:
   INSERT INTO film_like_list (film_id, user_id) VALUES (?, ?)
5) Добавление отзыва:
   INSERT INTO reviews (content, is_positive, user_id, film_id) VALUES(?, ?, ?, ?)
6) Проверка, является ли отызыв полезный (>0 - полезный, <0 - нет):
   SELECT SUM(CASE WHEN is_positive = true THEN 1 ELSE -1 END) AS count_value
   FROM review_like_list WHERE review_id= %d
   GROUP BY review_id

Это тестовый проект, который помог набраться опыта в командной работе.



