<p align="center">
  <img src="src/main/resources/app/app-logo-2x.png" width="96" alt="Icon" />
</p>

<h1 align="center">Self-Order Kiosk</h1>

<p align="center">A self-checkout experience where customers can quickly place an order.</p>

## Features

- 🛒 Place an order
- 🔀 Customize your order
- 🔓 Authenticate admin users
- ✏️ Manage items, categories and users
- 📃 See overview of orders
- 🎨 Light and dark theme support

## Stack

- Java Swing
- [FlatLaf](https://www.formdev.com/flatlaf)
- MySQL
- Maven

## Requirements

- JDK 8
- NetBeans

## Getting Started

1. Follow [these steps](#database) to set up the database.
2. Open the project in NetBeans.
3. Run the build command to download Maven dependencies.
4. Finally, run the project.

## Database

1. Create a MySQL database named **self_order_kiosk**.
2. Run the SQL queries located in the `/migrations` folder.
3. Create a new file named **db.properties** in `/src/main/resources/app`.
4. Enter the following details:

```properties
url=jdbc:mysql://<HOST>:<PORT>/self_order_kiosk?serverTimeZone=UTC
username=<USERNAME>
password=<PASSWORD>
```

**Demo Login**

- Username: bob
- Password: 123

## Production

1. Open the project in NetBeans.
2. Run the clean and build command.
3. Go to the `/target` folder.
4. Open **self-order-kiosk-VERSION-jar-with-dependencies.jar**.

## Changelog

### v0.2.0 - 2020-10-04

- New feature to manage items in Admin area
- New Launcher menubar
- New About window
- Theme settings moved to Launcher menubar

### v0.1.0 - 2020-09-30

- Initial alpha release

## License

[MIT License](LICENSE)

## Credits

Icons made by [Freepik](https://www.flaticon.com/authors/basic-gradient/gradient) from [www.flaticon.com](https://www.flaticon.com)
