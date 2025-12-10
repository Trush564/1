#!/bin/bash
# Скрипт для запуску JavaFX застосунку з правильним classpath (Linux/Mac)
# Він автоматично знаходить всі необхідні JAR файли JavaFX

MAVEN_REPO="$HOME/.m2/repository"
JAVA_VERSION="21"

# Формуємо classpath з основними класами
CLASSPATH="target/classes"

# Додаємо JavaFX JAR файли
CLASSPATH="$CLASSPATH:$MAVEN_REPO/org/openjfx/javafx-base/$JAVA_VERSION/javafx-base-$JAVA_VERSION.jar"
CLASSPATH="$CLASSPATH:$MAVEN_REPO/org/openjfx/javafx-base/$JAVA_VERSION/javafx-base-$JAVA_VERSION-linux.jar"
CLASSPATH="$CLASSPATH:$MAVEN_REPO/org/openjfx/javafx-controls/$JAVA_VERSION/javafx-controls-$JAVA_VERSION.jar"
CLASSPATH="$CLASSPATH:$MAVEN_REPO/org/openjfx/javafx-controls/$JAVA_VERSION/javafx-controls-$JAVA_VERSION-linux.jar"
CLASSPATH="$CLASSPATH:$MAVEN_REPO/org/openjfx/javafx-graphics/$JAVA_VERSION/javafx-graphics-$JAVA_VERSION.jar"
CLASSPATH="$CLASSPATH:$MAVEN_REPO/org/openjfx/javafx-graphics/$JAVA_VERSION/javafx-graphics-$JAVA_VERSION-linux.jar"
CLASSPATH="$CLASSPATH:$MAVEN_REPO/org/openjfx/javafx-fxml/$JAVA_VERSION/javafx-fxml-$JAVA_VERSION.jar"
CLASSPATH="$CLASSPATH:$MAVEN_REPO/org/openjfx/javafx-fxml/$JAVA_VERSION/javafx-fxml-$JAVA_VERSION-linux.jar"
CLASSPATH="$CLASSPATH:$MAVEN_REPO/org/openjfx/javafx-media/$JAVA_VERSION/javafx-media-$JAVA_VERSION.jar"
CLASSPATH="$CLASSPATH:$MAVEN_REPO/org/openjfx/javafx-media/$JAVA_VERSION/javafx-media-$JAVA_VERSION-linux.jar"

# Додаємо PostgreSQL (якщо використовується)
CLASSPATH="$CLASSPATH:$MAVEN_REPO/org/postgresql/postgresql/42.7.4/postgresql-42.7.4.jar"

# Формуємо module-path (шлях до JAR файлів)
MODULE_PATH="$MAVEN_REPO/org/openjfx/javafx-base/$JAVA_VERSION:$MAVEN_REPO/org/openjfx/javafx-controls/$JAVA_VERSION:$MAVEN_REPO/org/openjfx/javafx-graphics/$JAVA_VERSION:$MAVEN_REPO/org/openjfx/javafx-fxml/$JAVA_VERSION:$MAVEN_REPO/org/openjfx/javafx-media/$JAVA_VERSION"

# Запускаємо застосунок з module-path
echo "Запуск застосунку..."
java --module-path "$MODULE_PATH" --add-modules javafx.controls,javafx.fxml,javafx.media -cp "$CLASSPATH" org.example.demo11.HelloApplication

