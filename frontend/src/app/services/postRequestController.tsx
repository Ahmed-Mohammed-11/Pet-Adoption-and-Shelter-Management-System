import {BASE_BACKEND_URL} from "@/app/constants/apiConstants";
import SignupController from "@/app/services/signupController";
import signupController from "@/app/services/signupController";

class PostRequest implements IPostRequestController {
    sendPostRequest(payload: Object, endpoint: string): Promise<Response> {
        const url = BASE_BACKEND_URL + endpoint;
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('mode', 'cors')

        return fetch(url, {
            method: 'POST',
            body: JSON.stringify(payload),
            headers: headers
        });
    }
}

const PostRequestController = new PostRequest();
export default PostRequestController;