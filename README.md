# ToDo API

## 概要
Spring Boot で作成した ToDo 管理の REST API です。
Claude Code と一緒に DDD 軽量版の設計パターンを学習しながら作成しました。

## 技術スタック
- Java 17
- Spring Boot 3.5.10
- Spring Data JPA
- H2 Database（インメモリ）
- JUnit 5 / Mockito
- Swagger UI（springdoc-openapi）
- Lombok
- Maven

## 起動方法

### Eclipse
1. プロジェクトを右クリック
2. Run As → Spring Boot App

### コマンドライン
```powershell
cd C:\dev\todo
.\mvnw.cmd spring-boot:run
```

## API 一覧

| メソッド | エンドポイント | 説明 |
|---------|---------------|------|
| POST | `/api/todos` | ToDo を作成 |
| GET | `/api/todos` | ToDo 一覧を取得 |
| GET | `/api/todos/{id}` | ToDo 詳細を取得 |
| PUT | `/api/todos/{id}` | ToDo を更新 |
| DELETE | `/api/todos/{id}` | ToDo を削除 |
| POST | `/api/todos/{id}/complete` | ToDo を完了にする |

## Swagger UI
http://localhost:8080/swagger-ui/index.html

## テスト実行
```powershell
.\mvnw.cmd test
```

## H2 Console
http://localhost:8080/h2-console

| 設定 | 値 |
|------|-----|
| JDBC URL | `jdbc:h2:mem:tododb` |
| User Name | `sa` |
| Password | （空欄） |
