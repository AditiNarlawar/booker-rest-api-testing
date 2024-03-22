## REST Assured API Testing | Aditi Narlawar


This project contains end-to-end tests for the Restful Booker API. It covers various scenarios such as creating a booking, retrieving a booking, updating a booking, partially updating a booking, deleting a booking, checking if a booking is deleted, checking booking details when ID does not exist and booking details with invalid ID.

## What does this repository contain?

This project contains codes for demonstrating RESTful API Testing using Booker APIs. It includes the following features and components:

- **`REST Assured`:** Code demonstrating API testing using REST Assured library.
- **`Hamcrest Matchers`:** Assertions are performed using Hamcrest Matchers.
- **`TestNG Listeners`:** Events in the test execution are captured using TestNG Listeners, providing detailed logs.
- **`Log4j`:** Logs are captured using Log4j for better debugging and reporting.
- **`Extent Report`:** Extent Report is used for comprehensive test reporting.
- **`Lombok`:** Getter and Setters are automatically generated for post body requests using Lombok.
- **`Rest APIs`:** Testing is performed on the [Restful Booker](https://restful-booker.herokuapp.com/apidoc/index.html) REST APIs.

### Other features
- The repository covers various HTTP methods including `GET`, `POST`, `PUT`, `PATCH`, and `DELETE` requests.
- Sample code to get the `AuthorizationToken` is included.

### Run Test
- Using command: `mvn clean test`

## Test Scenarios

### Create Booking
- Test method to create a new booking.
- Uses the `POST /booking` endpoint.
- Verifies that the booking is created successfully and all details are correct.

### Get Booking
- Test method to retrieve an existing booking.
- Uses the `GET /booking/{id}` endpoint.
- Verifies that the retrieved booking details match the expected values.

### Update Booking
- Test method to update an existing booking.
- Uses the `PUT /booking/{id}` endpoint.
- Verifies that the booking is updated successfully and all details are correct.

### Partially Update Booking
- Test method to partially update an existing booking.
- Uses the `PATCH /booking/{id}` endpoint.
- Verifies that the booking is partially updated successfully and all modified details are correct.

### Delete Booking
- Test method to delete an existing booking.
- Uses the `DELETE /booking/{id}` endpoint.
- Verifies that the booking is deleted successfully.

### Check Booking Deletion
- Test method to check if a booking is deleted.
- Uses the `GET /booking/{id}` endpoint.
- Verifies that the booking returns a '404 Not Found' status code after deletion.

### Test Delete Booking When ID Does Not Exist
- Test method to verify the behavior when attempting to delete a booking with a non-existing ID.
- Utilizes the `DELETE /booking/{id}` endpoint.
- Verifies that the server responds with a '405 Method Not Allowed' status code.

### Test Booking Details With Invalid ID
- Test method to verify the behavior when retrieving booking details using an invalid ID.
- Utilizes the `GET /booking/{id}` endpoint.
- Verifies that the server responds with a '404 Not Found' status code.

## Test Setup
- The test data is set up before running the tests using the `@BeforeTest` annotation.
- Authentication token is generated using the `generateToken()` method.

## Prerequisites

- Java Development Kit (JDK)
- TestNG
- Maven
- IDE (e.g., IntelliJ IDEA)

## Setup Instructions

1. Clone the project repository.
2. Open the project in your preferred IDE.
3. Configure the project dependencies and WebDriver executable.
4. Locate the `restfulbookersuite.xml` file in the project structure.
   Right-click on the `restfulbookersuite.xml` file.
   Select run option to run the TestNG suite.

## Author

[Aditi Narlawar]