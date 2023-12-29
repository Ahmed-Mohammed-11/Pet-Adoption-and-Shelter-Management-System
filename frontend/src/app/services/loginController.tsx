import {BASE_BACKEND_URL} from "@/app/constants/apiConstants";

class LoginController implements IPostRequestController {
    sendPostRequest(payload: UserDTO, endpoint: string) {
        const url = BASE_BACKEND_URL + endpoint;
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('mode', 'cors')
        headers.append("withCredentials", "true");

        return fetch(url, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(payload)
        });
    }
}

const loginController = new LoginController();
export default loginController;