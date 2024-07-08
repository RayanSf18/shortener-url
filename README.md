# Shortener-url
E um serviço que permite encurtar URLs longas para torná-las mais compactas e fáceis de compartilhar.

## Exemplo:
O serviço recebe uma chamada para encurtar uma URL.

[POST] {{host}}/shorten-url
```
{
    "url": "https://google.com.br"
}
```

E retorna um JSON com a URL encurtada:

HTTP/1.1 200 OK
```
{
    "url": "https://xxx.com/DXB6V"
}
```

## 🚀 Tecnologias utilizadas
- Java
- Spring Boot
- Spring Data MongoDB
- Docker
- MongoDB

🔍 Baixe o projeto e teste você mesmo na prática.

Developed by Rayan

