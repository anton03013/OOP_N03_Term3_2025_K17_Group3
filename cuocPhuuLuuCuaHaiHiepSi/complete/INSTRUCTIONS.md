# Инструкция по запуску Spring Boot версии игры

## Быстрый запуск

### Вариант 1: Использование batch файла (Windows)
1. Дважды кликните на файл `run.bat`
2. Дождитесь запуска сервера
3. Откройте браузер и перейдите на `http://localhost:8080`

### Вариант 2: Ручной запуск

#### Требования:
- Java 11 или выше
- Maven (опционально, можно использовать встроенный wrapper)

#### Команды:

**Windows:**
```cmd
cd cuocPhuuLuuCuaHaiHiepSi\complete
mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
cd cuocPhuuLuuCuaHaiHiepSi/complete
./mvnw spring-boot:run
```

**С Maven (если установлен):**
```bash
cd cuocPhuuLuuCuaHaiHiepSi/complete
mvn spring-boot:run
```

## Доступ к игре

После успешного запуска:
1. Откройте веб-браузер
2. Перейдите по адресу: `http://localhost:8080`
3. Нажмите кнопку "🎮 Играть сейчас"

## Управление в игре

- **A** - Движение влево
- **D** - Движение вправо  
- **W** - Прыжок
- **Space** - Атака

## Возможные проблемы

### Ошибка "Java not found"
Установите Java 11 или выше и добавьте в PATH

### Ошибка "Port 8080 already in use"
Измените порт в `application.properties`:
```properties
server.port=8081
```

### Ошибка компиляции
Проверьте версию Java:
```bash
java -version
```
Должна быть 11 или выше.

## Структура проекта

```
complete/
├── src/main/java/com/example/servingwebcontent/
│   ├── Classes/           # Оригинальные игровые классы
│   ├── controller/        # REST контроллеры
│   ├── service/          # Игровая логика
│   ├── dto/              # Data Transfer Objects
│   └── texture/          # Текстуры
├── src/main/resources/static/
│   ├── index.html        # Главная страница
│   ├── game.html         # Игровая страница
│   └── texture/          # Веб-доступные текстуры
├── run.bat               # Скрипт запуска для Windows
└── pom.xml               # Maven конфигурация
```

## API Endpoints

- `GET /` - Главная страница
- `GET /game.html` - Игровая страница
- `GET /api/game/state` - Состояние игры
- `POST /api/game/input` - Ввод игрока
- `POST /api/game/reset` - Сброс игры

## Особенности миграции

✅ **Сохранено:**
- Вся логика ООП и наследование
- Игровая механика из BattleController
- Все текстуры и анимации
- Система коллизий и физика

🔄 **Изменено:**
- Интерфейс с Swing на HTML5 Canvas
- Архитектура на REST API
- Рендеринг на JavaScript

🆕 **Добавлено:**
- Веб-интерфейс
- Современный дизайн
- Кроссплатформенность 