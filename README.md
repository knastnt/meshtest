# Тестовое задание: Реализация REST приложения на Spring Boot

[![demo](https://raw.githubusercontent.com/knastnt/meshtest/master/demo.png)](https://meshtest-knastnt.herokuapp.com/)

---


  <strong>
     🎮 <a href="https://meshtest-knastnt.herokuapp.com/" target="_blank">Показать Демо (login:user password:1)</a>
      <br> 🎁 <a href="https://github.com/knastnt/meshtest/archive/v1.0.zip" target="_blank">Скачать проект ZIP</a>
      <br> ☘ <a href="https://github.com/knastnt/meshtest/releases/download/v1.0/meshtest-0.0.1-SNAPSHOT.jar" target="_blank">Скачать JAR</a>
  </strong>


---

**Задание:** <a href="https://docs.google.com/document/d/1FKiNwuKPcfVhAa0p6ipLIlyT8uTrIIo8Ii_UfgmW5GQ/edit" target="_blank">https://docs.google.com/document/d/1FKiNwuKPcfVhAa0p6ipLIlyT8uTrIIo8Ii_UfgmW5GQ/edit</a>

---

##  📣 Описание приложения

Приложение создано на стеке Java 8, Maven, Spring Boot, Spring Web, Spring Security, Spring Data JPA (Hibernate), PostgreSQL, Swagger.

Приложение запускается на ```8010``` порту.

Для работы с приложением необходима авторизация:

#### Пользователь ```user```, пароль ```1```

Принимается авторизация как через форму логина (<a href="http//localhost:8010/login">http//localhost:8010/login</a>), так и через передачу учетных данных в Header`е (Basic Authentification). В случае успешной аутентификации выдаётся Cookie ```secret```, при предъявлении которой повторная передача логина и пароля не требуется.

Завершение сессии происходит путём выполнения GET запроса <a href="http//localhost:8010/exit">http//localhost:8010/exit</a>


---


## REST эндпоинты

> **Note:** Для документирования REST к приложению подключен <a href="https://swagger.io/">Swagger</a>:<br>
> <a href="http://localhost:8010/v2/api-docs">localhost:8010/v2/api-docs</a><br>
> <a href="http://localhost:8010/v3/api-docs">localhost:8010/v3/api-docs</a><br>
> <a href="http://localhost:8010/swagger-ui/index.html">localhost:8010/swagger-ui/index.html</a>
>
####Эндпоинты реализованы согласно <a href="https://docs.google.com/document/d/1FKiNwuKPcfVhAa0p6ipLIlyT8uTrIIo8Ii_UfgmW5GQ/edit" target="_blank">заданию</a> с некоторыми оговорками:

```GET /profiles/last``` возвращает 404 в случае отсутствия последнего добавленного профиля.

```GET /profiles/{ID}``` возвращает 500 в случае ошибки парсинга ID (да, код должен быть другой, но это я уже не реализовывал)

```POST /profiles/get``` возвращает 500 в неверных параметров (да, код должен быть другой, но это я уже не реализовывал)

```GET /error/last``` возвращает 404 в случае отсутствия последней ошибки (и это считается ошибкой, поэтому в след.раз вернёт её =) ).

```created: timestamp``` в миллисекундах
 
```GET /exit Производит закрытия приложение``` у меня не поднялась рука останавливать работу всего приложения по GET - запросу. Поэтому сделал logout  



## Структура проекта


```
├── model/                          # каталог с Entity
|   └── Profile                     # Entity объект профиля
├── repository/                     # интерфейсы репозиториев
|   └── CrudProfileRepository       # наследник JpaRepository с парой дополнительных методов
├── util/                           # утильные классы
│   ├── exception/                  # объекты связанные с исключениями
│   |   ├── ErrorInfo               # объект возвращаемого JSON исключения
│   |   └── NotFoundException       # кастомный эксэпшн
|   └── ExceptionUtil               # утильные методы связанные с обработкой исключений
├── web/                            # классы непосредственно относящиеся к web взаимодействию
│   ├── error/                      # контроллеры исключений
│   |   └── ErrorRestController     # контроллер обрабатывающий GET /error/last
│   ├── profile/                    # контроллеры профиля
│   |   └── ProfileRestController   # контроллер обрабатывающий /profiles
│   ├── ExceptionInfoHandler        # обработчик исключений
│   └── RootController              # контроллер обрабатывающий GET /exit-success
├── MeshtestApplication             # главный класс приложения
└── WebSecurityConfig               # настройки Spring Security
...
── application.properties           # настройки приложения (порт, соединение с БД, генерация DDL, логирование...)

```





* Konstantin Zubrilin | [HH.ru](https://komsomolsk-na-amure.hh.ru/resume/2797db4fff056344b60039ed1f486a50525650) |  456kot@mail.ru
