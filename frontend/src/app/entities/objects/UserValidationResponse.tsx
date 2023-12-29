//could be deleted later if validation from server comes in different format
//this case handles response entity with the status of username, email and password
class UserValidationResponse {
    username?: string;
    email?: string;
    password?: string;
}