@echo off
REM Скрипт для створення EXE файлу з JAR за допомогою jpackage
REM Вимагає JDK 14 або новіший з jpackage

echo Створення EXE файлу...
echo.

REM Перевірка наявності jpackage
where jpackage >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ПОМИЛКА: jpackage не знайдено!
    echo jpackage доступний тільки в JDK 14+
    echo Завантаж JDK 14 або новіший з https://adoptium.net/
    echo.
    echo Альтернатива: використай Launch4j (див. lab8.fxml)
    pause
    exit /b 1
)

REM Створення EXE
jpackage ^
    --input target ^
    --name Demo11 ^
    --main-jar demo11-1.0-SNAPSHOT.jar ^
    --main-class org.example.demo11.HelloApplication ^
    --type exe ^
    --win-shortcut ^
    --win-menu

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ✅ EXE файл успішно створено!
    echo Файл буде в папці проєкту (або вказаній папці)
) else (
    echo.
    echo ❌ Помилка при створенні EXE файлу
    echo Можливо, потрібні додаткові параметри
)

pause


