# paymentservice

### Для запуска приложения требуется:
1. установить плагин Lombok для вашей IDE.
2. выполнить команду package для Server и Client (через консоль mvn package или в Maven Project -> Lifecycle)
3. запустить сервер командой `java -jar server/target/server-1.0-SNAPSHOT.jar 3089`
4. запустить клиента командой `java -jar client/target/client-1.0-SNAPSHOT.jar client/offices.txt  10 http://localhost:3089/pay payments.txt`

> Или же можно указать параметры при запуске проекта в Run/Debug Configurations:   
> для Сервера: указывается порт (например 3089)   
> для Клиента: указывается имя файла со списком точек продаж, количество платежей, url- сервиса приема платежей и имя файла с платежами.

### Описание задач:

Задача 1. Сервис платежей  
Напишите сервис, который по протоколу HTTP принимает 2 вида запросов:
1.	Запрос с сообщением о приеме платежа.
2.	Запрос получения и сброса статистики.

Задача 2. Генерация платежей  
Напишите программу, которая генерирует платежи и передает их в сервис платежей.

