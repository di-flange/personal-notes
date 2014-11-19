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

Подгрузка скриптов: 
    
    var rootPath = 'path_to/scripts/';

    import = function(target, path) {
        path = path || rootPath;
    
        if (typeof target === "string") {
            this.requires(path + target);
            return;
        }
    
        if (typeof target === "boolean") {
            if (target) this.requires(path)
            return;
        }
    
        if (typeof target === "object") {
            Object.keys(target).forEach(function(key) {
                requires(target[key], path + key + (typeof target[key] === 'boolean' ? '' : '/'))
            });
        }
    
    };
    
    requires = function(target) {
        var imported = document.createElement('script');
        imported.src = target;
        document.head.appendChild(imported);
    };
