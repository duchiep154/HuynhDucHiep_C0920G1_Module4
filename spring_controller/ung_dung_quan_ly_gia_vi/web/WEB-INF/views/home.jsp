
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sandwich Condiments</title>
</head>
<body>
<form action="/save">
    <div align="center">
        <fieldset>
            <h1>Sandwich Condiment</h1>
            <input type="checkbox" name="condiment" value="Lettuce"/>Lettuce
            <input type="checkbox" name="condiment" value="Tomato"/>Tomato
            <input type="checkbox" name="condiment" value="Mustard"/>Mustard
            <input type="checkbox" name="condiment" value="Sprouts"/>Sprouts
            <div align="center">
                <input type="submit" value="Save"/>
            </div>
        </fieldset>
        <h3 style="background-color: #eeff00">${condiments}</h3>
    </div>
</form>
</body>
</html>

