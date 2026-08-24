# Food Design Android App

## Project Summary

**Food Design** is an Android application developed using **Java** with an **MVVM architecture**. The project includes authentication APIs, OTP verification, session/token management, Firebase Cloud Messaging (FCM), and user notifications.

The application is integrated with the Bossly backend APIs and Firebase services.

---

## Technology Stack

* Android Java
* MVVM Architecture
* Retrofit
* Firebase Authentication/Services
* Firebase Cloud Messaging (FCM)
* AndroidX
* Material Components
* SharedPreferences / Session Management
* Gradle Kotlin DSL
* REST APIs
* JSON

---

# Authentication API Implementation

The following authentication APIs have been implemented:

## 1. Register API

Implemented user registration functionality.

**Flow:**

```text
User enters registration details
        ↓
Register API
        ↓
OTP sent to user
        ↓
OTP Verification
```

Responsibilities:

* Create a new user
* Submit registration information to backend
* Handle API success/error responses
* Navigate user to OTP verification

---

## 2. OTP Verify API

OTP verification has been implemented after registration.

**Flow:**

```text
Register
   ↓
OTP received
   ↓
Enter OTP
   ↓
OTP Verify API
   ↓
Account verified
```

Responsibilities:

* Submit OTP to backend
* Validate OTP
* Handle invalid/expired OTP
* Continue authentication flow after successful verification

---

## 3. Login API

Login API has been implemented using Retrofit.

**Flow:**

```text
Email / Username
       +
Password
       ↓
Login API
       ↓
Access Token
       +
Refresh Token
       ↓
SessionManager
       ↓
Authenticated Application
```

The application stores the required authentication/session information locally so the logged-in user can remain authenticated.

---

## 4. Refresh Token API

Refresh-token functionality has been implemented to handle expired access tokens.

**Flow:**

```text
API Request
    ↓
Access Token
    ↓
Token Expired
    ↓
Refresh Token API
    ↓
New Access Token
    ↓
Retry API Request
```

This prevents the user from being unnecessarily logged out when the short-lived access token expires.

---

## 5. Logout API

Logout API has been implemented.

**Flow:**

```text
User clicks Logout
       ↓
Logout API
       ↓
Refresh Token revoked/invalidated
       ↓
Clear local session
       ↓
Clear stored authentication data
       ↓
Navigate to Login
```

Local session data is cleared during logout so that the previous user's account cannot remain active on the device.

### Logout Token Handling

The logout API requires authentication using the access token and refresh token.

If an expired access token is supplied, the backend can return:

```text
401 Unauthorized
invalid_token
The token expired
```

The Android application therefore needs to handle expired tokens correctly and ensure local session cleanup during logout.

---

# Firebase Cloud Messaging Implementation

Firebase Cloud Messaging (FCM) has been integrated into the Android application for user notifications.

## FCM Implementation

Implemented:

* Firebase Messaging dependency
* FCM registration token generation
* `FirebaseMessagingService`
* Notification channel
* Android 13+ notification permission
* Notification handling
* Notification click handling
* Foreground notification handling
* Background notification handling
* Killed-app notification handling

---

# FCM Token

The application generates an FCM registration token for each device.

**Flow:**

```text
Android Application
        ↓
Firebase Messaging
        ↓
FCM Registration Token
        ↓
Log / Session / Backend
```

The token can be sent to the backend and associated with the logged-in user.

Example:

```text
User
 ↓
Login
 ↓
FCM Token
 ↓
Backend
 ↓
Save token against user
```

The token is refreshed automatically by Firebase when required, and `onNewToken()` is implemented to handle token updates.

---

# User Notification Handling

Notifications have been tested for all major application states.

## 1. Background Mode

When the application is running in the background:

```text
Firebase
   ↓
FCM
   ↓
Android System
   ↓
Notification
```

Notification is received successfully.

---

## 2. Killed Mode

When the application is closed/killed:

```text
Firebase
   ↓
FCM
   ↓
Android System
   ↓
Notification
```

Notification is received successfully.

Clicking the notification opens the application.

---

## 3. Foreground Mode

When the application is open and running:

```text
Firebase
   ↓
FCM
   ↓
FirebaseMessagingService
   ↓
onMessageReceived()
   ↓
Notification Channel
   ↓
Notification
```

Foreground notification handling has been implemented using `onMessageReceived()` so that the application can display notifications while the user is actively using the app.

---

# Notification Architecture

```text
                    Firebase
                       │
                       ▼
                 FCM Service
                       │
                       ▼
              Android Application
                       │
              ┌────────┴────────┐
              │                 │
          Background         Foreground
              │                 │
              ▼                 ▼
      System Notification   onMessageReceived()
                                │
                                ▼
                         Custom Notification
```

---

# Authentication + Notification Architecture

The overall application flow is:

```text
                    Android App
                        │
             ┌──────────┴──────────┐
             │                     │
       Authentication          Firebase FCM
             │                     │
             ▼                     ▼
       Register/Login          FCM Token
             │                     │
             ▼                     ▼
       OTP Verification      Backend/User
             │                     │
             ▼                     ▼
       Access Token          Notification
       Refresh Token               │
             │              ┌──────┴──────┐
             ▼              │             │
       SessionManager     Foreground   Background/
             │                           Killed
             ▼
          Logout
```

---

# Current Implementation Status

### Authentication

* [x] Register API
* [x] Login API
* [x] OTP Verify API
* [x] Refresh Token API
* [x] Logout API
* [x] Access Token handling
* [x] Refresh Token handling
* [x] Local session management

### Firebase Notifications

* [x] Firebase configuration
* [x] Firebase Messaging dependency
* [x] FCM token generation
* [x] `FirebaseMessagingService`
* [x] Notification permission
* [x] Notification channel
* [x] Background notification testing
* [x] Killed-app notification testing
* [x] Foreground notification handling
* [x] Notification click handling

---

# Testing

Authentication APIs were tested using Swagger/API testing tools.

Firebase notifications were tested on a real Android device in:

```text
1. Foreground
2. Background
3. Killed
```

FCM registration token generation was also verified through Android Studio Logcat.

---

# Future Improvements

* Store FCM token against the authenticated user in the backend.
* Support multiple devices per user.
* Implement automatic access-token refresh using Retrofit interceptors/authenticator.
* Handle `401 Unauthorized` globally.
* Implement notification deep links.
* Add notification types such as order, event, message, and system notifications.
* Add notification history.
* Add read/unread notification status.
* Improve logout handling when the access token has expired.

---

# Repository

This project is an **Android Java application** using MVVM architecture, REST APIs, Retrofit, Firebase Cloud Messaging, and token-based authentication.

The project is maintained in GitHub and contains the implementation for authentication, session management, API integration, and Firebase user notifications.
