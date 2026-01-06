# Task Tracking API

## Setup Instructions
1. MySQL mein task_db naam ka database banayein.
2. src/main/resources/application.properties mein apni DB credentials update karein.
3. Project ko run karne ke liye ./mvnw spring-boot:run command ka use karein.

## Assumptions
- Admin role ko identify karne ke liye x-user-role: admin header ka use kiya gaya hai.
- Status flow hamesha pending -> in_progress -> completed rahega.
- Ek user ko maximum 5 pending tasks assign kiye ja sakte hain.

## Sample API Requests
- *Create User*: POST /users (Body: name, email, role)
- *Create Task*: POST /tasks (Header: x-user-role: admin)
- *Update Status*: PATCH /tasks/{id}/status (Body: status)
- *Get Tasks*: GET /tasks?status=pending
