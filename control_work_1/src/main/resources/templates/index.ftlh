<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; }
        .menu { margin: 30px 0; }
        .menu a { display: inline-block; margin: 10px 15px 10px 0; padding: 12px 20px;
                 background: #007bff; color: white; text-decoration: none; border-radius: 5px;
                 font-weight: bold; transition: background-color 0.3s; }
        .menu a:hover { background: #0056b3; }
        .container { max-width: 800px; margin: 0 auto; }
        h1 { color: #333; border-bottom: 2px solid #007bff; padding-bottom: 10px; }
    </style>
</head>
<body>
<div class="container">
    <h1>Добро пожаловать в систему управления библиотекой</h1>

    <div class="menu">
        <a href="${contextPath}/show">📚 Просмотреть все книги</a>
        <a href="${contextPath}/add">➕ Добавить новую книгу</a>
    </div>

    <p>Система позволяет управлять каталогом книг: просматривать, добавлять и редактировать информацию о книгах.</p>
</div>
</body>
</html>