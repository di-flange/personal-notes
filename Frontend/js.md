Определение языка пользователя: 

    (navigator.language || navigator.userLanguage)
        .toLowerCase().split(/[_-]/)[0];

Относительный путь к файлу:

    var scripts = document.getElementsByTagName('script');
    var currentScriptPath = scripts[scripts.length - 1].src;
    
    currentScriptPath.substring(0, currentScriptPath.lastIndexOf('/') + 1) + target;
    
