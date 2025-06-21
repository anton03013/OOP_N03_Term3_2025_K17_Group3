# Миграция с Java Swing на Spring Boot

## Описание проекта

Этот проект представляет собой миграцию игры "Cuộc Phiêu Lưu Của Hai Hiệp Sĩ" с Java Swing на Spring Boot, сохранив всю концепцию ООП и логику игры.

## Сохраненные компоненты

### 🎮 Игровые классы (ООП)
- `Player.java` - базовый класс игрока
- `Enemies.java` - класс врагов (наследуется от Player)
- `Platforms.java` - класс платформ
- `PlatformManager.java` - менеджер платформ

### 🎯 Логика игры
- Вся логика из `BattleController` перенесена в `GameService`
- Сохранены все механики движения, атаки, прыжков
- Сохранена система коллизий и физика
- Сохранена ИИ врагов

### 🎨 Текстуры и анимации
- Все текстуры из папки `texture/` сохранены
- Анимации перенесены в JavaScript для веб-версии
- Сохранены все спрайты игроков и врагов

## Новая архитектура

### Spring Boot компоненты
- `GameService` - основной сервис с игровой логикой
- `GameController` - REST контроллер для API
- `GameState`, `PlayerState`, `PlatformState` - DTO классы
- `GameInput` - DTO для ввода игрока

### Веб-интерфейс
- HTML5 Canvas для рендеринга
- JavaScript для обработки анимаций
- Современный CSS дизайн
- Responsive интерфейс

## API Endpoints

- `GET /api/game/state` - получить состояние игры
- `POST /api/game/input` - отправить ввод игрока
- `POST /api/game/reset` - сбросить игру
- `GET /api/game/textures` - получить пути к текстурам

## Запуск проекта

### Требования
- Java 11 или выше
- Maven или Gradle

### Команды запуска

```bash
# Перейти в папку проекта
cd cuocPhuuLuuCuaHaiHiepSi/complete

# Собрать проект
./mvnw clean install

# Запустить приложение
./mvnw spring-boot:run
```

### Доступ к игре
1. Откройте браузер
2. Перейдите по адресу: `http://localhost:8080`
3. Нажмите "Играть сейчас"

## Управление

- **A/D** - Движение влево/вправо
- **W** - Прыжок
- **Space** - Атака

## Особенности миграции

### ✅ Сохранено
- Вся логика ООП и наследование
- Игровая механика и физика
- Система анимаций
- Все текстуры и спрайты
- Логика боя и урона

### 🔄 Изменено
- Интерфейс с Swing на HTML5 Canvas
- Архитектура с MVC на REST API
- Рендеринг с Java Graphics на JavaScript
- Обработка ввода с KeyListener на Keyboard Events

### 🆕 Добавлено
- Веб-интерфейс
- REST API
- Современный дизайн
- Возможность игры в браузере
- Кроссплатформенность

## Структура файлов

```
src/main/java/com/example/servingwebcontent/
├── Classes/                    # Оригинальные игровые классы
│   ├── Player.java
│   ├── Enemies.java
│   ├── Platforms.java
│   └── PlatformManager.java
├── controller/                 # Spring Boot контроллеры
│   └── GameController.java
├── service/                    # Игровая логика
│   └── GameService.java
├── dto/                        # Data Transfer Objects
│   ├── GameState.java
│   ├── PlayerState.java
│   ├── PlatformState.java
│   └── GameInput.java
└── texture/                    # Текстуры (скопированы в static)

src/main/resources/static/
├── index.html                  # Главная страница
├── game.html                   # Игровая страница
└── texture/                    # Веб-доступные текстуры
    ├── Sprites/
    ├── Nightborne/
    └── Background/
```

## Технологии

- **Backend**: Spring Boot, Java 11
- **Frontend**: HTML5, CSS3, JavaScript (ES6+)
- **Графика**: HTML5 Canvas
- **API**: REST
- **Сборка**: Maven/Gradle

## Авторы

Проект мигрирован с сохранением оригинальной концепции ООП и всех игровых механик. 