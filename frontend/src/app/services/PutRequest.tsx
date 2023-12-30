import {BASE_BACKEND_URL} from "@/app/constants/apiConstants";
import SignupController from "@/app/services/signupController";
import signupController from "@/app/services/signupController";
import PostRequestController from "@/app/services/postRequestController";

class PutRequestController {
    sendPutRequest(payload: Object, endpoint: string): Promise<Response> {
        const url = BASE_BACKEND_URL + endpoint;
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('mode', 'cors')
        headers.append('Authorization', localStorage.getItem("Authorization")!);

        return fetch(url, {
            method: 'PUT',
            body: JSON.stringify(payload),
            headers: headers
        });
    }
}

const PutRequest = new PutRequestController();
export default PutRequest;