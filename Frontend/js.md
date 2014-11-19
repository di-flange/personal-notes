Определение языка пользователя: 

    (navigator.language || navigator.userLanguage)
        .toLowerCase().split(/[_-]/)[0];

Относительный путь к файлу:

    var scripts = document.getElementsByTagName('script');
    var currentScriptPath = scripts[scripts.length - 1].src;
    
    currentScriptPath.substring(0, currentScriptPath.lastIndexOf('/') + 1) + target;
    
Получение аргументов скрипта (#?):

    var arguments = {};
    var indexOfArg = currentPath.lastIndexOf('#?');
    
    if (indexOfArg) {
        currentPath.substring(indexOfArg + 2, currentPath.length - 1)
            .split('&')
            .forEach(function(part) {
                var values =part.split('=');
                arguments[decodeURIComponent(values[0])] = decodeURIComponent(values[1]);
            });
    }
