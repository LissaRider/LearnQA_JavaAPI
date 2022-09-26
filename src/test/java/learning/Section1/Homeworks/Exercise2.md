# Ex2: Структура HTTP запроса

## Задание

На уроке мы рассказывали о структуре HTTP-запроса.
Определите с помощью `Chrome DevTools` или любым другим способом:

1) в какой части запроса отправляются cookie от клиента к серверу.
2) в какой части ответа сервер указывает клиенту какие cookie нужно выставить.

Ответ в виде текста.

## Ответ

1) **Request Headers**: Куки обычно запоминаются браузером и посылаются в HTTP-заголовке `Cookie` (_en-US_) с каждым
   новым запросом к одному и тому же серверу.
2) **Response Headers**: Получив HTTP-запрос, вместе с HTTP-ответом сервер может отправить заголовок `Set-Cookie`
   в клиентское приложение (браузер).