# 🌍 EasyTravel – Android Travel Planner App

**EasyTravel** is an Android application developed by a team of five students during the *Human-Computer Interaction (HCI)* course. The app helps users search, plan, and organize travel routes and events, while focusing on **usability**, **simplicity**, and **user-centered design**.

The app was created with Java in Android Studio and combines real-time data visualization, travel search, route preferences, and personal event planning in a sleek mobile interface.

---

## 🧠 Motivation

We identified gaps in existing travel apps: lack of clarity, cluttered UI, and poor adaptability to different user types. EasyTravel addresses these challenges by:
- Offering eco-friendly, fast, and cheap travel options
- Providing a simplified route planner interface
- Allowing users to manage personal travel itineraries
- Prioritizing design clarity, contrast, and accessibility

---

## ✨ Features

### 🔐 Authentication
- User registration and login
- Password reset with email validation
- Input format feedback and error handling

### 🧭 Travel Planning
- Search for travel routes based on preferences (eco / fast / cheap)
- Displays travel suggestions with cost, duration, and transport type
- Integration of Unsplash API for destination images
- Bookings saved locally and linked to providers for final booking

### 🗓️ Event Management
- Users can create custom events for each trip
- Events include name, address, location, and icon
- Editable and scrollable event overview

### 🛠 Settings & Help
- Edit profile (name, address, email, etc.)
- Info section with FAQs and contact details
- Improved UI with time pickers and input masks

---

## 🎨 Design & Usability

- Clean and minimal UI with guided interactions
- Real-time visual feedback (e.g., confirmation popups)
- Dynamic country selection dropdown
- Color accessibility and font clarity
- Safety checks for travel deletion

Screens improved based on usability testing with 10 participants across:
- Register task
- Travel task
- Event task
- Address task
- Help section task

---

## 🧪 Evaluation Summary

Usability tests revealed:
- Need for password feedback → implemented real-time feedback
- Event screen improvements → clearer icons, chronological ordering
- Travel view enhancements → sorting, price consistency, confirmation prompts
- Settings clarity → better labels, logout feature added

---

## 🔧 Tech Stack

| Area              | Technology                     |
|-------------------|--------------------------------|
| Language          | Java                           |
| IDE               | Android Studio                 |
| UI Libraries      | Android SDK, XML Layouts       |
| APIs Used         | Unsplash API, Retrofit2        |
| Image Loader      | Picasso                        |
| Mapping           | OpenStreetMap (Osmdroid)       |
| Testing           | Usability Interviews (10 users)|

---

## 🚀 Future Plans

- Fixing map bug for region-specific display
- Adding event recommendations and hotel booking
- Switching to **React Native** for better cross-device compatibility
- Improving travel sorting and provider integration

---

## 📘 Course Info

This project was developed during the **Human-Computer Interaction** course at the University of Vienna as part of the 2023 summer semester curriculum.

---

## 📄 License

This app was created for academic purposes only and is not intended for commercial use.
