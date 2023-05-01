# alice-sdk
### инструментарий для декларативной, я надеюсь, разработки навыков для Яндекс Алисы

## Example
```java
Button helloButton = ButtonResponseBuilder.of().setTitle("Hello").setHide(true).setUrl("https://hello.example.com/").assemble();
Button secondButton = ButtonResponseBuilder.of().setTitle("Second").setHide(false).assemble();

RequestManager.addRequestListener(serverRequest -> ServerResponseBuilder.ofDefault()
        .save(false, false, false)
        .getRequiredResponseBuilder()
        .setData("Добро пожаловать!", "добр+о пож+аловать")
        .setEndSession(false)
        .addButton().setTitle("First").setHide(true).setUrl("https://first.example.com/").postResponseItemBuilder()
        .addButton().setTitle("Привет").setHide(false).postResponseItemBuilder()
        .addButtons(List.of(helloButton, secondButton))
        .back().assemble()
);

Alice.getInstance(8080)
        .loggingRequests()
        .start();
```