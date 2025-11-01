<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle}</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; line-height: 1.6; }
        .form-container { max-width: 600px; background: #f8f9fa; padding: 30px;
                         border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        .form-group { margin: 20px 0; }
        label { display: block; margin-bottom: 8px; font-weight: bold; color: #333; }
        input[type="text"], input[type="number"] { width: 100%; padding: 10px;
                 border: 1px solid #ddd; border-radius: 4px; font-size: 16px; box-sizing: border-box; }
        input:focus { border-color: #007bff; outline: none; box-shadow: 0 0 5px rgba(0,123,255,0.3); }
        .checkbox-group { display: flex; align-items: center; }
        .checkbox-group input { width: auto; margin-right: 10px; transform: scale(1.2); }
        button { padding: 12px 30px; background: #007bff; color: white; border: none;
                border-radius: 5px; cursor: pointer; font-size: 16px; font-weight: bold;
                transition: background-color 0.3s; }
        button:hover { background: #0056b3; }
        .back-link { display: inline-block; margin: 10px 10px 20px 0; padding: 10px 15px;
                    background: #6c757d; color: white; text-decoration: none; border-radius: 5px;
                    font-weight: bold; }
        .back-link:hover { background: #545b62; }
        h1 { color: #333; border-bottom: 2px solid #007bff; padding-bottom: 10px; }
    </style>
</head>
<body>
<h1><#if isEdit>✏️ Редактировать книгу<#else>➕ Добавить новую книгу</#if></h1>

<div>
    <a href="${contextPath}/show" class="back-link">📋 Вернуться к списку</a>
    <a href="${contextPath}/" class="back-link">🏠 На главную</a>
</div>

<div class="form-container">
    <form method="post" action="${contextPath}/<#if isEdit>showone<#else>add</#if>">

        <#if isEdit && book??>
        <input type="hidden" name="id" value="${book.id}">
    </#if>

    <div class="form-group">
        <label for="title">Название книги:</label>
        <input type="text" id="title" name="title" value="<#if book??>${book.title}</#if>" required>
    </div>

    <div class="form-group">
        <label for="author">Автор:</label>
        <input type="text" id="author" name="author" value="<#if book??>${book.author}</#if>" required>
    </div>

    <div class="form-group">
        <label for="isbn">ISBN:</label>
        <input type="text" id="isbn" name="isbn" value="<#if book??>${book.isbn}</#if>" required>
    </div>

    <div class="form-group">
        <label for="year">Год издания:</label>
        <input type="number" id="year" name="year" value="<#if book??>${book.year}</#if>"
               min="1000" max="2025" required>
    </div>

    <div class="form-group">
        <label for="genre">Жанр:</label>
        <input type="text" id="genre" name="genre" value="<#if book??>${book.genre}</#if>" required>
    </div>

    <div class="form-group checkbox-group">
        <input type="checkbox" id="available" name="available" <#if book?? && book.available>checked</#if>>
    <label for="available">Доступна для выдачи</label>
</div>

<button type="submit"><#if isEdit>Сохранить изменения<#else>Добавить книгу</#if></button>
</form>
</div>
</body>
</html>