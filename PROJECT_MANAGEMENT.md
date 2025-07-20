# Project Management System

A simple project management system that allows project managers to create, configure, and manage projects with their teams.

## Features

### 🎯 Project Creation & Configuration
- **Create Projects**: Set up new projects with name, description, scope, and deadlines
- **Set Deadlines**: Define and update project deadlines
- **Define Scope**: Specify and modify project scope and objectives
- **Project Status**: Track project progress (PLANNING, ACTIVE, ON_HOLD, COMPLETED, CANCELLED)

### 👥 Team Management
- **Add Team Members**: Assign users to project teams
- **Remove Team Members**: Remove users from project teams
- **Set Entire Team**: Replace the whole team at once
- **Change Manager**: Transfer project management to another user

### 📊 Project Tracking
- **View Projects**: Get detailed project information
- **Overdue Detection**: Automatically identify overdue projects
- **Status Filtering**: Filter projects by status
- **Search**: Find projects by name

## API Endpoints

### Project Management

#### Create a Project
```bash
POST /api/projects
Content-Type: application/json

{
  "name": "E-commerce Platform",
  "description": "Building a new e-commerce platform",
  "scope": "Frontend, Backend, Database, Payment Integration",
  "deadline": "2024-06-30",
  "managerId": 1
}
```

#### Update Project Configuration
```bash
PUT /api/projects/{projectId}?managerId=1
Content-Type: application/json

{
  "name": "Updated Project Name",
  "description": "Updated description",
  "scope": "Updated scope",
  "startDate": "2024-01-15",
  "deadline": "2024-07-15",
  "status": "ACTIVE"
}
```

#### Set Project Deadline
```bash
PUT /api/projects/{projectId}/deadline?managerId=1
Content-Type: application/json

{
  "deadline": "2024-08-01"
}
```

#### Update Project Scope
```bash
PUT /api/projects/{projectId}/scope?managerId=1
Content-Type: application/json

{
  "scope": "Frontend development, API integration, Testing"
}
```

### Team Management

#### Add Team Member
```bash
POST /api/projects/{projectId}/team/{userId}?managerId=1
```

#### Remove Team Member
```bash
DELETE /api/projects/{projectId}/team/{userId}?managerId=1
```

#### Set Entire Team
```bash
PUT /api/projects/{projectId}/team?managerId=1
Content-Type: application/json

{
  "userIds": [2, 3, 4, 5]
}
```

#### Change Project Manager
```bash
PUT /api/projects/{projectId}/manager?currentManagerId=1
Content-Type: application/json

{
  "newManagerId": 2
}
```

### Project Information

#### Get Project Details
```bash
GET /api/projects/{projectId}
```

#### Get Projects Managed by User
```bash
GET /api/projects/managed?managerId=1
```

#### Get Projects Where User is Involved
```bash
GET /api/projects/involvement?userId=1
```

#### Get Team Members
```bash
GET /api/projects/{projectId}/team
```

#### Get Project Manager
```bash
GET /api/projects/{projectId}/manager
```

#### Get Projects by Status
```bash
GET /api/projects/status/ACTIVE
```

#### Get Overdue Projects
```bash
GET /api/projects/overdue
```

#### Search Projects
```bash
GET /api/projects/search?name=ecommerce
```

## Project Status Types

- **PLANNING**: Project is in planning phase
- **ACTIVE**: Project is actively being worked on
- **ON_HOLD**: Project is temporarily paused
- **COMPLETED**: Project has been completed
- **CANCELLED**: Project has been cancelled

## Security & Access Control

### Project Manager Permissions
Only the project manager can:
- Update project configuration (name, description, scope, deadlines)
- Add or remove team members
- Change project status
- Transfer management to another user

### Read Access
- Project managers can view all details of their projects
- Team members can view projects they are assigned to
- All users can view basic project information if they have access

## Usage Examples

### Example 1: Creating and Setting Up a Project

```bash
# 1. Create the project
curl -X POST http://localhost:8080/api/projects \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "name": "Mobile App Development",
    "description": "Developing a mobile app for customer engagement",
    "scope": "iOS, Android, Backend API, Admin Dashboard",
    "deadline": "2024-09-15",
    "managerId": 1
  }'

# 2. Add team members
curl -X POST "http://localhost:8080/api/projects/1/team/2?managerId=1" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"

curl -X POST "http://localhost:8080/api/projects/1/team/3?managerId=1" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"

# 3. Update project scope
curl -X PUT "http://localhost:8080/api/projects/1/scope?managerId=1" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{"scope": "iOS App, Android App, REST API, Admin Web Portal, Push Notifications"}'

# 4. Set project to active
curl -X PUT "http://localhost:8080/api/projects/1?managerId=1" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{"status": "ACTIVE"}'
```

### Example 2: Managing Team Changes

```bash
# Add a new team member
curl -X POST "http://localhost:8080/api/projects/1/team/4?managerId=1" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"

# Remove a team member
curl -X DELETE "http://localhost:8080/api/projects/1/team/3?managerId=1" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"

# Replace entire team
curl -X PUT "http://localhost:8080/api/projects/1/team?managerId=1" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "userIds": [2, 4, 5, 6]
  }'
```

### Example 3: Project Monitoring

```bash
# Check overdue projects
curl "http://localhost:8080/api/projects/overdue" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"

# Get all active projects
curl "http://localhost:8080/api/projects/status/ACTIVE" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"

# Get projects managed by user
curl "http://localhost:8080/api/projects/managed?managerId=1" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Database Schema

The project management system creates these tables:

- **projects**: Main project information
- **project_team_members**: Many-to-many relationship between projects and users

### Project Table Structure

| Field | Type | Description |
|-------|------|-------------|
| id | Long | Primary key |
| name | String | Project name |
| description | String | Project description |
| scope | String | Project scope |
| start_date | LocalDate | Project start date |
| deadline | LocalDate | Project deadline |
| status | Enum | Project status |
| manager_id | Long | ID of project manager |
| created_at | LocalDateTime | Creation timestamp |
| updated_at | LocalDateTime | Last update timestamp |

## Getting Started

1. **Register users** that will be project managers and team members
2. **Create projects** with one user as the manager
3. **Add team members** to the projects
4. **Set deadlines and scope** as needed
5. **Track progress** using status updates

The system is simple and focused on core project management needs without unnecessary complexity. 