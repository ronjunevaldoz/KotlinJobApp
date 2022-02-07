## User Guide

## Task
- [x] Create a login screen with a username and password
- [x] Once logged in, show a list of all available jobs, show the following details for each job
   - Reference
   - Title
   - Description
   - Client name (client first_name and last_name)
   - Company (client company)
   - Job Address (address1, city, state, postcode)
   - Status (job_status name)
- [x] Have a button to add a new job
- [x] When the button to add a new job is clicked, show a form to enter the following details
   - Title
   - Description

## Comments and Suggestions
- Consuming html tags from API data is not recommended in android application, It will break the material guide lines and override the design and layouts.

## Architecture & Patterns
- MVVM with Clean Architecture and Repository with Use-cases

## Dependencies
- [Compose] - Kotlin jetpack ui composition
- [Navigation] - Kotlin jetpack navigation
- [Hilt] - Dagger dependency injection
- [Serialization] - Kotlin serialization for encoding/decoding data
- [Retrofit2] - Network service library
- [DataStore] - Kotlin jetpack persistence used in android kotlin
- [Coroutine] - Async code execution

## License
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Author

Ron June Valdoz
