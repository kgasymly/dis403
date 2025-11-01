<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; }
        table { width: 100%; border-collapse: collapse; margin: 20px 0; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #007bff; color: white; font-weight: bold; }
        tr:nth-child(even) { background-color: #f8f9fa; }
        tr:hover { background-color: #e9ecef; }
        .available { color: #28a745; font-weight: bold; }
        .unavailable { color: #dc3545; font-weight: bold; }
        .actions a { margin-right: 10px; text-decoration: none; color: #007bff; font-weight: bold; }
        .actions a:hover { text-decoration: underline; }
        .back-link { display: inline-block; margin: 10px 10px 10px 0; padding: 10px 15px;
                    background: #6c757d; color: white; text-decoration: none; border-radius: 5px;
                    font-weight: bold; }
        .back-link:hover { background: #545b62; }
        .container { max-width: 1200px; margin: 0 auto; }
        h1 { color: #333; border-bottom: 2px solid #007bff; padding-bottom: 10px; }
    </style>
</head>
<body>
<div class="container">
    <h1>📚 Список всех книг</h1>

    <div>
        <a href="${contextPath}/" class="back-link">🏠 На главную</a>
        <a href="${contextPath}/add" class="back-link">➕ Добавить книгу</a>
    </div>

    <#if books?size == 0>
    <p>В библиотеке пока нет книг.</p>
    <#else>
    <table>
        <thead>
        <tr>
            <th>ID</th><th>Название</th><th>Автор</th><th>ISBN</th>
            <th>Год</th><th>Жанр</th><th>Доступность</th><th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <#list books as book>
        <tr>
            <td>${book.id}</td><td>${book.title}</td><td>${book.author}</td>
            <td>${book.isbn}</td><td>${book.year}</td><td>${book.genre}</td>
            <td class="${book.available?then('available', 'unavailable')}">
                ${book.available?then('Доступна', 'Недоступна')}
            </td>
            <td class="actions">
                <a href="${contextPath}/showone?id=${book.id}">Редактировать</a>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</#if>
</div>
</body>
</html>