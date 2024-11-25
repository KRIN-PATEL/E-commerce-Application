# E-commerce Application

## Overview
This is a fully functional **E-commerce Application** built using **Android Studio** with **Java**. The application supports user registration, login, product browsing, cart management, and a seamless checkout process. It uses **Room Database** for local data storage and implements modern architecture patterns to ensure efficient and maintainable code.

## Features

1. **User Authentication**:
   - Register and log in securely.
   - User sessions managed with `SharedPreferences`.

2. **Product Catalog**:
   - Displays a list of preloaded products with names, prices, descriptions, and images.
   - Search functionality to filter products dynamically.

3. **Product Details**:
   - Detailed view of each product with options to add items to the cart or navigate to the cart.

4. **Cart Management**:
   - Add, update, and remove items in the cart.
   - Real-time calculation of total cost, tax, and grand total.

5. **Checkout System**:
   - Collects user information such as name, email, address, and payment details.
   - Validates all input fields to ensure data accuracy.

6. **Thank You Page**:
   - Personalized thank-you message upon completing an order.
   - Clears cart and resets user session after the purchase.

7. **Image Slider**:
   - Highlights featured products with a carousel-style slider.

## Technologies Used
- **Android**: Native app development.
- **Java**: Core programming language.
- **Room Database**: Local data storage with SQLite.
- **Architecture**: MVVM (Model-View-ViewModel).
- **Glide**: For efficient image loading and caching.
- **RecyclerView**: For dynamic and efficient data display.

## Repository Link
[GitHub Repository](https://github.com/KRIN-PATEL/E-commerce-Application.git)

## Installation Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/KRIN-PATEL/E-commerce-Application.git
   ```

2. **Open in Android Studio**:
   - Open **Android Studio**.
   - Select "Open an Existing Project" and navigate to the cloned repository.

3. **Build the Project**:
   - Navigate to **Build > Make Project** to compile the project and resolve dependencies.

4. **Run the Application**:
   - Connect an Android device or start an emulator.
   - Click the **Run** button in Android Studio.

## Project Structure
```
├── MainActivity.java           # Entry point for login/register navigation
├── LoginActivity.java          # Handles user login
├── RegisterActivity.java       # Handles user registration
├── ProductActivity.java        # Displays product catalog with search functionality
├── ProductDetailActivity.java  # Detailed view of a product
├── CartActivity.java           # Manages cart items and totals
├── CheckoutActivity.java       # Processes user and payment information
├── ThankYouActivity.java       # Displays order confirmation
├── AppDatabase.java            # Room database singleton
├── ProductDao.java             # DAO for product operations
├── UserDao.java                # DAO for user operations
├── CartManager.java            # Singleton to manage cart items
├── layouts/                    # XML files for UI
├── resources/                  # Drawable resources (images)
└── adapters/                   # Adapters for RecyclerView
```

## Database Schema
1. **Users Table**:
   - Stores user credentials (username and password).

2. **Products Table**:
   - Stores product details such as name, price, description, and image.

## Key Components

### Cart Management
- **CartManager**:
  - Centralized logic for cart operations (add, update, remove).
- **CartActivity**:
  - Displays cart items and calculates totals.

### Product Management
- **ProductActivity**:
  - Displays product catalog with search functionality.
- **ProductDetailActivity**:
  - Allows users to view product details and add items to the cart.

### Room Database
- **Entities**:
  - `Product.java`: Represents the `products` table.
  - `User.java`: Represents the `users` table.
- **DAOs**:
  - `ProductDao.java`: Methods for querying products.
  - `UserDao.java`: Methods for querying users.


