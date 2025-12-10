@echo off
REM Скрипт для запуску JavaFX застосунку з правильним classpath
REM Він автоматично знаходить всі необхідні JAR файли JavaFX

set MAVEN_REPO=%USERPROFILE%\.m2\repository
set JAVA_VERSION=21

REM Формуємо classpath з основними класами
set CLASSPATH=target\classes

REM Додаємо JavaFX JAR файли
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\openjfx\javafx-base\%JAVA_VERSION%\javafx-base-%JAVA_VERSION%.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\openjfx\javafx-base\%JAVA_VERSION%\javafx-base-%JAVA_VERSION%-win.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\openjfx\javafx-controls\%JAVA_VERSION%\javafx-controls-%JAVA_VERSION%.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\openjfx\javafx-controls\%JAVA_VERSION%\javafx-controls-%JAVA_VERSION%-win.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\openjfx\javafx-graphics\%JAVA_VERSION%\javafx-graphics-%JAVA_VERSION%.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\openjfx\javafx-graphics\%JAVA_VERSION%\javafx-graphics-%JAVA_VERSION%-win.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\openjfx\javafx-fxml\%JAVA_VERSION%\javafx-fxml-%JAVA_VERSION%.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\openjfx\javafx-fxml\%JAVA_VERSION%\javafx-fxml-%JAVA_VERSION%-win.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\openjfx\javafx-media\%JAVA_VERSION%\javafx-media-%JAVA_VERSION%.jar
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\openjfx\javafx-media\%JAVA_VERSION%\javafx-media-%JAVA_VERSION%-win.jar

REM Додаємо PostgreSQL (якщо використовується)
set CLASSPATH=%CLASSPATH%;%MAVEN_REPO%\org\postgresql\postgresql\42.7.4\postgresql-42.7.4.jar

REM Формуємо module-path (шлях до JAR файлів)
set MODULE_PATH=%MAVEN_REPO%\org\openjfx\javafx-base\%JAVA_VERSION%;%MAVEN_REPO%\org\openjfx\javafx-controls\%JAVA_VERSION%;%MAVEN_REPO%\org\openjfx\javafx-graphics\%JAVA_VERSION%;%MAVEN_REPO%\org\openjfx\javafx-fxml\%JAVA_VERSION%;%MAVEN_REPO%\org\openjfx\javafx-media\%JAVA_VERSION%

REM Запускаємо застосунок з module-path
echo Запуск застосунку...
java --module-path "%MODULE_PATH%" --add-modules javafx.controls,javafx.fxml,javafx.media -cp "%CLASSPATH%" org.example.demo11.HelloApplication

pause

